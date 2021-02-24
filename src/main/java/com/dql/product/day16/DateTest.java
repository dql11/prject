package com.dql.product.day16;

import sun.java2d.pipe.SpanShapeRenderer;

import java.security.acl.LastOwnerException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;

public class DateTest {

    private static ThreadLocal threadSafeSimpleDateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    private static DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
            .appendValue(ChronoField.YEAR) //年
            .appendLiteral("/")
            .appendValue(MONTH_OF_YEAR) //月
            .appendLiteral("/")
            .appendValue(DAY_OF_MONTH) //日
            .appendLiteral(" ")
            .appendValue(ChronoField.HOUR_OF_DAY) //时
            .appendLiteral(":")
            .appendValue(ChronoField.MINUTE_OF_HOUR) //分
            .appendLiteral(":")
            .appendValue(ChronoField.SECOND_OF_MINUTE) //秒
            .appendLiteral(".")
            .appendValue(ChronoField.MILLI_OF_SECOND) //毫秒
            .toFormatter();
    public static void main(String[] args) throws ParseException {
//        Date date = new Date(2019, 12, 31, 11, 12, 13);
//        System.out.println(date);

//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2019, 11, 31, 11, 12, 13);
//        System.out.println(calendar.getTime());
//        Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
//        calendar2.set(2019, Calendar.DECEMBER, 31, 11, 12, 13);
//        System.out.println(calendar2.getTime());


//        System.out.println(new Date(0));
//        System.out.println(TimeZone.getDefault().getID() + ":" + TimeZone.getDefault().getRawOffset()/3600000);

//        String stringDate = "2020-01-02 22:00:00";
//        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        //默认时区解析时间表示
//        Date date1 = inputFormat.parse(stringDate);
//        System.out.println(date1 + ":" + date1.getTime());
//        //纽约时区解析时间表示
//        inputFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
//        Date date2 = inputFormat.parse(stringDate);
//        System.out.println(date2 + ":" + date2.getTime());


//        String stringDate = "2020-01-02 22:00:00";
//        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////同一Date
//        Date date = inputFormat.parse(stringDate);
////默认时区格式化输出：
//        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss Z]").format(date));
////纽约时区格式化输出
//        TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
//        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss Z]").format(date));


////一个时间表示
//        String stringDate = "2020-01-02 22:00:00";
////初始化三个时区
//        ZoneId timeZoneSH = ZoneId.of("Asia/Shanghai");
//        ZoneId timeZoneNY = ZoneId.of("America/New_York");
//        ZoneId timeZoneJST = ZoneOffset.ofHours(9);
////格式化器
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        ZonedDateTime date = ZonedDateTime.of(LocalDateTime.parse(stringDate, dateTimeFormatter), timeZoneJST);
////使用DateTimeFormatter格式化时间，可以通过withZone方法直接设置格式化使用的时区
//        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
//        System.out.println(timeZoneSH.getId() + outputFormat.withZone(timeZoneSH).format(date));
//        System.out.println(timeZoneNY.getId() + outputFormat.withZone(timeZoneNY).format(date));
//        System.out.println(timeZoneJST.getId() + outputFormat.withZone(timeZoneJST).format(date));



//        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
//        System.out.println("defaultLocale:" + Locale.getDefault());
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2019, Calendar.DECEMBER, 29,0,0,0);
//        SimpleDateFormat YYYY = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println("格式化: " + YYYY.format(calendar.getTime()));
//        System.out.println("weekYear:" + calendar.getWeekYear());
//        System.out.println("firstDayOfWeek:" + calendar.getFirstDayOfWeek());
//        System.out.println("minimalDaysInFirstWeek:" + calendar.getMinimalDaysInFirstWeek());


//
//        ExecutorService threadPool = Executors.newFixedThreadPool(100);
//        for (int i = 0; i < 20; i++) {
//            //提交20个并发解析时间的任务到线程池，模拟并发环境
//            threadPool.execute(() -> {
//                for (int j = 0; j < 10; j++) {
//                    try {
//
//                        System.out.println(((SimpleDateFormat)threadSafeSimpleDateFormat.get()).parse("2020-01-01 11:12:13"));
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//        threadPool.shutdown();
//        try {
//            threadPool.awaitTermination(1, TimeUnit.HOURS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        String dateString = "20160901";SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
//        System.out.println("result:" + dateFormat.parse(dateString));


////使用刚才定义的DateTimeFormatterBuilder构建的DateTimeFormatter来解析这个时间
//        LocalDateTime localDateTime = LocalDateTime.parse("2020/1/2 12:34:56.780", dateTimeFormatter);
////解析成功
//        System.out.println(localDateTime.format(dateTimeFormatter));
////使用yyyyMM格式解析20160901是否可以成功呢？
//        String dt = "20160901";
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMM");
//        System.out.println("result:" + dateTimeFormatter.parse(dt));


//        Date today = new Date();
//        Date nextMonth = new Date(today.getTime() + 30L * 1000 * 60 * 60 * 24);
//        System.out.println(today);
//        System.out.println(nextMonth);


//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());
//        c.add(Calendar.DAY_OF_MONTH, 30);
//        System.out.println(c.getTime());

//        LocalDateTime localDateTime =LocalDateTime.now();
//        System.out.println(localDateTime.plusDays(30));

//        System.out.println(LocalDate.now()
//                .minus(Period.ofDays(1))
//                .plus(1, ChronoUnit.DAYS)
//                .minusMonths(1)
//                .plus(Period.ofMonths(1)));

//        System.out.println(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
////        n("//今天之前的一个周六");
////        ("//本月最后一个工作日");
//        System.out.println(LocalDate.now().with(TemporalAdjusters.firstDayOfYear()).plusDays(255));
//        System.out.println(LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.SATURDAY)));
//        System.out.println(LocalDate.now().with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY)));
//        System.out.println(LocalDate.now().with(temporal -> temporal.plus(ThreadLocalRandom.current().nextInt(100),ChronoUnit.DAYS)));
//
//        System.out.println(LocalDate.now().query(DateTest::isFamilyDay));

        final LocalDate date1 = LocalDate.of(2020, 10, 1);
        final LocalDate date2 = LocalDate.of(2020, 12, 15);
        System.out.println(Period.between(date1,date2).getDays());
        System.out.println(ChronoUnit.DAYS.between(date1,date2));

    }

    public static Boolean isFamilyDay(TemporalAccessor date){
        int month =date.get(MONTH_OF_YEAR);
        int day =date.get(DAY_OF_MONTH);
        if (month == Month.FEBRUARY.getValue() && day == 23) {
            return Boolean.TRUE;
        }
        return false;
    }


}
