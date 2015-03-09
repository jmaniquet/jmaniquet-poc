package fr.jmaniquet.poc.jdbctemplate.jodatime.argsetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.ArgumentTypePreparedStatementSetter;

public class JodaArgumentTypePreparedStatementSetter extends ArgumentTypePreparedStatementSetter {
	
	public JodaArgumentTypePreparedStatementSetter(Object[] args, int[] argTypes) {
		super(args, argTypes);
	}
	
	@Override
	protected void doSetValue(PreparedStatement ps, int parameterPosition, int argType, Object argValue) throws SQLException {
		Object argValueToUse = argValue;
		if (argValue != null && argValue instanceof DateTime) {
			DateTime theValue = (DateTime) argValue;
			argValueToUse = theValue.toDate();
		}
		super.doSetValue(ps, parameterPosition, argType, argValueToUse);
	}
}
