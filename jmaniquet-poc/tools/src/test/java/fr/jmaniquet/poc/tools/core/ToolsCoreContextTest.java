package fr.jmaniquet.poc.tools.core;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jmaniquet.poc.tools.core.ToolsCoreContext;
import fr.jmaniquet.poc.tools.test.context.ToolsEmbaddedDataBaseContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		ToolsEmbaddedDataBaseContext.class,
		ToolsCoreContext.class}
)
public class ToolsCoreContextTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ToolsCoreContext toolsContext;
	
	@Test
	public void testDataSourceNotNull() {
		Assert.assertNotNull(dataSource);
	}
	
	@Test
	public void testJdbcTemplateNotNull() {
		Assert.assertNotNull(jdbcTemplate);
	}
	
	@Test
	public void testToolsContextNotNull() {
		Assert.assertNotNull(toolsContext);
	}
}
