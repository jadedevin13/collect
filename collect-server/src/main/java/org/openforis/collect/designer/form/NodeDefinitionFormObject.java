package org.openforis.collect.designer.form;

import org.apache.commons.lang3.StringUtils;
import org.openforis.collect.designer.model.AttributeType;
import org.openforis.collect.designer.model.NodeType;
import org.openforis.collect.metamodel.CollectAnnotations;
import org.openforis.collect.metamodel.ui.UIOptions;
import org.openforis.collect.metamodel.ui.UIOptions.Orientation;
import org.openforis.collect.model.CollectSurvey;
import org.openforis.idm.metamodel.AttributeDefinition;
import org.openforis.idm.metamodel.Calculable;
import org.openforis.idm.metamodel.EntityDefinition;
import org.openforis.idm.metamodel.NodeDefinition;
import org.openforis.idm.metamodel.NodeLabel.Type;
import org.openforis.idm.metamodel.Prompt;

/**
 * 
 * @author S. Ricci
 *
 */
public abstract class NodeDefinitionFormObject<T extends NodeDefinition> extends VersionableItemFormObject<T> {
	
	public static final String REQUIRED_FIELD = "required";
	
	public enum RequirenessType {
		NOT_REQUIRED, ALWAYS_REQUIRED, REQUIRED_WHEN
	}
	
	public enum RelevanceType {
		ALWAYS_RELEVANT, RELEVANT_WHEN
	}
	
	@SuppressWarnings("unused")
	private EntityDefinition parentDefinition;
	
	//generic
	private String name;
	private String description;
	private boolean multiple;
	private String requirenessType;
	private String requiredWhenExpression;
	private String relevanceType;
	private String relevantExpression;
	private boolean hideWhenNotRelevant;
	private String minCountExpression;
	private String maxCountExpression;
	private boolean calculated;
	private boolean includeInDataExport;
	private boolean showInUI;
	private boolean fromCollectEarthCSV;

