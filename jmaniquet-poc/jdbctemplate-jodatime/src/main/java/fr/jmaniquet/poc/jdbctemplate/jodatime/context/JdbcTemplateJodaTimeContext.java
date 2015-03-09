package fr.jmaniquet.poc.jdbctemplate.jodatime.context;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import fr.jmaniquet.poc.jdbctemplate.jodatime.JodaJdbcTemplate;
import fr.jmaniquet.poc.tools.context.ToolsContext;

@Configuration
@Import(ToolsContext.class)
@EnableTransactionManagement(order = 1)
public class JdbcTemplateJodaTimeContext {

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public JdbcTemplate customJdbcTemplate() {
		return new JodaJdbcTemplate(dataSource);
	}
}
