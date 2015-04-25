package fr.jmaniquet.poc.tools.core.user;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.junit.Assert;
import org.junit.Test;

import fr.jmaniquet.poc.tools.core.user.User;
import fr.jmaniquet.poc.tools.core.user.UserBuilder;

public class UserBuilderTest {

	@Test
	public void testBirthDate() {
		DateTime someBirthDate = DateTime.now()
				.withDayOfMonth(4)
				.withMonthOfYear(DateTimeConstants.OCTOBER)
				.withYear(1982)
				.withHourOfDay(13)
				.withMinuteOfHour(47)
				.withSecondOfMinute(33)
				.withMillisOfSecond(0);
		User u = UserBuilder.builder().birthDate(someBirthDate).build();
		Assert.assertEquals(someBirthDate, u.getBirthDate());
	}
	
	@Test
	public void testGivenName() {
		String someGivenName = "someGivenName";
		User u = UserBuilder.builder().givenName(someGivenName).build();
		Assert.assertEquals(someGivenName, u.getGivenName());
	}
	
	@Test
	public void testId() {
		Long someId = 6541L;
		User u = UserBuilder.builder().id(someId).build();
		Assert.assertEquals(someId, u.getId());
	}
	
	@Test
	public void testName() {
		String someName = "someName";
		User u = UserBuilder.builder().name(someName).build();
		Assert.assertEquals(someName, u.getName());
	}
	
	@Test
	public void testNoProperty() {
		User u = UserBuilder.builder().build();
		Assert.assertNull(u.getId());
		Assert.assertNull(u.getName());
		Assert.assertNull(u.getGivenName());
		Assert.assertNull(u.getBirthDate());
	}
}
