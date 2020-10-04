package org.openforis.collect.web.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openforis.collect.command.AddAttributeCommand;
import org.openforis.collect.command.AddEntityCommand;
import org.openforis.collect.command.Command;
import org.openforis.collect.command.CommandDispatcher;
import org.openforis.collect.command.CreateRecordCommand;
import org.openforis.collect.command.CreateRecordPreviewCommand;
import org.openforis.collect.command.DeleteNodeCommand;
import org.openforis.collect.command.DeleteRecordCommand;
import org.openforis.collect.command.RecordCommand;
import org.openforis.collect.command.UpdateAttributeCommand;
import org.openforis.collect.command.UpdateBooleanAttributeCommand;
import org.openforis.collect.command.UpdateCodeAttributeCommand;
import org.openforis.collect.command.UpdateDateAttributeCommand;
import org.openforis.collect.command.UpdateIntegerAttributeCommand;
import org.openforis.collect.command.UpdateRealAttributeCommand;
import org.openforis.collect.command.UpdateTextAttributeCommand;
import org.openforis.collect.designer.metamodel.AttributeType;
import org.openforis.collect.event.EventListener;
import org.openforis.collect.event.RecordEvent;
import org.openforis.collect.manager.SessionManager;
import org.openforis.collect.manager.SurveyManager;
import org.openforis.collect.model.CollectSurvey;
import org.openforis.collect.web.ws.AppWS;
import org.openforis.collect.web.ws.AppWS.RecordEventMessage;
import org.openforis.commons.web.Response;
import org.openforis.idm.metamodel.BooleanAttributeDefinition;
import org.openforis.idm.metamodel.CodeAttributeDefinition;
import org.openforis.idm.metamodel.DateAttributeDefinition;
import org.openforis.idm.metamodel.NumberAttributeDefinition;
import org.openforis.idm.metamodel.NumericAttributeDefinition.Type;
import org.openforis.idm.metamodel.TextAttributeDefinition;
import org.openforis.idm.metamodel.Unit;
import org.openforis.idm.model.BooleanValue;
import org.openforis.idm.model.Code;
import org.openforis.idm.model.Date;
import org.openforis.idm.model.IntegerValue;
import org.openforis.idm.model.RealValue;
import org.openforis.idm.model.TextValue;
import org.openforis.idm.model.Value;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("api/command")
public class CommandController {

	@Autowired
	private SurveyManager surveyManager;
	@Autowired
	private CommandDispatcher commandDispatcher;
	@Autowired
	private SessionManager sessionManager;
	@Autowired
	private AppWS appWS;

	@RequestMapping(value = "record", method = POST, consumes = APPLICATION_JSON_VALUE)
	@Transactional
	public @ResponseBody Response createRecord(@RequestBody CreateRecordCommand command) {
		return submitCommand(command);
	}

	@RequestMapping(value = "record_preview", method = POST, consumes = APPLICATION_JSON_VALUE)
	public @ResponseBody List<RecordEventView> createRecordPreview(@RequestBody CreateRecordPreviewCommand command) {
		return submitCommandSync(command);
	}

	@RequestMapping(value = "record", method = DELETE, consumes = APPLICATION_JSON_VALUE)
	@Transactional
	public @ResponseBody Response deleteRecord(@RequestBody DeleteRecordCommand command) {
		return submitCommand(command);
	}

	@RequestMapping(value = "record/attribute/new", method = POST, consumes = APPLICATION_JSON_VALUE)
	@Transactional
	public @ResponseBody Response addAttribute(@RequestBody AddAttributeCommand command) {
		return submitCommand(command);
	}

	@RequestMapping(value = "record/attributes", method = POST, consumes = APPLICATION_JSON_VALUE)
	@Transactional
	public @ResponseBody Response addOrUpdateAttributes(@RequestBody UpdateAttributesCommandWrapper commandsWrapper) {
		List<UpdateAttributeCommandWrapper> commands = commandsWrapper.getCommands();
		if (!commands.isEmpty()) {
			final CollectSurvey survey = getSurvey(commands.get(0));
			commands.forEach(c -> {
				UpdateAttributeCommand<?> command = c.toCommand(survey);
				submitCommand(command);
			});
		}
		return new Response();
	}

	@RequestMapping(value = "record/attribute", method = POST, consumes = APPLICATION_JSON_VALUE)
	@Transactional
	public @ResponseBody Object updateAttribute(@RequestBody UpdateAttributeCommandWrapper commandWrapper) {
		CollectSurvey survey = getSurvey(commandWrapper);
		UpdateAttributeCommand<?> command = commandWrapper.toCommand(survey);
		return submitCommand(command);
	}

	@RequestMapping(value = "record/entity", method = POST, consumes = APPLICATION_JSON_VALUE)
	@Transactional
	public @ResponseBody Object addEntity(@RequestBody AddEntityCommand command) {
		return submitCommand(command);
	}

	@RequestMapping(value = "record/node/delete", method = POST, consumes = APPLICATION_JSON_VALUE)
	@Transactional
	public @ResponseBody Object deleteNode(@RequestBody DeleteNodeCommand command) {
		return submitCommand(command);
	}

