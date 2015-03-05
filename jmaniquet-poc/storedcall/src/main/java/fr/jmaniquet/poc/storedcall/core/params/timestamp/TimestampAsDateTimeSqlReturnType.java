package fr.jmaniquet.poc.storedcall.core.params.timestamp;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.SqlReturnType;

public class TimestampAsDateTimeSqlReturnType implements SqlReturnType {

	@Override
	public Object getTypeValue(CallableStatement cs, int paramIndex, int sqlType, String typeName) throws SQLException {
		Timestamp timestamp = cs.getTimestamp(paramIndex);
		if (timestamp == null) {
			return null;	
		}
		return new DateTime(timestamp.getTime());
	}

}
