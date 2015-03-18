package fr.jmaniquet.poc.storedcall.countusers;

import static fr.jmaniquet.poc.tools.constants.TestDataConstants.NB_USERS;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/storedcall-test-context.xml"})
@TestExecutionListeners(listeners = DbUnitTestExecutionListener.class)
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup("classpath:/selectuser-dataset.xml")
public class CountUserServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private CountUserService underTest;
	
	@Test
	public void testCountUsers() {
		Integer nbUsers = underTest.countUsers();
		Assert.assertNotNull(nbUsers);
		Assert.assertEquals(NB_USERS, nbUsers.intValue());
	}
	
	@Test
	public void testCountUsersWithIn() {
		int incrementIn = 17;
		
		Integer nbUsers = underTest.countUsersWithIn(incrementIn);
		
		Assert.assertNotNull(nbUsers);
		Assert.assertEquals(NB_USERS, nbUsers.intValue());
	}
}
