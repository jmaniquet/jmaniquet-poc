package fr.jmaniquet.poc.storedcall.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

public final class TestDataConstants {
	
	public static final Long USER1_ID = 1L;
	public static final String USER1_NAME = "userName1";
	public static final String USER1_GIVENNAME = "userGivenName1";
	
	public static final Long USER2_ID = 2L;
	public static final String USER2_NAME = "userName2";
	public static final String USER2_GIVENNAME = "userGivenName2";

	public static final DateTime USER1_BIRTHDATE = DateTime.now()
			.withDayOfMonth(4)
			.withMonthOfYear(DateTimeConstants.OCTOBER)
			.withYear(1982)
			.withHourOfDay(13)
			.withMinuteOfHour(47)
			.withSecondOfMinute(33)
			.withMillisOfSecond(0);
	
	private TestDataConstants() {}
}
