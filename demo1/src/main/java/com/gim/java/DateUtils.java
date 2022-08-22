package com.gim.java;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Gim
 */
public class DateUtils {

    /**
     * 计算 相差小时数
     * @param startDate
     * @param endDate
     * @return
     */
    public static String diffDate(Date startDate,Date endDate){
        final long between = DateUtil.between(startDate, endDate, DateUnit.MINUTE);
        System.out.println(between);
        final String format = String.format("%.2f", between % 60L / 60.0);
        return format;
    }

    /**
     * 对一个时间加一个时间
     *
     */
    public static Date addDate(Date targetDate, Date nowDate,Date endDate)
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
        Calendar cal = Calendar.getInstance();
        cal.setTime(targetDate);
        cal.add(Calendar.HOUR, (int) hour);
        cal.add(Calendar.MINUTE, (int) min);
        cal.set(Calendar.YEAR,targetDate.getYear());
        cal.set(Calendar.MONTH,targetDate.getMonth());

        return cal.getTime();
    }
}
