package fr.jmaniquet.poc.jdbctemplate.jodatime;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import fr.jmaniquet.poc.jdbctemplate.jodatime.argsetter.JodaArgumentPreparedStatementSetter;
import fr.jmaniquet.poc.jdbctemplate.jodatime.argsetter.JodaArgumentTypePreparedStatementSetter;
import fr.jmaniquet.poc.jdbctemplate.jodatime.rowmapper.JodaColumnMapRowMapper;
import fr.jmaniquet.poc.jdbctemplate.jodatime.rowmapper.JodaSingleColumnRowMapper;

public class JodaJdbcTemplate extends JdbcTemplate {
	
	public JodaJdbcTemplate(DataSource dataSource) {
		super(dataSource);
	}
	
	protected RowMapper<Map<String, Object>> getColumnMapRowMapper() {
		return new JodaColumnMapRowMapper();
	}
	
	@Override
	protected <T> RowMapper<T> getSingleColumnRowMapper(Class<T> requiredType) {
		return new JodaSingleColumnRowMapper<T>(requiredType);
	}
	
	@Override
	protected PreparedStatementSetter newArgPreparedStatementSetter(Object[] args) {
		return new JodaArgumentPreparedStatementSetter(args);
	}
	
	@Override
	protected PreparedStatementSetter newArgTypePreparedStatementSetter(Object[] args, int[] argTypes) {
		return new JodaArgumentTypePreparedStatementSetter(args, argTypes);
	}
}
