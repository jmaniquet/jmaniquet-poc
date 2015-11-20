package fr.jmaniquet.poc.storedcall.test.config;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

@Configuration
public class StoredCallEmbaddedDataBaseConfig {

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder dsb = new EmbeddedDatabaseBuilder()
				.setType(HSQL)
				.addScript("classpath:schema.sql")
				.addScript("classpath:proc.sql")
				.setSeparator("/;");
		return dsb.build();
	}
}
