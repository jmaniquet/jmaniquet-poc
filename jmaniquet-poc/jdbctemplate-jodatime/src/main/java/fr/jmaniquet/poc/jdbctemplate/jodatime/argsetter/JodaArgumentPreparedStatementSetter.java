package fr.jmaniquet.poc.jdbctemplate.jodatime.argsetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;

public class JodaArgumentPreparedStatementSetter extends ArgumentPreparedStatementSetter {
	
	public JodaArgumentPreparedStatementSetter(Object[] args) {
		super(args);
	}
	
	@Override
	protected void doSetValue(PreparedStatement ps, int parameterPosition, Object argValue) throws SQLException {		
		Object argValueToUse = argValue;
		if (argValue != null && argValue instanceof DateTime) {
			DateTime theValue = (DateTime) argValue;
			argValueToUse = theValue.toDate();
		}
		super.doSetValue(ps, parameterPosition, argValueToUse);
	}
}
