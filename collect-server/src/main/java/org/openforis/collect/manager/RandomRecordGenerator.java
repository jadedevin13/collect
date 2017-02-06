package org.openforis.collect.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openforis.collect.model.CollectRecord;
import org.openforis.collect.model.CollectSurvey;
import org.openforis.collect.model.RecordFilter;
import org.openforis.collect.model.SamplingDesignItem;
import org.openforis.collect.model.SamplingDesignSummaries;
import org.openforis.collect.model.User;
import org.openforis.commons.collection.Visitor;
import org.openforis.idm.metamodel.AttributeDefinition;
import org.openforis.idm.metamodel.EntityDefinition;
import org.openforis.idm.model.Attribute;
import org.openforis.idm.model.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RandomRecordGenerator {
	
	@Autowired
	private RecordManager recordManager;
	@Autowired
	private SurveyManager surveyManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private SamplingDesignManager samplingDesignManager;
	
	@Transactional
	public CollectRecord generate(int surveyId, int userId) {
		CollectSurvey survey = surveyManager.getById(surveyId);
		Map<List<String>, Integer> recordMeasurementsByKey = calculateRecordMeasurementsByKey(survey, userId);
		
		if (recordMeasurementsByKey.isEmpty()) {
			throw new IllegalStateException(String.format("Sampling design data not defined for survey %s", survey.getName()));
		}
		Integer minMeasurements = Collections.min(recordMeasurementsByKey.values());
		//do not consider measurements different from minimum measurement
		Iterator<Entry<List<String>, Integer>> iterator = recordMeasurementsByKey.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<List<String>, Integer> entry = iterator.next();
			if (entry.getValue() != minMeasurements) {
				iterator.remove();
			}
		}
		//randomly select one record key among the ones with minimum measurements
		List<List<String>> recordKeys = new ArrayList<List<String>>(recordMeasurementsByKey.keySet());
		int recordKeyIdx = new Double(Math.floor(Math.random() * recordKeys.size())).intValue();
		List<String> recordKey = recordKeys.get(recordKeyIdx);
		
		User user = userManager.loadById(userId);
		
		EntityDefinition rootEntityDef = survey.getSchema().getRootEntityDefinitions().get(0);
		String rootEntityName = rootEntityDef.getName();
		CollectRecord record = recordManager.create(survey, rootEntityName, user, null);
		
		List<AttributeDefinition> keyAttributeDefs = rootEntityDef.getKeyAttributeDefinitions();
		//TODO exclude measurement attribute (and update it later with username?)
		for (int i = 0; i < keyAttributeDefs.size(); i++) {
			String keyPart = recordKey.get(i);
			AttributeDefinition keyAttrDef = keyAttributeDefs.get(i);
			Attribute<?,Value> keyAttribute = record.findNodeByPath(keyAttrDef.getPath());
			recordManager.updateAttribute(keyAttribute, keyAttrDef.createValue(keyPart));
		}
		
		recordManager.save(record);
		
		return record;
	}
	
	private Map<List<String>, Integer> calculateRecordMeasurementsByKey(CollectSurvey survey, final int userID) {
		final Map<List<String>, Integer> measurementsByRecordKey = new HashMap<List<String>, Integer>();
		recordManager.visitSummaries(new RecordFilter(survey), null, new Visitor<CollectRecord>() {
			public void visit(CollectRecord summary) {
				if (summary.getCreatedBy().getId() != userID) {
					List<String> keys = summary.getRootEntityKeyValues();
					Integer measurements = measurementsByRecordKey.get(keys);
					if (measurements == null) {
						measurements = 1;
					} else {
						measurements += 1;
					}
					measurementsByRecordKey.put(keys, measurements);
				}
			}
		});
		EntityDefinition rootEntityDef = survey.getSchema().getRootEntityDefinitions().get(0);
		List<AttributeDefinition> keyAttrDefs = rootEntityDef.getKeyAttributeDefinitions();
		//TODO exclude measurement attributes
		List<AttributeDefinition> nonMeasurementKeyAttrDefs = keyAttrDefs;
		SamplingDesignSummaries samplingPoints = samplingDesignManager.loadBySurvey(survey.getId(), nonMeasurementKeyAttrDefs.size());
		for (SamplingDesignItem item : samplingPoints.getRecords()) {
			if (item.getLevelCodes().size() == nonMeasurementKeyAttrDefs.size()) {
				List<String> key = item.getLevelCodes().subList(0, nonMeasurementKeyAttrDefs.size());
				Integer measurements = measurementsByRecordKey.get(key);
				if (measurements == null) {
					measurementsByRecordKey.put(key, 0);
				}
			}
		}
		return measurementsByRecordKey;
	}

}
