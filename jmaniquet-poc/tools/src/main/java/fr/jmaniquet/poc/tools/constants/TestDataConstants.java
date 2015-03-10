package fr.jmaniquet.poc.tools.constants;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

public final class TestDataConstants {
	
	public static final int NB_USERS = 3;
	
	public static final Long USER1_ID = 1L;
	public static final String USER1_NAME = "userName1";
	public static final String USER1_GIVENNAME = "userGivenName1";
	public static final DateTime USER1_BIRTHDATE = DateTime.now()
			.withDayOfMonth(4)
			.withMonthOfYear(DateTimeConstants.OCTOBER)
			.withYear(1982)
			.withHourOfDay(13)
			.withMinuteOfHour(47)
			.withSecondOfMinute(33)
			.withMillisOfSecond(0);
	
	public static final Long USER2_ID = 2L;
	public static final String USER2_NAME = "userName2";
	public static final String USER2_GIVENNAME = "userGivenName2";

	public static final Long USER3_ID = 3L;
	public static final String USER3_NAME = "userName3";
	public static final String USER3_GIVENNAME = "userGivenName3";
	public static final DateTime USER3_BIRTHDATE = DateTime.now()
			.withDayOfMonth(22)
			.withMonthOfYear(DateTimeConstants.MAY)
			.withYear(1984)
			.withHourOfDay(10)
			.withMinuteOfHour(23)
			.withSecondOfMinute(37)
			.withMillisOfSecond(1);
	
	private TestDataConstants() {}
}
