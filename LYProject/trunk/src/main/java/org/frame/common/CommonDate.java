package org.frame.common;

import org.apache.tomcat.jni.Local;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 作者： liwei
 * 时间：2018年1月2日
 * 描述：公共时间类
 */
public class CommonDate {

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 作者： liwei
     * 时间：2017年12月29日
     * 描 述：根据指定时间获取指定天数后的时间
     *
     * @param specifiedDay
     * @return
     */
    public static LocalDate getSpecifiedDayAfter(String specifiedDay, int day) {
        return LocalDate.parse(specifiedDay).plusDays(day);
    }

    /**
     * 作者： liwei
     * 时间：2017年12月29日
     * 描 述：根据指定时间获取指定天数后的时间
     *
     * @param date
     * @return
     */
    public static String getSpecifiedDayAfter(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int days = c.get(Calendar.DATE);
        c.set(Calendar.DATE, days + day);
        String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
                .format(c.getTime());
        return dayAfter;
    }

    /**
     * 基于java8
     * 作者： liwei
     * 时间：2017年12月29日
     * 描 述：根据指定时间获取指定天数后的时间
     * 例：getSpecifiedDayAfter(LocalDate.parse('2018-10-01'),2)=2018-10-03
     *
     * @param specifiedDay
     * @return
     */
    public static LocalDate getSpecifiedDayAfter(LocalDate specifiedDay, int day) {
        return specifiedDay.plusDays(day);
    }

    /**
     * 作者： liwei
     * 时间：2017年12月29日
     * 描 述：date转String
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 作者： liwei
     * 时间：2017年12月29日
     * 描 述：date转String
     *
     * @param date
     * @return
     */
    public static String dateToString(Local date) {
        return date.toString();
    }

