/**
 * This class is generated by jOOQ
 */
package org.openforis.collect.persistence.jooq.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "2.0.1"},
                            comments = "This class is generated by jOOQ")
public class DataRecord extends org.jooq.impl.UpdatableRecordImpl<org.openforis.collect.persistence.jooq.tables.records.DataRecord> {

	private static final long serialVersionUID = 2128287573;

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public void setId(java.lang.Integer value) {
		setValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.ID, value);
	}

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public java.lang.Integer getId() {
		return getValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.ID);
	}

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public java.util.List<org.openforis.collect.persistence.jooq.tables.records.DataRecord> fetchDataList() {
		return create()
			.selectFrom(org.openforis.collect.persistence.jooq.tables.Data.DATA)
			.where(org.openforis.collect.persistence.jooq.tables.Data.DATA.PARENT_ID.equal(getValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.ID)))
			.fetch();
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [collect.data.record_id]
	 * REFERENCES record [collect.record.id]
	 * </pre></code>
	 */
	public void setRecordId(java.lang.Integer value) {
		setValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.RECORD_ID, value);
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [collect.data.record_id]
	 * REFERENCES record [collect.record.id]
	 * </pre></code>
	 */
	public java.lang.Integer getRecordId() {
		return getValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.RECORD_ID);
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [collect.data.record_id]
	 * REFERENCES record [collect.record.id]
	 * </pre></code>
	 */
	public org.openforis.collect.persistence.jooq.tables.records.RecordRecord fetchRecord() {
		return create()
			.selectFrom(org.openforis.collect.persistence.jooq.tables.Record.RECORD)
			.where(org.openforis.collect.persistence.jooq.tables.Record.RECORD.ID.equal(getValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.RECORD_ID)))
			.fetchOne();
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [collect.data.definition_id]
	 * REFERENCES schema_definition [collect.schema_definition.id]
	 * </pre></code>
	 */
	public void setDefinitionId(java.lang.Integer value) {
		setValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.DEFINITION_ID, value);
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [collect.data.definition_id]
	 * REFERENCES schema_definition [collect.schema_definition.id]
	 * </pre></code>
	 */
	public java.lang.Integer getDefinitionId() {
		return getValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.DEFINITION_ID);
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [collect.data.definition_id]
	 * REFERENCES schema_definition [collect.schema_definition.id]
	 * </pre></code>
	 */
	public org.openforis.collect.persistence.jooq.tables.records.SchemaDefinitionRecord fetchSchemaDefinition() {
		return create()
			.selectFrom(org.openforis.collect.persistence.jooq.tables.SchemaDefinition.SCHEMA_DEFINITION)
			.where(org.openforis.collect.persistence.jooq.tables.SchemaDefinition.SCHEMA_DEFINITION.ID.equal(getValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.DEFINITION_ID)))
			.fetchOne();
	}

	/**
	 * An uncommented item
	 */
	public void setNumber1(java.math.BigDecimal value) {
		setValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.NUMBER1, value);
	}

	/**
	 * An uncommented item
	 */
	public java.math.BigDecimal getNumber1() {
		return getValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.NUMBER1);
	}

	/**
	 * An uncommented item
	 */
	public void setNumber2(java.math.BigDecimal value) {
		setValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.NUMBER2, value);
	}

	/**
	 * An uncommented item
	 */
	public java.math.BigDecimal getNumber2() {
		return getValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.NUMBER2);
	}

	/**
	 * An uncommented item
	 */
	public void setNumber3(java.math.BigDecimal value) {
		setValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.NUMBER3, value);
	}

	/**
	 * An uncommented item
	 */
	public java.math.BigDecimal getNumber3() {
		return getValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.NUMBER3);
	}

	/**
	 * An uncommented item
	 */
	public void setText1(java.lang.String value) {
		setValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.TEXT1, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getText1() {
		return getValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.TEXT1);
	}

	/**
	 * An uncommented item
	 */
	public void setText2(java.lang.String value) {
		setValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.TEXT2, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getText2() {
		return getValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.TEXT2);
	}

	/**
	 * An uncommented item
	 */
	public void setText3(java.lang.String value) {
		setValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.TEXT3, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getText3() {
		return getValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.TEXT3);
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [collect.data.taxon_id]
	 * REFERENCES taxon [collect.taxon.id]
	 * </pre></code>
	 */
	public void setTaxonId(java.lang.Integer value) {
		setValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.TAXON_ID, value);
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [collect.data.taxon_id]
	 * REFERENCES taxon [collect.taxon.id]
	 * </pre></code>
	 */
	public java.lang.Integer getTaxonId() {
		return getValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.TAXON_ID);
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [collect.data.taxon_id]
	 * REFERENCES taxon [collect.taxon.id]
	 * </pre></code>
	 */
	public org.openforis.collect.persistence.jooq.tables.records.TaxonRecord fetchTaxon() {
		return create()
			.selectFrom(org.openforis.collect.persistence.jooq.tables.Taxon.TAXON)
			.where(org.openforis.collect.persistence.jooq.tables.Taxon.TAXON.ID.equal(getValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.TAXON_ID)))
			.fetchOne();
	}

	/**
	 * An uncommented item
	 */
	public void setRemarks(java.lang.String value) {
		setValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.REMARKS, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getRemarks() {
		return getValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.REMARKS);
	}

	/**
	 * An uncommented item
	 */
	public void setSymbol(java.lang.String value) {
		setValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.SYMBOL, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getSymbol() {
		return getValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.SYMBOL);
	}

	/**
	 * An uncommented item
	 */
	public void setState(java.lang.String value) {
		setValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.STATE, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getState() {
		return getValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.STATE);
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [collect.data.parent_id]
	 * REFERENCES data [collect.data.id]
	 * </pre></code>
	 */
	public void setParentId(java.lang.Integer value) {
		setValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.PARENT_ID, value);
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [collect.data.parent_id]
	 * REFERENCES data [collect.data.id]
	 * </pre></code>
	 */
	public java.lang.Integer getParentId() {
		return getValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.PARENT_ID);
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [collect.data.parent_id]
	 * REFERENCES data [collect.data.id]
	 * </pre></code>
	 */
	public org.openforis.collect.persistence.jooq.tables.records.DataRecord fetchData() {
		return create()
			.selectFrom(org.openforis.collect.persistence.jooq.tables.Data.DATA)
			.where(org.openforis.collect.persistence.jooq.tables.Data.DATA.ID.equal(getValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.PARENT_ID)))
			.fetchOne();
	}

	/**
	 * An uncommented item
	 */
	public void setIdx(java.lang.Integer value) {
		setValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.IDX, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.Integer getIdx() {
		return getValue(org.openforis.collect.persistence.jooq.tables.Data.DATA.IDX);
	}

	/**
	 * Create a detached DataRecord
	 */
	public DataRecord() {
		super(org.openforis.collect.persistence.jooq.tables.Data.DATA);
	}
}
