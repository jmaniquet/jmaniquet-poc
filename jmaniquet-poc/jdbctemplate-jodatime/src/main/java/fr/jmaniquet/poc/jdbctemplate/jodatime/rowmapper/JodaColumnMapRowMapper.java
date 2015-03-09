package fr.jmaniquet.poc.jdbctemplate.jodatime.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.ColumnMapRowMapper;

public class JodaColumnMapRowMapper extends ColumnMapRowMapper {

	@Override
	protected Object getColumnValue(ResultSet rs, int index) throws SQLException {
		Object resultSetValue = super.getColumnValue(rs, index);
		if (resultSetValue == null) {
			return null;
		}
		
		if (!Date.class.isAssignableFrom(resultSetValue.getClass())) {
			return resultSetValue;
		}
		
		Date utilDate = (Date) resultSetValue;
		return new DateTime(utilDate.getTime());
	}
}
