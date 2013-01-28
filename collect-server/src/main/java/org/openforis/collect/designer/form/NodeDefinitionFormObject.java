package org.openforis.collect.designer.form;

import org.openforis.collect.designer.model.AttributeType;
import org.openforis.collect.designer.model.NodeType;
import org.openforis.collect.metamodel.ui.UIOptions;
import org.openforis.collect.metamodel.ui.UITab;
import org.openforis.collect.model.CollectSurvey;
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
	
	public static final String INHERIT_TAB_NAME = "inherit";
	
	//public static NamedObject INHERIT_TAB;
	public static UITab INHERIT_TAB;
	
	static {
		//init static variables
		//INHERIT_TAB = new NamedObject(LabelKeys.INHERIT_TAB);
		INHERIT_TAB = new UITab();
	};
	
	private EntityDefinition parentDefinition;
	
	//generic
	private String name;
	private String description;
	private boolean multiple;
	private boolean required;
	private String requiredExpression;
	private String relevantExpression;
	private Integer minCount;
	private Integer maxCount;
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
	
	NodeDefinitionFormObject(EntityDefinition parentDefn) {
		this.parentDefinition = parentDefn;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static NodeDefinitionFormObject<? extends NodeDefinition> newInstance(EntityDefinition parentDefn, NodeType nodeType, AttributeType attributeType) {
		NodeDefinitionFormObject<NodeDefinition> formObject = null;
		if ( nodeType != null ) {
			switch ( nodeType) {
			case ENTITY:
				formObject = new EntityDefinitionFormObject(parentDefn);
				break;
			case ATTRIBUTE:
				return (NodeDefinitionFormObject<NodeDefinition>) newInstance(parentDefn, attributeType);
			}
		}
		return formObject;
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
	public void loadFrom(T source, String language, String defaultLanguage) {
		super.loadFrom(source, language, defaultLanguage);
		//generic
		name = source.getName();
		multiple = source.isMultiple();
		Integer nodeMinCount = source.getMinCount();
		required = nodeMinCount != null && nodeMinCount.intValue() > 0;
		requiredExpression = source.getRequiredExpression();
		relevantExpression = source.getRelevantExpression();
		minCount = nodeMinCount;
		maxCount = source.getMaxCount();
		if (! multiple ) {
			maxCount = null;
		}
		//labels
		headingLabel = getLabel(source, Type.HEADING, language, defaultLanguage);
		instanceLabel = getLabel(source, Type.INSTANCE, language, defaultLanguage);
		numberLabel = getLabel(source, Type.NUMBER, language, defaultLanguage);
		interviewPromptLabel = getPrompt(source, Prompt.Type.INTERVIEW, language, defaultLanguage);
		paperPromptLabel = getPrompt(source, Prompt.Type.PAPER, language, defaultLanguage);
		handheldPromptLabel = getPrompt(source, Prompt.Type.HANDHELD, language, defaultLanguage);
		pcPromptLabel = getPrompt(source, Prompt.Type.PC, language, defaultLanguage);
		description = getDescription(source, language, defaultLanguage);
		//layout
		UIOptions uiOptions = getUIOptions(source);
		UITab tab = uiOptions.getAssignedTab(parentDefinition, source, false);
		tabName = tab != null ? tab.getName(): INHERIT_TAB_NAME;
	}

	protected String getLabel(T source, Type type, String languageCode, String defaultLanguage) {
		String result = source.getLabel(type, languageCode);
		if ( result == null && languageCode != null && languageCode.equals(defaultLanguage) ) {
			//try to get the label associated to default language
			result = source.getLabel(type, null);
		}
		return result;
	}

	protected String getPrompt(T source, Prompt.Type type, String languageCode, String defaultLanguage) {
		String result = source.getPrompt(type, languageCode);
		if ( result == null && languageCode != null && languageCode.equals(defaultLanguage) ) {
			//try to get the label associated to default language
			result = source.getPrompt(type, null);
		}
		return result;
	}
	
	protected String getDescription(T source, String languageCode, String defaultLanguage) {
		String result = source.getDescription(languageCode);
		if ( result == null && languageCode != null && languageCode.equals(defaultLanguage) ) {
			//try to get the label associated to default language
			result = source.getDescription(null);
		}
		return result;
	}
	
	@Override
	public void saveTo(T dest, String languageCode) {
		super.saveTo(dest, languageCode);
		dest.setName(name);
		dest.setLabel(Type.HEADING, languageCode, headingLabel);
		dest.setLabel(Type.INSTANCE, languageCode, instanceLabel);
		dest.setLabel(Type.NUMBER, languageCode, numberLabel);
		dest.setPrompt(Prompt.Type.HANDHELD, languageCode, handheldPromptLabel);
		dest.setPrompt(Prompt.Type.INTERVIEW, languageCode, interviewPromptLabel);
		dest.setPrompt(Prompt.Type.PAPER, languageCode, paperPromptLabel);
		dest.setPrompt(Prompt.Type.PC, languageCode, pcPromptLabel);
		dest.setDescription(languageCode, description);
		dest.setMultiple(multiple);
		dest.setMinCount(null);
		dest.setMaxCount(null);
		dest.setRequiredExpression(null);
		if ( multiple ) {
			dest.setMinCount(minCount);
			dest.setMaxCount(maxCount);
		} else {
			if (required) {
				dest.setMinCount(1);
			}
			dest.setRequiredExpression(requiredExpression);
		}
		dest.setRelevantExpression(relevantExpression);
		saveTabInfo(dest);
	}

	protected void saveTabInfo(T dest) {
		if ( parentDefinition != null ) {
			UIOptions uiOptions = getUIOptions(dest);
			if ( tabName == null || tabName.equals(INHERIT_TAB_NAME) ) {
				uiOptions.removeTabAssociation(dest);
			} else {
				UITab tab = uiOptions.getTab(tabName);
				uiOptions.assignToTab(dest, tab);
			}
		}
	}

	@Override
	protected void reset() {
		//TODO
	}
	
	protected UIOptions getUIOptions(NodeDefinition nodeDefn) {
		CollectSurvey survey = (CollectSurvey) nodeDefn.getSurvey();
		UIOptions uiOptions = survey.getUIOptions();
		return uiOptions;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required =  required;
	}

	public String getRequiredExpression() {
		return requiredExpression;
	}

	public void setRequiredExpression(String requiredExpression) {
		this.requiredExpression = requiredExpression;
	}

	public String getRelevantExpression() {
		return relevantExpression;
	}

	public void setRelevantExpression(String relevantExpression) {
		this.relevantExpression = relevantExpression;
	}

	public Integer getMinCount() {
		return minCount;
	}

	public void setMinCount(Integer minCount) {
		this.minCount = minCount;
	}

	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

}
