package fr.jmaniquet.poc.storedcall.core.params.timestamp;

import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;

public class TimestampAsDateTimeSqlInParameter extends SqlParameter {

	public TimestampAsDateTimeSqlInParameter(String name) {
		super(name, Types.TIMESTAMP);
	}
}
