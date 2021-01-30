package me.kyeong.java8to11.timeapi;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TimeApiRunner {

	public static void main(String[] args) {
		// 기계용 시간 API인 Instant -> 메소드 시간을 비교하거나할 때 유용
		Instant nowIns = Instant.now();
		System.out.println(nowIns); // 기준시 (UTC = GMT)
		
		ZoneId zoneId = ZoneId.systemDefault();
		System.out.println(zoneId);
		
		ZonedDateTime atZone = nowIns.atZone(zoneId);
		System.out.println(atZone);
		
		LocalDateTime now = LocalDateTime.now(); // system의 zone을 참고하여 Locale한 시간을 반환함.
		
		LocalDateTime.of(1995, Month.APRIL, 27, 0, 0, 0);
		
		ZonedDateTime nowInUTC = ZonedDateTime.now(ZoneId.of("UTC"));
		System.out.println(nowInUTC);
		
		ZonedDateTime zonedDateTime = nowIns.atZone(ZoneId.of("Asia/Seoul"));
		System.out.println(zonedDateTime);
		zonedDateTime.toInstant(); // 서로서로 변환할 수 있음
		
		// 기간을 표현하는 방법
		/*
		 * 인간 친화적 -> Period
		 * 기계 친화적 -> Duration
		 */
		LocalDate today = LocalDate.now();
		LocalDate thisYearBirthdaty = LocalDate.of(2021, Month.APRIL, 27);
		System.out.println(today);
		System.out.println(thisYearBirthdaty);
		
		Period period = Period.between(today, thisYearBirthdaty);
		
		System.out.println();
		System.out.println(period.getMonths() + "-" + period.getDays());
		Period until = today.until(thisYearBirthdaty);
		System.out.println(until.get(ChronoUnit.DAYS));
		
		
		Instant nowIns2 = Instant.now();
		Instant plus = nowIns2.plus(10, ChronoUnit.SECONDS);
		Duration between = Duration.between(nowIns2, plus);
		System.out.println(between.getSeconds());
		
		// formatter
		LocalDateTime nowLDT = LocalDateTime.now();
		DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		System.out.println(now.format(MMddyyyy));
		// parsing
		LocalDate parse = LocalDate.parse("04/27/1995", MMddyyyy);
		System.out.println(parse);
		
		// 각 예전 API와 Java8 API의 Date 클래스들은 서로 서로의 타입으로 변환할 수 있다.
		Date date = new Date();
		Instant instant = date.toInstant();
		Date from = Date.from(instant);
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar()
				;
		ZonedDateTime dateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());
		GregorianCalendar from2 = GregorianCalendar.from(dateTime);
		
		ZoneId zoneId2 = TimeZone.getTimeZone("PST").toZoneId();
		TimeZone 레거시 = TimeZone.getTimeZone(zoneId2);
		
		// 정리: java8의 date api들은 imutable하게 새로운 인스턴스를 생성한다. 주의해서 사용할 것
	}
}
