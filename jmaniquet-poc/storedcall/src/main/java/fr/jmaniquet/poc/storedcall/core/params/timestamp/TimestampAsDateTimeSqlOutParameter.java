package fr.jmaniquet.poc.storedcall.core.params.timestamp;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;

public class TimestampAsDateTimeSqlOutParameter extends SqlOutParameter {

	public TimestampAsDateTimeSqlOutParameter(String name) {
		super(name, Types.TIMESTAMP, null, new TimestampAsDateTimeSqlReturnType());
	}
}
