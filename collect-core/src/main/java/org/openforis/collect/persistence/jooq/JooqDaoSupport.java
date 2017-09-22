package org.openforis.collect.persistence.jooq;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jooq.Query;
import org.jooq.exception.DataAccessException;

/**
 * @author G. Miceli
 * @author M. Togna
 * @author S. Ricci
 */
public abstract class JooqDaoSupport {
	protected final Log log = LogFactory.getLog(getClass());

	private static final String CONSTRAINT_VIOLATION_CODE = "23";
	private static final String CONSTRAINT_VIOLATION_MESSAGE = "constraint violation";
	
	protected CollectDSLContext dsl;
	   
	public static boolean isConstraintViolation(DataAccessException e) {
		Throwable cause = e.getCause();
		if (! (cause instanceof SQLException)) {
			return false;
		}
		String sqlState = ((SQLException) cause).getSQLState();
		if (sqlState == null) {
			return StringUtils.containsIgnoreCase(cause.getMessage(), CONSTRAINT_VIOLATION_MESSAGE);
		} else {
			return sqlState.startsWith(CONSTRAINT_VIOLATION_CODE);
		}
	}
	
	protected Log getLog() {
		return log;
	}
	
	// TODO Move to MappingJooqFactory
	protected static Timestamp toTimestamp(Date date) {
		if ( date == null ) {
			return null;
		} else {
			return new Timestamp(date.getTime());
		}
	}

	protected CollectDSLContext dsl() {
		return dsl;
	}
	
	public void setDsl(CollectDSLContext dsl) {
		this.dsl = dsl;
	}
	
	public static class CollectStoreQuery {
		
		private Query internalQuery;

		public CollectStoreQuery(Query internalQuery) {
			super();
			this.internalQuery = internalQuery;
		}
		
		public Query getInternalQuery() {
			return internalQuery;
		}
	}
	
	public static class CollectStoreQueryBuffer {
		
		private static final int DEFAULT_BATCH_SIZE = 100;
		
		private int bufferSize;
		private List<CollectStoreQuery> buffer;
		
		public CollectStoreQueryBuffer() {
			this(DEFAULT_BATCH_SIZE);
		}
		
		public CollectStoreQueryBuffer(int size) {
			this.bufferSize = size;
			this.buffer = new ArrayList<CollectStoreQuery>(size);
		}
		
		public void append(CollectStoreQuery query) {
			buffer.add(query);
			if (buffer.size() == bufferSize) {
				flush();
			}
		}

		public void appendAll(List<CollectStoreQuery> queries) {
			for (CollectStoreQuery query : queries) {
				append(query);
			}
		}
		
		public int size() {
			return buffer.size();
		}
		
		public List<CollectStoreQuery> flush() {
			List<CollectStoreQuery> current = new ArrayList<CollectStoreQuery>(buffer);
			buffer.clear();
			return current;
		}

	}
}
