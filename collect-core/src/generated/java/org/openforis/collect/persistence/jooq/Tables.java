/**
 * This class is generated by jOOQ
 */
package org.openforis.collect.persistence.jooq;


import javax.annotation.Generated;

import org.openforis.collect.persistence.jooq.tables.OfcApplicationInfo;
import org.openforis.collect.persistence.jooq.tables.OfcCodeList;
import org.openforis.collect.persistence.jooq.tables.OfcConfig;
import org.openforis.collect.persistence.jooq.tables.OfcDataCleansingChain;
import org.openforis.collect.persistence.jooq.tables.OfcDataCleansingChainSteps;
import org.openforis.collect.persistence.jooq.tables.OfcDataCleansingReport;
import org.openforis.collect.persistence.jooq.tables.OfcDataCleansingStep;
import org.openforis.collect.persistence.jooq.tables.OfcDataCleansingStepValue;
import org.openforis.collect.persistence.jooq.tables.OfcDataQuery;
import org.openforis.collect.persistence.jooq.tables.OfcDataQueryGroup;
import org.openforis.collect.persistence.jooq.tables.OfcDataQueryGroupQuery;
import org.openforis.collect.persistence.jooq.tables.OfcDataQueryType;
import org.openforis.collect.persistence.jooq.tables.OfcDataReport;
import org.openforis.collect.persistence.jooq.tables.OfcDataReportItem;
import org.openforis.collect.persistence.jooq.tables.OfcLogo;
import org.openforis.collect.persistence.jooq.tables.OfcMessage;
import org.openforis.collect.persistence.jooq.tables.OfcMessageProcessing;
import org.openforis.collect.persistence.jooq.tables.OfcRecord;
import org.openforis.collect.persistence.jooq.tables.OfcRecordData;
import org.openforis.collect.persistence.jooq.tables.OfcSamplingDesign;
import org.openforis.collect.persistence.jooq.tables.OfcSurvey;
import org.openforis.collect.persistence.jooq.tables.OfcSurveyFile;
import org.openforis.collect.persistence.jooq.tables.OfcTaxon;
import org.openforis.collect.persistence.jooq.tables.OfcTaxonVernacularName;
import org.openforis.collect.persistence.jooq.tables.OfcTaxonomy;
import org.openforis.collect.persistence.jooq.tables.OfcUser;
import org.openforis.collect.persistence.jooq.tables.OfcUserRole;
import org.openforis.collect.persistence.jooq.tables.OfcUserUsergroup;
import org.openforis.collect.persistence.jooq.tables.OfcUsergroup;
import org.openforis.collect.persistence.jooq.tables.SamplingDesign;


