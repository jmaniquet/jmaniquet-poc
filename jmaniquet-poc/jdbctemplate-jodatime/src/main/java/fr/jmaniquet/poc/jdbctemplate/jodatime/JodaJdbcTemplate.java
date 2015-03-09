package fr.jmaniquet.poc.jdbctemplate.jodatime;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class JodaJdbcTemplate extends JdbcTemplate {
	
	public JodaJdbcTemplate(DataSource dataSource) {
		super(dataSource);
	}
}
