package fr.jmaniquet.poc.tools.user;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.junit.Assert;
import org.junit.Test;

public class UserUtilsTest {
	
	private UserUtils underTest = new UserUtilsImpl();
	
	private DateTime fakeDateTime = DateTime.now()
		.withDayOfMonth(4)
		.withMonthOfYear(DateTimeConstants.OCTOBER)
		.withYear(1982)
		.withHourOfDay(13)
		.withMinuteOfHour(47)
		.withSecondOfMinute(33)
		.withMillisOfSecond(0);
	
	@Test
	public void testAssertEqualsWhenBirthDateEquals() {
		User u1 = UserBuilder.builder().birthDate(fakeDateTime).build();
		User u2 = UserBuilder.builder().birthDate(new DateTime(fakeDateTime.getMillis())).build();
		
		underTest.assertEquals(u1, u2);
	}
	
	@Test(expected = AssertionError.class)
	public void testAssertEqualsWhenBirthDateIsNullOnOneSide() {
		User u1 = UserBuilder.builder().birthDate(fakeDateTime).build();
		User u2 = UserBuilder.builder().birthDate(null).build();
		
		underTest.assertEquals(u1, u2);
	}
	
	@Test(expected = AssertionError.class)
	public void testAssertEqualsWhenBirthDateNotEquals() {
		User u1 = UserBuilder.builder().birthDate(fakeDateTime).build();
		User u2 = UserBuilder.builder().birthDate(fakeDateTime.plus(1)).build();
		
		underTest.assertEquals(u1, u2);
	}
	
	@Test
	public void testAssertEqualsWhenBirthDateSame() {
		User u1 = UserBuilder.builder().birthDate(fakeDateTime).build();
		User u2 = UserBuilder.builder().birthDate(fakeDateTime).build();
		
		underTest.assertEquals(u1, u2);
	}
	
	@Test
	public void testAssertEqualsWhenGivenNameEquals() {
		User u1 = UserBuilder.builder().givenName("someGivenName").build();
		User u2 = UserBuilder.builder().givenName("someGivenName").build();
		
		underTest.assertEquals(u1, u2);
	}
	
	@Test(expected = AssertionError.class)
	public void testAssertEqualsWhenGivenNameIsNullOnOneSide() {
		User u1 = UserBuilder.builder().givenName("someGivenName").build();
		User u2 = UserBuilder.builder().givenName(null).build();
		
		underTest.assertEquals(u1, u2);
	}
	
	@Test(expected = AssertionError.class)
	public void testAssertEqualsWhenGivenNameNotEquals() {
		User u1 = UserBuilder.builder().givenName("someGivenName1").build();
		User u2 = UserBuilder.builder().givenName("someGivenName2").build();
		
		underTest.assertEquals(u1, u2);
	}
	
	@Test
	public void testAssertEqualsWhenGivenNameSame() {
		String givenName = "someGivenName";
		User u1 = UserBuilder.builder().givenName(givenName).build();
		User u2 = UserBuilder.builder().givenName(givenName).build();
		
		underTest.assertEquals(u1, u2);
	}
	
	@Test
	public void testAssertEqualsWhenIdEquals() {
		Long id1 = new Long(1L);
		Long id2 = new Long(1L);
		Assert.assertNotSame(id1, id2);
		
		User u1 = UserBuilder.builder().id(id1).build();
		User u2 = UserBuilder.builder().id(id2).build();
		
		underTest.assertEquals(u1, u2);
	}
	
	@Test(expected = AssertionError.class)
	public void testAssertEqualsWhenIdIsNullOnOneSide() {
		User u1 = UserBuilder.builder().id(1L).build();
		User u2 = UserBuilder.builder().givenName(null).build();
		
		underTest.assertEquals(u1, u2);
	}
	
	@Test(expected = AssertionError.class)
	public void testAssertEqualsWhenIdNotEquals() {
		User u1 = UserBuilder.builder().id(1L).build();
		User u2 = UserBuilder.builder().id(2L).build();
		
		underTest.assertEquals(u1, u2);
	}
	
	@Test
	public void testAssertEqualsWhenIdSame() {
		Long id = 1L;
		User u1 = UserBuilder.builder().id(id).build();
		User u2 = UserBuilder.builder().id(id).build();
		
		underTest.assertEquals(u1, u2);
	}
	
	@Test
	public void testAssertEqualsWhenNameEquals() {
		User u1 = UserBuilder.builder().name("name").build();
		User u2 = UserBuilder.builder().name("name").build();
		
		underTest.assertEquals(u1, u2);
	}
	
	@Test(expected = AssertionError.class)
	public void testAssertEqualsWhenNameIsNullOnOneSide() {
		User u1 = UserBuilder.builder().name("name").build();
		User u2 = UserBuilder.builder().name(null).build();
		
		underTest.assertEquals(u1, u2);
	}
	
	@Test(expected = AssertionError.class)
	public void testAssertEqualsWhenNameNotEquals() {
		User u1 = UserBuilder.builder().name("name1").build();
		User u2 = UserBuilder.builder().name("name2").build();
		
		underTest.assertEquals(u1, u2);
	}
	
	@Test
	public void testAssertEqualsWhenNameSame() {
		String name = "someName";
		User u1 = UserBuilder.builder().name(name).build();
		User u2 = UserBuilder.builder().name(name).build();
		
		underTest.assertEquals(u1, u2);
	}
}
