package com.gim.java.base;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Gim
 */
public class DateHelper {

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate)
    {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 获取 一天 开始时间
     * @param localDate 2022-08-01
     * @return 2022-08-01 00：00：00
     */
    public static String getTodayStart(LocalDate localDate){
        LocalDateTime today_start = LocalDateTime.of(localDate, LocalTime.MIN);
        final String format2 = today_start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return format2;
    }

    /**
     * 获取一天结束时间
     * @param localDate 2022-08-01
     * @return 2022-08-01 23:59:59
     */
    public static String getTodayEnd(LocalDate localDate){
        LocalDateTime today_end = LocalDateTime.of(localDate, LocalTime.MAX);
        final String format3 = today_end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return format3;
    }

    /**
     * 获取一段时间内的所有日期
     * @param start 开始时间 @example 2022-05-01
     * @param end 结束时间 @example 2022-07-01
     * @return list ['2022-05-01','2022-05-02'....'2022-07-01']
     */
    public static List<String> getBetweenDate(String start, String end) {
        List<String> list = new ArrayList<>();
        // LocalDate默认的时间格式为2020-02-02
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }
        Stream.iterate(startDate, d -> d.plusDays(1)).limit(distance + 1).forEach(f -> list.add(f.toString()));
        return list;
    }


}
