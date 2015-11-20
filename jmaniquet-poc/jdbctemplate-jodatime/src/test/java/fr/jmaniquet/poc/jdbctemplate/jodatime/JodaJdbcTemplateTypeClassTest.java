package fr.jmaniquet.poc.jdbctemplate.jodatime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jmaniquet.poc.tools.test.context.ToolsEmbaddedDataBaseContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		ToolsEmbaddedDataBaseContext.class,
		JdbcTemplateJodaTimeContext.class}
)
public class JodaJdbcTemplateTypeClassTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	@Qualifier(BeanConstants.CUSTOM_JDBCTEMPLATE)
	private JdbcTemplate customJdbcTemplate;
	
	@Autowired
	@Qualifier(BeanConstants.CLASSIC_JDBCTEMPLATE)
	private JdbcTemplate classicJdbcTemplate;
	
	@Test
	public void testClassicClass() {
		Assert.assertNotNull(classicJdbcTemplate);
		Assert.assertFalse(classicJdbcTemplate instanceof JodaJdbcTemplate);
	}
	
	@Test
	public void testCustomisationClass() {
		Assert.assertNotNull(customJdbcTemplate);
		Assert.assertTrue(customJdbcTemplate instanceof JodaJdbcTemplate);
	}
}
