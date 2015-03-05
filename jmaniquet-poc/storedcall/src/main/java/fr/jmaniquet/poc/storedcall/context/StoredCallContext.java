package fr.jmaniquet.poc.storedcall.context;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.ResourceTransactionManager;

import fr.jmaniquet.poc.tools.context.ToolsContext;

@Configuration
@Import(ToolsContext.class)
@EnableTransactionManagement(order = 1)
@ComponentScan("fr.jmaniquet.poc.storedcall")
public class StoredCallContext {

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public ResourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
}