	//labels
	private String headingLabel;
	private String instanceLabel;
	private String numberLabel;
	private String interviewPromptLabel;
	private String paperPromptLabel;
	private String handheldPromptLabel;
	private String pcPromptLabel;
	//layout
	private String tabName;
	private int column;
	private int columnSpan;
	private Integer width;
	private Integer labelWidth;
	private String labelOrientation;

	
	NodeDefinitionFormObject(EntityDefinition parentDefn) {
		this.parentDefinition = parentDefn;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static NodeDefinitionFormObject<? extends NodeDefinition> newInstance(EntityDefinition parentDefn, NodeType nodeType, AttributeType attributeType) {
		if ( nodeType == null ) {
			return null;
		} else {
			switch ( nodeType) {
			case ENTITY:
				return new EntityDefinitionFormObject(parentDefn);
			case ATTRIBUTE:
				return (NodeDefinitionFormObject<NodeDefinition>) newInstance(parentDefn, attributeType);
			default:
				throw new IllegalArgumentException("Unsupported node type: " + nodeType.getClass().getName());
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes" })
	public static AttributeDefinitionFormObject<?> newInstance(EntityDefinition parentDefn, AttributeType attributeType) {
		if ( attributeType != null ) {
			switch (attributeType) {
			case BOOLEAN:
				return new BooleanAttributeDefinitionFormObject(parentDefn);
			case CODE:
				return new CodeAttributeDefinitionFormObject(parentDefn);
			case COORDINATE:
				return new CoordinateAttributeDefinitionFormObject(parentDefn);
			case DATE:
				return new DateAttributeDefinitionFormObject(parentDefn);
			case FILE:
				return new FileAttributeDefinitionFormObject(parentDefn);
			case NUMBER:
				return new NumberAttributeDefinitionFormObject(parentDefn);
			case RANGE:
				return new RangeAttributeDefinitionFormObject(parentDefn);
			case TAXON:
				return new TaxonAttributeDefinitionFormObject(parentDefn);
			case TEXT:
				return new TextAttributeDefinitionFormObject(parentDefn);
			case TIME:
				return new TimeAttributeDefinitionFormObject(parentDefn);
			default:
				throw new IllegalStateException("Attribute type not supported");
			}
		} else {
			return null;
		}
	}
	
	@Override
	public void loadFrom(T source, String language) {
		super.loadFrom(source, language);
		CollectSurvey survey = (CollectSurvey) source.getSurvey();
		UIOptions uiOptions = survey.getUIOptions();
		
		//generic
		name = source.getName();
		multiple = source.isMultiple();
		if (source.isAlwaysRequired()) {
			requirenessType = RequirenessType.ALWAYS_REQUIRED.name();
		} else {
			requiredWhenExpression = source.extractRequiredExpression();
			if (requiredWhenExpression == null) {
				requirenessType = RequirenessType.NOT_REQUIRED.name();
			} else {
				requirenessType = RequirenessType.REQUIRED_WHEN.name();
			}
		}
		
		relevanceType = source.getRelevantExpression() == null ? RelevanceType.ALWAYS_RELEVANT.name(): RelevanceType.RELEVANT_WHEN.name();
		relevantExpression = source.getRelevantExpression();
		minCountExpression = source.getMinCountExpression();
		maxCountExpression = multiple ? source.getMaxCountExpression(): null;
		
		//labels
		headingLabel = source.getLabel(Type.HEADING, language);
		instanceLabel = source.getLabel(Type.INSTANCE, language);
		numberLabel = source.getLabel(Type.NUMBER, language);
		interviewPromptLabel = source.getPrompt(Prompt.Type.INTERVIEW, language);
		paperPromptLabel = source.getPrompt(Prompt.Type.PAPER, language);
		handheldPromptLabel = source.getPrompt(Prompt.Type.HANDHELD, language);
		pcPromptLabel = source.getPrompt(Prompt.Type.PC, language);
		description = source.getDescription(language);
		//layout
		hideWhenNotRelevant = uiOptions.isHideWhenNotRelevant(source);
		column = uiOptions.getColumn(source);
		columnSpan = uiOptions.getColumnSpan(source);
		width = uiOptions.getWidth(source);
		labelWidth = uiOptions.getLabelWidth(source);
		labelOrientation = uiOptions.getLabelOrientation(source).name();

		CollectAnnotations annotations = survey.getAnnotations();
		
		if (source instanceof AttributeDefinition) {
			fromCollectEarthCSV = annotations.isFromCollectEarthCSV((AttributeDefinition) source);
		}
		
		if ( source instanceof Calculable ) {
			calculated = ((Calculable) source).isCalculated();
			//show in UI
			showInUI = ! uiOptions.isHidden(source);
			
			includeInDataExport = annotations.isIncludedInDataExport(source);
		}
	}

	@Override
	public void saveTo(T dest, String languageCode) {
		super.saveTo(dest, languageCode);
		if ( ! name.equals(dest.getName()) ) {
			dest.rename(name);
		}
		dest.setLabel(Type.HEADING, languageCode, headingLabel);
		dest.setLabel(Type.INSTANCE, languageCode, instanceLabel);
		dest.setLabel(Type.NUMBER, languageCode, numberLabel);
		dest.setPrompt(Prompt.Type.HANDHELD, languageCode, handheldPromptLabel);
		dest.setPrompt(Prompt.Type.INTERVIEW, languageCode, interviewPromptLabel);
		dest.setPrompt(Prompt.Type.PAPER, languageCode, paperPromptLabel);
		dest.setPrompt(Prompt.Type.PC, languageCode, pcPromptLabel);
		dest.setDescription(languageCode, description);
		dest.setMultiple(multiple);
		dest.setMinCountExpression(null);
		dest.setMaxCountExpression(null);
		dest.setRequiredExpression(null);
		if ( multiple ) {
			dest.setMinCountExpression(StringUtils.trimToNull(minCountExpression));
			dest.setMaxCountExpression(StringUtils.trimToNull(maxCountExpression));
		} else {
			RequirenessType requirenessTypeEnum = RequirenessType.valueOf(requirenessType);
			switch(requirenessTypeEnum) {
			case ALWAYS_REQUIRED:
				dest.setAlwaysRequired();
				break;
			case REQUIRED_WHEN:
				dest.setRequiredExpression(StringUtils.trimToNull(requiredWhenExpression));
				break;
			default:
				break;
			}
		}
		
		CollectSurvey survey = (CollectSurvey) dest.getSurvey();
		CollectAnnotations annotations = survey.getAnnotations();
		
		UIOptions uiOptions = survey.getUIOptions();
		
		RelevanceType relevanceTypeEnum = RelevanceType.valueOf(relevanceType);
		switch (relevanceTypeEnum) {
		case RELEVANT_WHEN:
			dest.setRelevantExpression(StringUtils.trimToNull(relevantExpression));
			uiOptions.setHideWhenNotRelevant(dest, hideWhenNotRelevant);
			break;
		default:
			dest.setRelevantExpression(null);
			uiOptions.setHideWhenNotRelevant(dest, false);
		}
		
		if (dest instanceof AttributeDefinition) {
			annotations.setFromCollectEarthCSV((AttributeDefinition) dest, fromCollectEarthCSV);
		}
		
		//layout
		uiOptions.setColumn(dest, column);
		uiOptions.setColumnSpan(dest, columnSpan);
		uiOptions.setWidth(dest, width);
		uiOptions.setLabelWidth(dest, labelWidth);
		uiOptions.setLabelOrientation(dest, Orientation.valueOf(labelOrientation));
		
		if ( dest instanceof Calculable ) {
			((Calculable) dest).setCalculated(calculated);
			
			//include in data export
			annotations.setIncludeInDataExport(dest, includeInDataExport);
			
			//show in ui
			uiOptions.setHidden(dest, ! showInUI);
		}
	}

	@Override
	protected void reset() {
		calculated = false;
		showInUI = true;
		includeInDataExport = true;
	}
	
	protected UIOptions getUIOptions(NodeDefinition nodeDefn) {
		CollectSurvey survey = (CollectSurvey) nodeDefn.getSurvey();
		UIOptions uiOptions = survey.getUIOptions();
		return uiOptions;
	}
	
	public void setParentDefinition(EntityDefinition parentDefinition) {
		this.parentDefinition = parentDefinition;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isCalculated() {
		return calculated;
	}
	
	public void setCalculated(boolean calculated) {
		this.calculated = calculated;
	}
	
	public boolean isIncludeInDataExport() {
		return includeInDataExport;
	}
	
	public void setIncludeInDataExport(boolean includeInDataExport) {
		this.includeInDataExport = includeInDataExport;
	}
	
	public boolean isShowInUI() {
		return showInUI;
	}
	
	public void setShowInUI(boolean showInUI) {
		this.showInUI = showInUI;
	}
	
	public String getHeadingLabel() {
		return headingLabel;
	}
	
	public void setHeadingLabel(String headingLabel) {
		this.headingLabel = headingLabel;
	}
	
	public String getInstanceLabel() {
		return instanceLabel;
	}
	
	public void setInstanceLabel(String instanceLabel) {
		this.instanceLabel = instanceLabel;
	}
	
	public String getNumberLabel() {
		return numberLabel;
	}
	
	public void setNumberLabel(String numberLabel) {
		this.numberLabel = numberLabel;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isMultiple() {
		return multiple;
	}
	
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}
	
	public String getInterviewPromptLabel() {
		return interviewPromptLabel;
	}

	public void setInterviewPromptLabel(String interviewPromptLabel) {
		this.interviewPromptLabel = interviewPromptLabel;
	}

	public String getPaperPromptLabel() {
		return paperPromptLabel;
	}

	public void setPaperPromptLabel(String paperPromptLabel) {
		this.paperPromptLabel = paperPromptLabel;
	}

	public String getHandheldPromptLabel() {
		return handheldPromptLabel;
	}

	public void setHandheldPromptLabel(String handheldPromptLabel) {
		this.handheldPromptLabel = handheldPromptLabel;
	}

	public String getPcPromptLabel() {
		return pcPromptLabel;
	}

	public void setPcPromptLabel(String pcPromptLabel) {
		this.pcPromptLabel = pcPromptLabel;
	}

	public String getRequirenessType() {
		return requirenessType;
	}
	
	public void setRequirenessType(String requirenessType) {
		this.requirenessType = requirenessType;
	}
	
	public String getRequiredWhenExpression() {
		return requiredWhenExpression;
	}
	
	public void setRequiredWhenExpression(String requiredWhenExpression) {
		this.requiredWhenExpression = requiredWhenExpression;
	}
	
	public String getRelevanceType() {
		return relevanceType;
	}
	
	public void setRelevanceType(String relevanceType) {
		this.relevanceType = relevanceType;
	}
	
	public String getRelevantExpression() {
		return relevantExpression;
	}

	public void setRelevantExpression(String relevantExpression) {
		this.relevantExpression = relevantExpression;
	}

	public boolean isHideWhenNotRelevant() {
		return hideWhenNotRelevant;
	}
	
	public void setHideWhenNotRelevant(boolean hideWhenNotRelevant) {
		this.hideWhenNotRelevant = hideWhenNotRelevant;
	}
	
	public String getMinCountExpression() {
		return minCountExpression;
	}

	public void setMinCountExpression(String expression) {
		this.minCountExpression = expression;
	}

	public String getMaxCountExpression() {
		return maxCountExpression;
	}

	public void setMaxCountExpression(String expression) {
		this.maxCountExpression = expression;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public int getColumnSpan() {
		return columnSpan;
	}
	
	public void setColumnSpan(int columnSpan) {
		this.columnSpan = columnSpan;
	}
	
	public Integer getWidth() {
		return width;
	}
	
	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getLabelWidth() {
		return labelWidth;
	}
	
	public void setLabelWidth(Integer labelWidth) {
		this.labelWidth = labelWidth;
	}
	
	public String getLabelOrientation() {
		return labelOrientation;
	}
	
	public void setLabelOrientation(String labelOrientation) {
		this.labelOrientation = labelOrientation;
	}
	
	public boolean isFromCollectEarthCSV() {
		return fromCollectEarthCSV;
	}
	
	public void setFromCollectEarthCSV(boolean fromCollectEarthCSV) {
		this.fromCollectEarthCSV = fromCollectEarthCSV;
	}
	
}
