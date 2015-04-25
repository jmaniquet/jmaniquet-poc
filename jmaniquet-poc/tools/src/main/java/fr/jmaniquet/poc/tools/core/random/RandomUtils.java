package fr.jmaniquet.poc.tools.core.random;

import java.util.Random;

import org.joda.time.DateTime;

public final class RandomUtils {

	private RandomUtils() {}
	
	public static DateTime randomDate() {

		int randomYear = randomInt(1970, 2014);
		int randomDayOfYear = randomInt(1, 365);
		int randomHourOfDay = randomInt(0, 23);
		int randomMinuteOfHour = randomInt(0, 59);
		int randomSecondOfMinute = randomInt(0, 59);

		DateTime randomDateTime = new DateTime()
			.withYear(randomYear)
			.withDayOfYear(randomDayOfYear)
			.withHourOfDay(randomHourOfDay)
			.withMinuteOfHour(randomMinuteOfHour)
			.withSecondOfMinute(randomSecondOfMinute)
			.withMillisOfSecond(0);
		
		return randomDateTime;
	}
	
	public static long randomId() {
		long range = 9223372036854775807L;
		Random r = new Random();
		long number = (long)(r.nextDouble()*range);
		return Math.abs(number);
	}
	
	private static int randomInt(int min, int max) {
		Random rand = new Random();
		int randomInt = rand.nextInt(max - min + 1) + min;
		return randomInt;
	}
}
