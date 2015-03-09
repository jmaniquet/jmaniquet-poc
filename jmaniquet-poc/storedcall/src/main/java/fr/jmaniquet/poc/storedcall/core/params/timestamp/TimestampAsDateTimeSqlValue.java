package fr.jmaniquet.poc.storedcall.core.params.timestamp;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.jdbc.support.SqlValue;

public class TimestampAsDateTimeSqlValue implements SqlValue {

	private DateTime value;
	
	public TimestampAsDateTimeSqlValue(DateTime pValue) {
		super();
		this.value = pValue;
	}

	@Override
	public void setValue(PreparedStatement ps, int paramIndex) throws SQLException {
		Object valueToUse = (value != null ? value.toDate() : null);
		StatementCreatorUtils.setParameterValue(ps, paramIndex, Types.TIMESTAMP, valueToUse);
		
	}

	@Override
	public void cleanup() {}
}
