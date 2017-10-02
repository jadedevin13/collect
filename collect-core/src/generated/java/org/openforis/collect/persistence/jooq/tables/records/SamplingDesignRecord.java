/**
 * This class is generated by jOOQ
 */
package org.openforis.collect.persistence.jooq.tables.records;


import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.TableRecordImpl;
import org.openforis.collect.persistence.jooq.tables.SamplingDesign;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SamplingDesignRecord extends TableRecordImpl<SamplingDesignRecord> implements Record4<Integer, String, String, String> {

	private static final long serialVersionUID = 300723854;

	/**
	 * Setter for <code>collect.sampling_design.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>collect.sampling_design.id</code>.
	 */
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>collect.sampling_design.cluster</code>.
	 */
	public void setCluster(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>collect.sampling_design.cluster</code>.
	 */
	public String getCluster() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>collect.sampling_design.plot</code>.
	 */
	public void setPlot(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>collect.sampling_design.plot</code>.
	 */
	public String getPlot() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>collect.sampling_design.coordinate</code>.
	 */
	public void setCoordinate(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>collect.sampling_design.coordinate</code>.
	 */
	public String getCoordinate() {
		return (String) getValue(3);
	}

	// -------------------------------------------------------------------------
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<Integer, String, String, String> fieldsRow() {
		return (Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<Integer, String, String, String> valuesRow() {
		return (Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return SamplingDesign.SAMPLING_DESIGN.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return SamplingDesign.SAMPLING_DESIGN.CLUSTER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return SamplingDesign.SAMPLING_DESIGN.PLOT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return SamplingDesign.SAMPLING_DESIGN.COORDINATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getCluster();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getPlot();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value4() {
		return getCoordinate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord value2(String value) {
		setCluster(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord value3(String value) {
		setPlot(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord value4(String value) {
		setCoordinate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord values(Integer value1, String value2, String value3, String value4) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached SamplingDesignRecord
	 */
	public SamplingDesignRecord() {
		super(SamplingDesign.SAMPLING_DESIGN);
	}

	/**
	 * Create a detached, initialised SamplingDesignRecord
	 */
	public SamplingDesignRecord(Integer id, String cluster, String plot, String coordinate) {
		super(SamplingDesign.SAMPLING_DESIGN);

		setValue(0, id);
		setValue(1, cluster);
		setValue(2, plot);
		setValue(3, coordinate);
	}
}
