import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * @description: 测试类
 * @author: tiger
 * @create: 2019-10-20 19:01
 */
public class Test1 {

    @Test
    public void testLogin() throws Exception {
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate = " + localDate);

//        LocalDate localDate = LocalDate.of(2018,11,12);
//        System.out.println("localDate = " + localDate);

        //获取年、月、日、星期几
        //获取年份
        int year = localDate.getYear();
        System.out.println("获取年份year = " + year);
        int year1 = localDate.get(ChronoField.YEAR);
        System.out.println("获取年份year1 = " + year1);
        //获取月份
        Month month = localDate.getMonth();
        System.out.println("获取月份month = " + month);
        int month1 = localDate.getMonthValue();
        System.out.println("获取月份month1 = " + month1);
        int month2 = localDate.get(ChronoField.MONTH_OF_YEAR);
        System.out.println("获取月份month2 = " + month2);
        //获取年份中的天数
        int dayOfYear = localDate.getDayOfYear();
        System.out.println("获取年份中的天数dayOfYear = " + dayOfYear);
        //获取月份中的天数
        int day = localDate.getDayOfMonth();
        System.out.println("获取月份中的天数day = " + day);
        int day1 = localDate.get(ChronoField.DAY_OF_MONTH);
        System.out.println("获取月份中的天数day1 = " + day1);
        //获取星期中的天数
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        System.out.println("获取星期中的天数dayOfWeek = " + dayOfWeek);
        int dayOfWeek1 = localDate.get(ChronoField.DAY_OF_WEEK);
        System.out.println("获取星期中的天数dayOfWeek1 = " + dayOfWeek1);

        //时分秒
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime = " + localTime);

        //获取时分秒
        int hour = localTime.getHour();
        //获取一天中小时
        System.out.println("获取一天中小时hour = " + hour);
        int hour1 = localTime.get(ChronoField.HOUR_OF_DAY);
        System.out.println("获取一天中小时hour1 = " + hour1);
        int hour2 = localTime.get(ChronoField.SECOND_OF_MINUTE);
        System.out.println("获取一天中小时hour2 = " + hour2);
        //获取小时中分钟
        int minute = localTime.getMinute();
        System.out.println("获取小时中分钟minute = " + minute);
        int minute1 = localTime.get(ChronoField.MINUTE_OF_HOUR);
        System.out.println("获取小时中分钟minute1 = " + minute1);
        //获取一天中的秒数
        int secondOfDay = localTime.get(ChronoField.SECOND_OF_DAY);
        System.out.println("获取一天中的秒数secondOfDay = " + secondOfDay);
        //获取分钟中秒数
        int second = localTime.getSecond();
        System.out.println("获取分钟中秒数second = " + second);
        int second1 = localTime.get(ChronoField.SECOND_OF_MINUTE);
        System.out.println("获取分钟中秒数second1 = " + second1);

        //增加、减少年月日
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);
        //增加一年
        LocalDateTime localDateTime1 = localDateTime.plus(1, ChronoUnit.YEARS);
        System.out.println("增加一年localDateTime1 = " + localDateTime1);
        //减少一年
        LocalDateTime localDateTime2 = localDateTime.minusYears(1);
        System.out.println("减少一年localDateTime2 = " + localDateTime2);

        //修改时间
        LocalDateTime localDateTime3 = localDateTime.withYear(2099);
        System.out.println("修改时间localDateTime3 = " + localDateTime3);

        //计算时间
        //例如计算当月最后一天是几号
        LocalDateTime localDateTime4 = LocalDateTime.now().plusMonths(1).withDayOfMonth(1).minusDays(1);
        System.out.println("当月最后一天是几号localDateTime4 = " + localDateTime4.getDayOfMonth());
        //例如计算下个周末是几号
        LocalDateTime localDateTime5 = LocalDateTime.now().with(ChronoField.DAY_OF_WEEK, 7).plusWeeks(1);
        System.out.println("当前日期的下个周末是几号localDateTime5 = " + localDateTime5.getDayOfMonth());

        //格式化时间
        String sf = localDateTime.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("格式化时间sf = " + sf);
        String sf1 = localDateTime.format(DateTimeFormatter.ISO_DATE);
        System.out.println("格式化时间sf1 = " + sf1);
        String sf2 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("格式化时间sf3 = " + sf2);

        //解析时间
        LocalDate localDate2 = LocalDate.parse(sf, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("解析时间localDate2 = " + localDate2);
        LocalDate localDate3 = LocalDate.parse(sf1, DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("解析时间localDate3 = " + localDate3);
    }
}
