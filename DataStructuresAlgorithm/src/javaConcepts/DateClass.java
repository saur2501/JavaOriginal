package javaConcepts;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateClass {
	public static void main(String[] args) throws ParseException {
	//Create Date Object
	ZoneId zone1 = ZoneId.systemDefault();										//Asia/Calcutta
	ZoneId zone2 = ZoneId.of("Asia/Tokyo");
	LocalDate localDate = LocalDate.now();										//2017-03-10
	localDate = LocalDate.of(2017, 1, 13);										//2017-01-13
	LocalDateTime localDateTime = LocalDateTime.now();							//2017-03-10T01:53:48.733
	LocalTime localTime1 = LocalTime.now();										//01:53:00.013
	LocalTime localTime2 = LocalTime.now(zone2);
	MonthDay monthDay = MonthDay.now();											//--03-10
	
	Date date1 = new Date();													//Fri Mar 10 02:11:34 IST 2017
	OffsetTime offset = OffsetTime.now(); 										//02:08:58.957+05:30
	//Operations on date
	long minutes = ChronoUnit.MINUTES.between(localTime1, localTime2);
	ValueRange r1 = monthDay.range(ChronoField.MONTH_OF_YEAR);  
    System.out.println(r1);
    //Transformation
    localDateTime = localDate.atTime(1,50,9);
    	long epochTime=System.currentTimeMillis();  
    java.util.Date date2=new java.util.Date(epochTime);
    LocalDate monthDate = monthDay.atYear(1994);
	//Print them
    System.out.println("Local Date : " + localDate);
    System.out.println(zone1.getId());
    System.out.println(zone2.getDisplayName(TextStyle.FULL, Locale.ROOT));
    //Print Parts
    DayOfWeek dayOfWeek = DayOfWeek.from(localDate);
    System.out.println("Day : " + dayOfWeek.getValue() + "(Ordinal=" + dayOfWeek.ordinal() + ") , " + dayOfWeek.get(ChronoField.DAY_OF_WEEK));
    System.out.println("Today: " + dayOfWeek + ", Yesterday: " + dayOfWeek.minus(1) + ", Day after 3 days = " + dayOfWeek.plus(3).name());
    System.out.println("Day of year = " + localDateTime.getDayOfYear());
    System.out.println("Monthday's month = " + monthDay.get(ChronoField.MONTH_OF_YEAR));
    int hour = offset.get(ChronoField.HOUR_OF_DAY);
    int hour1 = offset.getHour();
        
    //Changing the numbers
    localDateTime.withHour(5);
    localDateTime.plusMinutes(2);
    LocalTime timeSub = localTime1.minusHours(2);
    LocalDate yesterday = localDate.minusDays(1);  
    LocalDate tomorrow = yesterday.plusDays(2);
    
    //formatting
    System.out.println("Before Formatting: " + localDateTime);
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
    String formatDateTime = localDateTime.format(format);  
    System.out.println("After Formatting: " + formatDateTime);
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  						//dd-M-yyyy hh:mm:ss; dd MMMM yyyy; dd MMMM yyyy zzzz; E, dd MMM yyyy HH:mm:ss z- E for day, z for zone, zzzz- full zone.
    String strDate= formatter.format(date1);
    System.out.println(strDate);
    DateFormat.getInstance().format(date1);
    
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");    
    Date date7 = formatter.parse("31/03/2015");  
    System.out.println("Date is: " + date7);  
    
    System.out.println("Minutes between two time zone: " + minutes);
     
    
    
      
    OffsetDateTime offsetDT = OffsetDateTime.now();   										//2017-03-10T02:14:01.380+05:30
    System.out.println(offsetDT.getDayOfMonth());
    System.out.println(offsetDT.toLocalDate());
    Clock c = Clock.systemDefaultZone();      												//SystemClock[Asia/Calcutta]
    System.out.println(c.getZone());														//Asia/Calcutta
    Clock c1 = Clock.systemUTC();  															//SystemClock[Z]
    Duration d = Duration.ofHours(5);  														//PT5H
    Clock clock = Clock.offset(c1, d);														//OffsetClock[SystemClock[Z],PT5H]
    System.out.println(clock.instant());													//Asia/Calcutta
    ZonedDateTime zone = ZonedDateTime.parse("2016-10-05T08:20:10+05:30[Asia/Kolkata]");	//2016-10-05T08:20:10+05:30[Asia/Kolkata]  
    
    ZonedDateTime zonedDateTime= ZonedDateTime.now();										//2017-03-10T02:21:02.982+05:30[Asia/Calcutta]
    ZonedDateTime p = zonedDateTime.plus(Period.ofDays(126));  
    System.out.println(p);  
    LocalDateTime  ldt = LocalDateTime.of(2017, Month.JANUARY,  19,   15,   26);  
    ZoneId  india = ZoneId.of("Asia/Kolkata");												//Asia/Kolkata
    ZonedDateTime zonedDateTime1  = ZonedDateTime.of(ldt, india);   
    System.out.println("In India Central Time Zone: " + zonedDateTime1);  
    ZoneOffset zoneOffset = ZoneOffset.UTC;  												//Z
    Temporal temp = zoneOffset.adjustInto(ZonedDateTime.now());   							//2017-03-10T02:25:26.550+05:30[Asia/Calcutta]
    ZoneOffset zoneOffsetNearIndia = ZoneOffset.ofHours(5);									//+05:00  
    ZoneOffset zoneOffsetIndia = ZoneOffset.ofHoursMinutes(5,30);							//+05:30
    boolean b1 = zone.isSupported(ChronoField.OFFSET_SECONDS);  							//true
    System.out.println(b1);  
    Year y = Year.now();  																	//2017
    Year year = Year.of(2017);  
    LocalDate l = year.atDay(123);  
    System.out.println(l);  
    System.out.println(year.length() + "\n" + year.isLeap());
    YearMonth ym = YearMonth.now(); System.out.println(ym + " is koi mil"); 				//2017-03
    String s = ym.format(DateTimeFormatter.ofPattern("MM yyyy"));							//03 2017  
    System.out.println(s);  
    long l1 = ym.get(ChronoField.YEAR);  													//2017
    System.out.println(l1);  
    YearMonth ym2 = ym.plus(Period.ofYears(2));  
    System.out.println(ym2);
    Period period = Period.ofDays(24);														//P24D
    Temporal temporal = period.addTo(LocalDate.now());  									//2017-04-03
    System.out.println(temporal); 
    Period period1 = Period.of(2017,02,16);  												//P2017Y2M16D
    System.out.println(period1.toString());
    Period period2 = period1.minus(Period.ofMonths(2));  
    System.out.println(period2);  
    Period period3 = Period.ofMonths(4); System.out.println(period3 + " we rock");			//P4M
    Duration duration = Duration.between(LocalTime.NOON,LocalTime.MAX);						//PT11H59M59.999999999S
    System.out.println(duration.get(ChronoUnit.SECONDS));
    System.out.println(duration.isNegative());  
    System.out.println(duration.get(ChronoUnit.SECONDS));
    Duration d2 = duration.minus(duration);  
    System.out.println(d2.getSeconds()); 
    Instant inst = Instant.parse("2017-02-03T10:37:30.00Z");  								//2017-02-03T10:37:30Z
    System.out.println(inst);
    System.out.println(inst.isSupported(ChronoUnit.YEARS));
    Instant instant = Instant.now();  
    System.out.println(instant);
    instant = instant.minus(Duration.ofDays(125));  
    System.out.println(instant);
    Month month = Month.valueOf("January".toUpperCase());  
    System.out.printf("For the month of %s all Sunday are:%n", month);
    System.out.println(month.getValue());  
    System.out.println(month.name() + month.length(true));  
    System.out.println(month.plus(2));
    LocalDate localdate = Year.now().atMonth(month).atDay(1).with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));
    Month mi = localdate.getMonth();  
    while (mi == month) {  
        System.out.printf("%s%n", localdate);  
        localdate = localdate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));  
        mi = localdate.getMonth();  
    }
    //Java7
    java.util.Date date=new java.util.Date();  
    System.out.println(date);  
      
    System.out.println(date2);
    long millisec=System.currentTimeMillis();  
    java.sql.Date date3=new java.sql.Date(millisec);  
    System.out.println(date3);
    String str="2015-03-31";  
    java.sql.Date date4 = java.sql.Date.valueOf(str);//converting string into sql date  
    System.out.println(date4);  
    Calendar calendar = Calendar.getInstance();															//java.util.GregorianCalendar[time=1489093527821,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="Asia/Calcutta",offset=19800000,dstSavings=0,useDaylight=false,transitions=6,lastRule=null],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2017,MONTH=2,WEEK_OF_YEAR=10,WEEK_OF_MONTH=2,DAY_OF_MONTH=10,DAY_OF_YEAR=69,DAY_OF_WEEK=6,DAY_OF_WEEK_IN_MONTH=2,AM_PM=0,HOUR=2,HOUR_OF_DAY=2,MINUTE=35,SECOND=27,MILLISECOND=821,ZONE_OFFSET=19800000,DST_OFFSET=0]  
    System.out.println("The current date is : " + calendar.getTime());  
    calendar.add(Calendar.DATE, -15);  
    System.out.println("15 days ago: " + calendar.getTime());  
    calendar.add(Calendar.MONTH, 4);
    System.out.println("At present Calendar's Day: " + calendar.get(Calendar.YEAR));
    int maximum = calendar.getMaximum(Calendar.WEEK_OF_YEAR);
    System.out.println("Maximum number of weeks in year: " + maximum);
    int minimum = calendar.getMinimum(Calendar.DAY_OF_WEEK);  
    System.out.println("Minimum number of days in week: " + minimum);
    String[] id = TimeZone.getAvailableIDs();
    System.out.println("Time zones are : " + Arrays.toString(id));
    TimeZone timezone = TimeZone.getTimeZone("Asia/Kolkata");												//Asia/Kolkata,...
    System.out.println("The Offset value of TimeZone: " +   
    		timezone.getOffset(Calendar.ZONE_OFFSET));
    System.out.println("Value of ID is: " + timezone.getID());  
    System.out.println("Display name for default time zone: "+ timezone.getDisplayName());
    
    java.util.Date currentDate = new java.util.Date();  
    System.out.println("Current Date: "+currentDate);  
    String dateToStr = DateFormat.getInstance().format(currentDate);  										//3/10/17 1:53 AM
    System.out.println("Date Format using getInstance(): "+dateToStr);
    dateToStr = DateFormat.getTimeInstance().format(currentDate);  											//1:53:48 AM
    System.out.println("Date Format using getTimeInstance(): "+dateToStr);  
    dateToStr = DateFormat.getDateInstance(DateFormat.MEDIUM).format(currentDate);  						//Mar 10, 2017
    System.out.println("Date Format using getDateInstance(DateFormat.MEDIUM): "+dateToStr);   
  }
}