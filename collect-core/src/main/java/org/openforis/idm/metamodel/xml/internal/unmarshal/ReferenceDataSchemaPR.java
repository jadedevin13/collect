package org.openforis.idm.metamodel.xml.internal.unmarshal;

import static org.openforis.idm.metamodel.xml.IdmlConstants.ATTRIBUTE;
import static org.openforis.idm.metamodel.xml.IdmlConstants.KEY;
import static org.openforis.idm.metamodel.xml.IdmlConstants.NAME;
import static org.openforis.idm.metamodel.xml.IdmlConstants.REFERENCE_DATA_SCHEMA;
import static org.openforis.idm.metamodel.xml.IdmlConstants.SAMPLING_POINT;
import static org.openforis.idm.metamodel.xml.IdmlConstants.TAXON;

import java.io.IOException;

import org.openforis.idm.metamodel.ReferenceDataSchema;
import org.openforis.idm.metamodel.ReferenceDataSchema.ReferenceDataDefinition;
import org.openforis.idm.metamodel.ReferenceDataSchema.SamplingPointDefinition;
import org.openforis.idm.metamodel.ReferenceDataSchema.TaxonDefinition;
import org.openforis.idm.metamodel.xml.XmlParseException;
import org.xmlpull.v1.XmlPullParserException;

/**
 * @author S. Ricci
 */
class ReferenceDataSchemaPR extends IdmlPullReader {
	
	private ReferenceDataSchema referenceDataSchema;

	public ReferenceDataSchemaPR() {
		super(REFERENCE_DATA_SCHEMA, 1);
		addChildPullReaders(
				new SamplingPointPR(),
				new TaxonPR()
			);
	}
	
	@Override
	protected void onStartTag() throws XmlParseException,
			XmlPullParserException, IOException {
		super.onStartTag();
		SurveyUnmarshaller parentReader = (SurveyUnmarshaller) getParentReader();
		this.referenceDataSchema = new ReferenceDataSchema();
		parentReader.survey.setReferenceDataSchema(this.referenceDataSchema);
	}

	private abstract class ReferenceDataPR<T extends ReferenceDataDefinition> extends IdmlPullReader {

		protected T referenceData;
		
		public ReferenceDataPR(String tagName) {
			super(tagName);
			addChildPullReaders(new AttributePR());
		}
	}
	
	private class SamplingPointPR extends ReferenceDataPR<SamplingPointDefinition> {
		
		public SamplingPointPR() {
			super(SAMPLING_POINT);
		}

		@Override
		protected void onStartTag() throws XmlParseException,
				XmlPullParserException, IOException {
			super.onStartTag();
			ReferenceDataSchemaPR parentReader = (ReferenceDataSchemaPR) getParentReader();
			referenceData = new ReferenceDataSchema.SamplingPointDefinition();
			parentReader.referenceDataSchema.setSamplingPointDefinition(referenceData);
		}
	}

	private class TaxonPR extends ReferenceDataPR<TaxonDefinition> {
		
		public TaxonPR() {
			super(TAXON);
		}
		
		@Override
		protected void onStartTag() throws XmlParseException,
				XmlPullParserException, IOException {
			super.onStartTag();
			ReferenceDataSchemaPR parentReader = (ReferenceDataSchemaPR) getParentReader();
			referenceData = new ReferenceDataSchema.TaxonDefinition();
			parentReader.referenceDataSchema.setTaxonDefinition(referenceData);
		}
	}

	private class AttributePR extends IdmlPullReader {

		public AttributePR() {
			super(ATTRIBUTE);
		}
		
		@Override
		protected void onStartTag() throws XmlParseException {
			String name = getAttribute(NAME, true);
			boolean key = getBooleanAttributeWithDefault(KEY, false);
			ReferenceDataPR<?> parentReader = (ReferenceDataPR<?>) getParentReader();
			parentReader.referenceData.addAttribute(name, key);
		}

	}

}