package fr.jmaniquet.poc.tools.context;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/tools-context-test.xml"})
public class ToolsContextTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	private ToolsContext toolsContext;
	
	@Test
	public void testToolsContextNotNull() {
		Assert.assertNotNull(toolsContext);
	}
}