    /**
     * 作者： liwei
     * 时间：2017年12月29日
     * 描 述：String转date yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static Date StringToDate(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(date);
    }

    /**
     * 作者： liwei
     * 时间：2017年12月29日
     * 描 述：String转LocalDate yyyy-MM-dd
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static LocalDate StringToLocalDate(String date) {
        return LocalDate.parse(date);
    }

    /**
     * 作者： liwei
     * 时间：2017年12月29日
     * 描 述：String转date yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static LocalDateTime StringToLocalDateTime(String date) {
        return LocalDateTime.parse(date);
    }

    /**
     * 作者： liwei
     * 时间：2017年12月16日
     * 描 述：String转date yyyy-MM-dd
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static Date StringTodateYMD(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(date);
    }

    /**
     * 作者： liwei 时间：2017年12月29日 描 述：获取系统当前时间
     *
     * @return
     * @throws ParseException
     */
    public static Date nowDate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy:MM:dd HH:mm:ss");
        Date now = new Date();
        String date = dateFormat.format(now);
        return dateFormat.parse(date);
    }

    /**
     * 作者： Li.Wei
     * 时间： 2018/8/28 10:43
     * 描述： 获取系统当前时间
     **/
    public static LocalDateTime nowDateLocalDateTime() {
        return LocalDateTime.parse(LocalDateTime.now().format(DATETIME_FORMATTER),DATETIME_FORMATTER);
    }

    /**
     * 作者： Li.Wei
     * 时间： 2018/8/28 10:38
     * 描述： 获取系统当前时间
     **/
    public static String nowDateLocalDateTimeToString() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }

    /**
     * 作者： liwei 时间：2017年12月29日 描 述：获取系统当前日期
     *
     * @return
     * @throws ParseException
     */
    public static String nowDateYMD() {
        return LocalDate.now().toString();
    }

    /**
     * 作者： liwei 时间：2017年12月29日 描 述：获取当前时间的时间戳
     *
     * @return
     */
    public static long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 作者： liwei 时间：2017年12月29日 描 述：时间戳转换成时间格式
     *
     * @param timeStamp
     * @return
     */
    public static Date timeStampDate(Long timeStamp) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(timeStamp));
        return sdf.parse(sd);
    }

    /**
     * 作者： liwei 时间：2017年12月29日 描 述：将时间转换为时间戳
     *
     * @param s
     * @return
     * @throws ParseException
     */
    public static Long dateToStamp(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        return ts;
    }

    /**
     * 作者： liwei 时间：2017年12月6日 描 述：根据指定日期和天数得到新的日期 -返回String
     *
     * @param date
     * @param day
     * @return
     * @throws Exception
     */
    public static String appointDateString(Date date, long day) {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相加得到新的毫秒数
        return dateToString(new Date(time)); // 将毫秒数转换成日期
    }

    /**
     * 作者： liwei 时间：2017年12月6日 描 述：根据指定日期和天数得到新的日期 -返回Date
     *
     * @param date
     * @param day
     * @return
     * @throws Exception
     */
    public static Date appointDateDate(Date date, long day) throws Exception {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }

    /**
     * 作者： yudongdong 时间：2017年12月29日 描 述：根据当前时间与指定月数获取新的时间
     *
     * @param count
     * @return
     */
    public static Date getBeforeOrAfterDate(Integer count) {
        Calendar calendar = Calendar.getInstance(); // 得到日历
        calendar.setTime(new Date());// 把当前时间赋给日历
        calendar.add(Calendar.MONTH, count); // 设置为前或后几个月
        Date date = calendar.getTime(); // 得时间
        return date;
    }

    /**
     * 作者： liwei 时间：2017年12月6日 描 述：获取指定时间的年
     *
     * @param date
     * @return
     */
    public static int appointDateYear(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        return now.get(Calendar.YEAR);
    }

    /**
     * 作者： liwei 时间：2017年12月6日 描 述：获取指定时间的月
     *
     * @param date
     * @return
     */
    public static int appointDateMonth(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        return now.get(Calendar.MONTH) + 1;
    }

    /**
     * 作者： liwei 时间：2017年12月6日 描 述：获取指定时间的日
     *
     * @param date
     * @return
     */
    public static int appointDateDay(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        return now.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 作者： yudongdong 时间：2017年12月29日 描 述：获得当前月--结束日期
     *
     * @param date
     * @return
     */
    public static Date getMaxMonthDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 作者： yudongdong 时间：2017年12月29日 描 述：获得当前月--开始日期
     *
     * @param date
     * @return
     */
    public static Date getMinMonthDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();

    }

    /**
     * 作者： yudongdong 时间：2017年12月29日 描 述：获得当前周- 星期天的日期
     *
     * @return
     */
    public static Date getPreviousSunday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        Date monday = currentDate.getTime();
        return monday;
    }

    /**
     * 作者： yudongdong 时间：2017年12月29日 描 述：获得当前周- 星期一的日期
     *
     * @return
     */
    public static Date getCurrentMonday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        Date monday = currentDate.getTime();
        return monday;
    }

    /**
     * 作者： yudongdong 时间：2017年12月29日 描 述： 获得本周一与当前日期相差的天数
     *
     * @return
     */
    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    /**
     * 作者： yudongdong 时间：2017年12月29日 描 述：当天的开始时间
     *
     * @return
     */
    public static Date getDateStart() {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        return c1.getTime();

    }

    /**
     * 作者： yudongdong 时间：2017年12月29日 描 述：当天的结束时间
     *
     * @return
     */
    public static Date getDateEnd() {
        Calendar c2 = new GregorianCalendar();
        c2.setTime(new Date());
        c2.set(Calendar.HOUR_OF_DAY, 23);
        c2.set(Calendar.MINUTE, 59);
        c2.set(Calendar.SECOND, 59);
        return c2.getTime();
    }

    /**
     * 作者： yudongdong 时间：2017年12月29日 描 述：根据年、月获取指定月有多少天
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 作者： yudongdong 时间：2017年12月29日 描 述：获取指定时间是星期几
     *
     * @param date
     * @return
     */
    public static int getWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek - 1;
    }

    /**
     * 作者： yudongdong 时间：2017年12月29日 描 述：获取指定年有多少周
     *
     * @param year
     * @return
     */
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        return getWeekNumOfYear(c.getTime());
    }

    /**
     * 作者： yudongdong 时间：2017年12月29日 描 述：获取指定时间是一年中的多少周
     *
     * @param date
     * @return
     */
    public static int getWeekNumOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * time差
     *
     * @param before
     * @param after
     * @return
     */
    public static long diffTimes(Date before, Date after) {
        return after.getTime() - before.getTime();
    }

    /**
     * 秒差
     *
     * @param before
     * @param after
     * @return
     */
    public static long diffSecond(Date before, Date after) {
        return (after.getTime() - before.getTime()) / 1000;
    }

    /**
     * 分种差
     *
     * @param before
     * @param after
     * @return
     */
    public static int diffMinute(Date before, Date after) {
        return (int) (after.getTime() - before.getTime()) / 1000 / 60;
    }

    /**
     * 时差
     *
     * @param before
     * @param after
     * @return
     */
    public static int diffHour(Date before, Date after) {
        return (int) (after.getTime() - before.getTime()) / 1000 / 60 / 60;
    }

    /**
     * 天数差
     *
     * @param
     * @param
     * @return
     */
    public static int diffDay(Date before, Date after) {
        return Integer.parseInt(String.valueOf(((after.getTime() - before
                .getTime()) / 86400000)));
    }

    /**
     * 月差
     *
     * @param before
     * @param after
     * @return
     */
    public static int diffMonth(Date before, Date after) {
        int monthAll = 0;
        int yearsX = diffYear(before, after);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(before);
        c2.setTime(after);
        int monthsX = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        monthAll = yearsX * 12 + monthsX;
        int daysX = c2.get(Calendar.DATE) - c1.get(Calendar.DATE);
        if (daysX > 0) {
            monthAll = monthAll + 1;
        }
        return monthAll;
    }

    /**
     * 年差
     *
     * @param before
     * @param after
     * @return
     */
    public static int diffYear(Date before, Date after) {
        return appointDateYear(after) - appointDateYear(before);
    }

    /**
     * 设置23:59:59
     *
     * @param date
     * @return
     */
    public static Date setEndDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 设置00:00:00
     *
     * @param date
     * @return
     */
    public static Date setStartDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        return calendar.getTime();
    }

    /**
     * 作者： Li.Wei
     * 时间： 2018/8/28 10:27
     * 描述： 取指定时间月份的最后一天
     **/
    public static LocalDate lastDayOfThisMonth(LocalDate date) {
        return date.with(TemporalAdjusters.lastDayOfMonth());
    }

}
