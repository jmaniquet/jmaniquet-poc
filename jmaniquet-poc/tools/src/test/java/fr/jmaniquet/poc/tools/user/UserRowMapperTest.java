package fr.jmaniquet.poc.tools.user;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
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

import fr.jmaniquet.poc.tools.user.UserUtils;
import fr.jmaniquet.poc.tools.user.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/tools-context-test.xml"})
@TestExecutionListeners(listeners = DbUnitTestExecutionListener.class)
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup("classpath:/selectuser-dataset.xml")
public class UserRowMapperTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private UserUtils underTest;
	
	private Long fakeId1 = 1L;
	private Long fakeId2 = 2L;
	
	private DateTime fakeDateTime = DateTime.now()
			.withDayOfMonth(4)
			.withMonthOfYear(DateTimeConstants.OCTOBER)
			.withYear(1982)
			.withHourOfDay(13)
			.withMinuteOfHour(47)
			.withSecondOfMinute(33)
			.withMillisOfSecond(0);
	
	@Test
	public void testUserRowMapperWhenBirthDateNotNull() {
		User u = underTest.findUserById(fakeId1);
		Assert.assertEquals(fakeId1, u.getId());
		Assert.assertEquals("userName1", u.getName());
		Assert.assertEquals("userGivenName1", u.getGivenName());
		Assert.assertEquals(fakeDateTime, u.getBirthDate());
	}
	
	@Test
	public void testUserRowMapperWhenBirthDateNull() {
		User u = underTest.findUserById(fakeId2);
		Assert.assertEquals(fakeId2, u.getId());
		Assert.assertEquals("userName2", u.getName());
		Assert.assertEquals("userGivenName2", u.getGivenName());
		Assert.assertNull(u.getBirthDate());
	}
}
