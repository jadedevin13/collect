/**
 * This class is generated by jOOQ
 */
package org.openforis.collect.persistence.jooq.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.2" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OfcTaxonRecord extends org.jooq.impl.UpdatableRecordImpl<org.openforis.collect.persistence.jooq.tables.records.OfcTaxonRecord> implements org.jooq.Record8<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer> {

	private static final long serialVersionUID = -2066168941;

	/**
	 * Setter for <code>collect.ofc_taxon.id</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>collect.ofc_taxon.id</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>collect.ofc_taxon.taxon_id</code>.
	 */
	public void setTaxonId(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>collect.ofc_taxon.taxon_id</code>.
	 */
	public java.lang.Integer getTaxonId() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>collect.ofc_taxon.code</code>.
	 */
	public void setCode(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>collect.ofc_taxon.code</code>.
	 */
	public java.lang.String getCode() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>collect.ofc_taxon.scientific_name</code>.
	 */
	public void setScientificName(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>collect.ofc_taxon.scientific_name</code>.
	 */
	public java.lang.String getScientificName() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>collect.ofc_taxon.taxon_rank</code>.
	 */
	public void setTaxonRank(java.lang.String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>collect.ofc_taxon.taxon_rank</code>.
	 */
	public java.lang.String getTaxonRank() {
		return (java.lang.String) getValue(4);
	}

	/**
	 * Setter for <code>collect.ofc_taxon.taxonomy_id</code>.
	 */
	public void setTaxonomyId(java.lang.Integer value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>collect.ofc_taxon.taxonomy_id</code>.
	 */
	public java.lang.Integer getTaxonomyId() {
		return (java.lang.Integer) getValue(5);
	}

	/**
	 * Setter for <code>collect.ofc_taxon.step</code>.
	 */
	public void setStep(java.lang.Integer value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>collect.ofc_taxon.step</code>.
	 */
	public java.lang.Integer getStep() {
		return (java.lang.Integer) getValue(6);
	}

	/**
	 * Setter for <code>collect.ofc_taxon.parent_id</code>.
	 */
	public void setParentId(java.lang.Integer value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>collect.ofc_taxon.parent_id</code>.
	 */
	public java.lang.Integer getParentId() {
		return (java.lang.Integer) getValue(7);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Integer> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record8 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row8<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer> fieldsRow() {
		return (org.jooq.Row8) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row8<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer> valuesRow() {
		return (org.jooq.Row8) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON.TAXON_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON.CODE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON.SCIENTIFIC_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON.TAXON_RANK;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field6() {
		return org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON.TAXONOMY_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field7() {
		return org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON.STEP;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field8() {
		return org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON.PARENT_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value2() {
		return getTaxonId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getScientificName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getTaxonRank();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value6() {
		return getTaxonomyId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value7() {
		return getStep();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value8() {
		return getParentId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcTaxonRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcTaxonRecord value2(java.lang.Integer value) {
		setTaxonId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcTaxonRecord value3(java.lang.String value) {
		setCode(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcTaxonRecord value4(java.lang.String value) {
		setScientificName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcTaxonRecord value5(java.lang.String value) {
		setTaxonRank(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcTaxonRecord value6(java.lang.Integer value) {
		setTaxonomyId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcTaxonRecord value7(java.lang.Integer value) {
		setStep(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcTaxonRecord value8(java.lang.Integer value) {
		setParentId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcTaxonRecord values(java.lang.Integer value1, java.lang.Integer value2, java.lang.String value3, java.lang.String value4, java.lang.String value5, java.lang.Integer value6, java.lang.Integer value7, java.lang.Integer value8) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached OfcTaxonRecord
	 */
	public OfcTaxonRecord() {
		super(org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON);
	}

	/**
	 * Create a detached, initialised OfcTaxonRecord
	 */
	public OfcTaxonRecord(java.lang.Integer id, java.lang.Integer taxonId, java.lang.String code, java.lang.String scientificName, java.lang.String taxonRank, java.lang.Integer taxonomyId, java.lang.Integer step, java.lang.Integer parentId) {
		super(org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON);

		setValue(0, id);
		setValue(1, taxonId);
		setValue(2, code);
		setValue(3, scientificName);
		setValue(4, taxonRank);
		setValue(5, taxonomyId);
		setValue(6, step);
		setValue(7, parentId);
	}
}