/**
 * Convenience access to all tables in collect
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

	/**
	 * The table collect.ofc_application_info
	 */
	public static final OfcApplicationInfo OFC_APPLICATION_INFO = org.openforis.collect.persistence.jooq.tables.OfcApplicationInfo.OFC_APPLICATION_INFO;

	/**
	 * The table collect.ofc_code_list
	 */
	public static final OfcCodeList OFC_CODE_LIST = org.openforis.collect.persistence.jooq.tables.OfcCodeList.OFC_CODE_LIST;

	/**
	 * The table collect.ofc_config
	 */
	public static final OfcConfig OFC_CONFIG = org.openforis.collect.persistence.jooq.tables.OfcConfig.OFC_CONFIG;

	/**
	 * The table collect.ofc_data_cleansing_chain
	 */
	public static final OfcDataCleansingChain OFC_DATA_CLEANSING_CHAIN = org.openforis.collect.persistence.jooq.tables.OfcDataCleansingChain.OFC_DATA_CLEANSING_CHAIN;

	/**
	 * The table collect.ofc_data_cleansing_chain_steps
	 */
	public static final OfcDataCleansingChainSteps OFC_DATA_CLEANSING_CHAIN_STEPS = org.openforis.collect.persistence.jooq.tables.OfcDataCleansingChainSteps.OFC_DATA_CLEANSING_CHAIN_STEPS;

	/**
	 * The table collect.ofc_data_cleansing_report
	 */
	public static final OfcDataCleansingReport OFC_DATA_CLEANSING_REPORT = org.openforis.collect.persistence.jooq.tables.OfcDataCleansingReport.OFC_DATA_CLEANSING_REPORT;

	/**
	 * The table collect.ofc_data_cleansing_step
	 */
	public static final OfcDataCleansingStep OFC_DATA_CLEANSING_STEP = org.openforis.collect.persistence.jooq.tables.OfcDataCleansingStep.OFC_DATA_CLEANSING_STEP;

	/**
	 * The table collect.ofc_data_cleansing_step_value
	 */
	public static final OfcDataCleansingStepValue OFC_DATA_CLEANSING_STEP_VALUE = org.openforis.collect.persistence.jooq.tables.OfcDataCleansingStepValue.OFC_DATA_CLEANSING_STEP_VALUE;

	/**
	 * The table collect.ofc_data_query
	 */
	public static final OfcDataQuery OFC_DATA_QUERY = org.openforis.collect.persistence.jooq.tables.OfcDataQuery.OFC_DATA_QUERY;

	/**
	 * The table collect.ofc_data_query_group
	 */
	public static final OfcDataQueryGroup OFC_DATA_QUERY_GROUP = org.openforis.collect.persistence.jooq.tables.OfcDataQueryGroup.OFC_DATA_QUERY_GROUP;

	/**
	 * The table collect.ofc_data_query_group_query
	 */
	public static final OfcDataQueryGroupQuery OFC_DATA_QUERY_GROUP_QUERY = org.openforis.collect.persistence.jooq.tables.OfcDataQueryGroupQuery.OFC_DATA_QUERY_GROUP_QUERY;

	/**
	 * The table collect.ofc_data_query_type
	 */
	public static final OfcDataQueryType OFC_DATA_QUERY_TYPE = org.openforis.collect.persistence.jooq.tables.OfcDataQueryType.OFC_DATA_QUERY_TYPE;

	/**
	 * The table collect.ofc_data_report
	 */
	public static final OfcDataReport OFC_DATA_REPORT = org.openforis.collect.persistence.jooq.tables.OfcDataReport.OFC_DATA_REPORT;

	/**
	 * The table collect.ofc_data_report_item
	 */
	public static final OfcDataReportItem OFC_DATA_REPORT_ITEM = org.openforis.collect.persistence.jooq.tables.OfcDataReportItem.OFC_DATA_REPORT_ITEM;

	/**
	 * The table collect.ofc_logo
	 */
	public static final OfcLogo OFC_LOGO = org.openforis.collect.persistence.jooq.tables.OfcLogo.OFC_LOGO;

	/**
	 * The table collect.ofc_message
	 */
	public static final OfcMessage OFC_MESSAGE = org.openforis.collect.persistence.jooq.tables.OfcMessage.OFC_MESSAGE;

	/**
	 * The table collect.ofc_message_processing
	 */
	public static final OfcMessageProcessing OFC_MESSAGE_PROCESSING = org.openforis.collect.persistence.jooq.tables.OfcMessageProcessing.OFC_MESSAGE_PROCESSING;

	/**
	 * The table collect.ofc_record
	 */
	public static final OfcRecord OFC_RECORD = org.openforis.collect.persistence.jooq.tables.OfcRecord.OFC_RECORD;

	/**
	 * The table collect.ofc_record_data
	 */
	public static final OfcRecordData OFC_RECORD_DATA = org.openforis.collect.persistence.jooq.tables.OfcRecordData.OFC_RECORD_DATA;

	/**
	 * The table collect.ofc_sampling_design
	 */
	public static final OfcSamplingDesign OFC_SAMPLING_DESIGN = org.openforis.collect.persistence.jooq.tables.OfcSamplingDesign.OFC_SAMPLING_DESIGN;

	/**
	 * The table collect.ofc_survey
	 */
	public static final OfcSurvey OFC_SURVEY = org.openforis.collect.persistence.jooq.tables.OfcSurvey.OFC_SURVEY;

	/**
	 * The table collect.ofc_survey_file
	 */
	public static final OfcSurveyFile OFC_SURVEY_FILE = org.openforis.collect.persistence.jooq.tables.OfcSurveyFile.OFC_SURVEY_FILE;

	/**
	 * The table collect.ofc_taxon
	 */
	public static final OfcTaxon OFC_TAXON = org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON;

	/**
	 * The table collect.ofc_taxonomy
	 */
	public static final OfcTaxonomy OFC_TAXONOMY = org.openforis.collect.persistence.jooq.tables.OfcTaxonomy.OFC_TAXONOMY;

	/**
	 * The table collect.ofc_taxon_vernacular_name
	 */
	public static final OfcTaxonVernacularName OFC_TAXON_VERNACULAR_NAME = org.openforis.collect.persistence.jooq.tables.OfcTaxonVernacularName.OFC_TAXON_VERNACULAR_NAME;

	/**
	 * The table collect.ofc_user
	 */
	public static final OfcUser OFC_USER = org.openforis.collect.persistence.jooq.tables.OfcUser.OFC_USER;

	/**
	 * The table collect.ofc_usergroup
	 */
	public static final OfcUsergroup OFC_USERGROUP = org.openforis.collect.persistence.jooq.tables.OfcUsergroup.OFC_USERGROUP;

	/**
	 * The table collect.ofc_user_role
	 */
	public static final OfcUserRole OFC_USER_ROLE = org.openforis.collect.persistence.jooq.tables.OfcUserRole.OFC_USER_ROLE;

	/**
	 * The table collect.ofc_user_usergroup
	 */
	public static final OfcUserUsergroup OFC_USER_USERGROUP = org.openforis.collect.persistence.jooq.tables.OfcUserUsergroup.OFC_USER_USERGROUP;

	/**
	 * The table collect.sampling_design
	 */
	public static final SamplingDesign SAMPLING_DESIGN = org.openforis.collect.persistence.jooq.tables.SamplingDesign.SAMPLING_DESIGN;
}
