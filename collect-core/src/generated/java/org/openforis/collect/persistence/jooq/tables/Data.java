/**
 * This class is generated by jOOQ
 */
package org.openforis.collect.persistence.jooq.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "2.0.1"},
                            comments = "This class is generated by jOOQ")
public class Data extends org.jooq.impl.UpdatableTableImpl<org.openforis.collect.persistence.jooq.tables.records.DataRecord> {

	private static final long serialVersionUID = 2085132063;

	/**
	 * The singleton instance of data
	 */
	public static final org.openforis.collect.persistence.jooq.tables.Data DATA = new org.openforis.collect.persistence.jooq.tables.Data();

	/**
	 * The class holding records for this type
	 */
	private static final java.lang.Class<org.openforis.collect.persistence.jooq.tables.records.DataRecord> __RECORD_TYPE = org.openforis.collect.persistence.jooq.tables.records.DataRecord.class;

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.openforis.collect.persistence.jooq.tables.records.DataRecord> getRecordType() {
		return __RECORD_TYPE;
	}

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.DataRecord, java.lang.Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [collect.data.record_id]
	 * REFERENCES record [collect.record.id]
	 * </pre></code>
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.DataRecord, java.lang.Integer> RECORD_ID = createField("record_id", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [collect.data.definition_id]
	 * REFERENCES schema_definition [collect.schema_definition.id]
	 * </pre></code>
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.DataRecord, java.lang.Integer> DEFINITION_ID = createField("definition_id", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.DataRecord, java.math.BigDecimal> NUMBER1 = createField("number1", org.jooq.impl.SQLDataType.NUMERIC, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.DataRecord, java.math.BigDecimal> NUMBER2 = createField("number2", org.jooq.impl.SQLDataType.NUMERIC, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.DataRecord, java.math.BigDecimal> NUMBER3 = createField("number3", org.jooq.impl.SQLDataType.NUMERIC, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.DataRecord, java.lang.String> TEXT1 = createField("text1", org.jooq.impl.SQLDataType.VARCHAR, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.DataRecord, java.lang.String> TEXT2 = createField("text2", org.jooq.impl.SQLDataType.VARCHAR, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.DataRecord, java.lang.String> TEXT3 = createField("text3", org.jooq.impl.SQLDataType.VARCHAR, this);

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [collect.data.taxon_id]
	 * REFERENCES taxon [collect.taxon.id]
	 * </pre></code>
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.DataRecord, java.lang.Integer> TAXON_ID = createField("taxon_id", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.DataRecord, java.lang.String> REMARKS = createField("remarks", org.jooq.impl.SQLDataType.VARCHAR, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.DataRecord, java.lang.String> SYMBOL = createField("symbol", org.jooq.impl.SQLDataType.CHAR, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.DataRecord, java.lang.String> STATE = createField("state", org.jooq.impl.SQLDataType.CHAR, this);

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [collect.data.parent_id]
	 * REFERENCES data [collect.data.id]
	 * </pre></code>
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.DataRecord, java.lang.Integer> PARENT_ID = createField("parent_id", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.DataRecord, java.lang.Integer> IDX = createField("idx", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * No further instances allowed
	 */
	private Data() {
		super("data", org.openforis.collect.persistence.jooq.Collect.COLLECT);
	}

	/**
	 * No further instances allowed
	 */
	private Data(java.lang.String alias) {
		super(alias, org.openforis.collect.persistence.jooq.Collect.COLLECT, org.openforis.collect.persistence.jooq.tables.Data.DATA);
	}

	@Override
	public org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.DataRecord> getMainKey() {
		return org.openforis.collect.persistence.jooq.Keys.data_pkey;
	}

	@Override
	@SuppressWarnings("unchecked")
	public java.util.List<org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.DataRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.DataRecord>>asList(org.openforis.collect.persistence.jooq.Keys.data_pkey);
	}

	@Override
	@SuppressWarnings("unchecked")
	public java.util.List<org.jooq.ForeignKey<org.openforis.collect.persistence.jooq.tables.records.DataRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.openforis.collect.persistence.jooq.tables.records.DataRecord, ?>>asList(org.openforis.collect.persistence.jooq.Keys.data__FK_data_record, org.openforis.collect.persistence.jooq.Keys.data__FK_data_schema_definition, org.openforis.collect.persistence.jooq.Keys.data__FK_data_taxon, org.openforis.collect.persistence.jooq.Keys.data__FK_data_parent);
	}

	@Override
	public org.openforis.collect.persistence.jooq.tables.Data as(java.lang.String alias) {
		return new org.openforis.collect.persistence.jooq.tables.Data(alias);
	}
}