	private Response submitCommand(Command command) {
		if (command instanceof RecordCommand) {
			((RecordCommand) command).setUsername(sessionManager.getLoggedUsername());
		}
		commandDispatcher.submit(command, new EventListener() {
			public void onEvent(RecordEvent event) {
				appWS.sendMessage(new RecordEventMessage(new RecordEventView(event)));
			}
		});
		return new Response();
	}

	private List<RecordEventView> submitCommandSync(Command command) {
		if (command instanceof RecordCommand) {
			((RecordCommand) command).setUsername(sessionManager.getLoggedUsername());
		}
		List<RecordEvent> events = commandDispatcher.submitSync(command);

		List<RecordEventView> result = new ArrayList<RecordEventView>(events.size());
		for (RecordEvent event : events) {
			result.add(new RecordEventView(event));
		}
		return result;
	}
	
	private CollectSurvey getSurvey(RecordCommand command) {
		return surveyManager.getOrLoadSurveyById(command.getSurveyId());
	}

	static class RecordEventView {

		private RecordEvent event;

		public RecordEventView(RecordEvent event) {
			super();
			this.event = event;
		}

		public String getEventType() {
			return event.getClass().getSimpleName();
		}

		public RecordEvent getEvent() {
			return event;
		}

	}

	static class UpdateAttributesCommandWrapper {

		List<UpdateAttributeCommandWrapper> commands = new ArrayList<UpdateAttributeCommandWrapper>();

		public List<UpdateAttributeCommandWrapper> getCommands() {
			return commands;
		}

		public void setCommands(List<UpdateAttributeCommandWrapper> commands) {
			this.commands = commands;
		}
	}

	static class UpdateAttributeCommandWrapper extends UpdateAttributeCommand<Value> {

		private static final long serialVersionUID = 1L;

		AttributeType attributeType;
		Type numericType;
		Map<String, Object> valueByField;

		Value extractValue(CollectSurvey survey) {
			switch (attributeType) {
			case BOOLEAN:
				return new BooleanValue((Boolean) valueByField.get(BooleanAttributeDefinition.VALUE_FIELD));
			case CODE:
				return new Code((String) valueByField.get(CodeAttributeDefinition.CODE_FIELD),
						(String) valueByField.get(CodeAttributeDefinition.QUALIFIER_FIELD));
			case DATE:
				return new Date((Integer) valueByField.get(DateAttributeDefinition.YEAR_FIELD_NAME),
						(Integer) valueByField.get(DateAttributeDefinition.MONTH_FIELD_NAME),
						(Integer) valueByField.get(DateAttributeDefinition.DAY_FIELD_NAME));
			case NUMBER:
				Integer unitId = (Integer) valueByField.get(NumberAttributeDefinition.UNIT_FIELD);
				Unit unit = unitId == null ? null : survey.getUnit(unitId);
				return numericType == Type.INTEGER
						? new IntegerValue(
								((Number) valueByField.get(NumberAttributeDefinition.VALUE_FIELD)).intValue(), unit)
						: new RealValue(
								((Number) valueByField.get(NumberAttributeDefinition.VALUE_FIELD)).doubleValue(), unit);
			case TEXT:
				return new TextValue((String) valueByField.get(TextAttributeDefinition.VALUE_FIELD));
			default:
				throw new IllegalStateException("Unsupported command type: " + attributeType);
			}
		}

		@SuppressWarnings("unchecked")
		public UpdateAttributeCommand<Value> toCommand(CollectSurvey survey) {
			UpdateAttributeCommand<Value> c;
			Class<? extends UpdateAttributeCommand<?>> commandType = toCommandType();
			try {
				c = (UpdateAttributeCommand<Value>) commandType.getConstructor().newInstance();
				BeanUtils.copyProperties(this, c, "attributeType", "value");
				c.setValue(extractValue(survey));
				return c;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		private Class<? extends UpdateAttributeCommand<?>> toCommandType() {
			switch (attributeType) {
			case BOOLEAN:
				return UpdateBooleanAttributeCommand.class;
			case CODE:
				return UpdateCodeAttributeCommand.class;
			case DATE:
				return UpdateDateAttributeCommand.class;
			case NUMBER:
				return numericType == Type.INTEGER ? UpdateIntegerAttributeCommand.class
						: UpdateRealAttributeCommand.class;
			case TEXT:
				return UpdateTextAttributeCommand.class;
			default:
				throw new IllegalStateException("Unsupported command type: " + attributeType);
			}
		}

		public AttributeType getAttributeType() {
			return attributeType;
		}

		public void setAttributeType(AttributeType attributeType) {
			this.attributeType = attributeType;
		}

		public Type getNumericType() {
			return numericType;
		}

		public void setNumericType(Type numericType) {
			this.numericType = numericType;
		}

		public Map<String, Object> getValueByField() {
			return valueByField;
		}

		public void setValueByField(Map<String, Object> valueByField) {
			this.valueByField = valueByField;
		}
	}

}
