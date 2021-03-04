package com.cmj.example.utils;

import com.cmj.example.vo.CycleRateResultVo;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/1/6
 */
public class DateTimeUtils {
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_FORMAT2 = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT4 = "yyyy-MM-dd HH:mm";
    private static final ThreadLocal<SimpleDateFormat> localSimpleDateFormat = new ThreadLocal<SimpleDateFormat>();

    /**
     * 判断当前时间是否小于指定时间
     *
     * @param dateStr
     * @param dateFormart
     * @return boolean
     * @author mengjie_chen
     * @date 2021/1/6
     */
    public static boolean isLessThanDate(String dateStr, String dateFormart) {
        if (StringUtils.isEmpty(dateStr)) {
            return false;
        }
        Date now = new Date();
        if (StringUtils.isEmpty(dateFormart)) {
            dateFormart = DateTimeUtils.DATETIME_FORMAT;
        }
        Date date = DateTimeUtils.parse(dateStr, dateFormart);
        return now.getTime() - date.getTime() < 0;
    }

    /**
     * 判断当前时间是否在指定的两个时间之间
     *
     * @param startDateStr
     * @param endTimeStr
     * @param dateFormart
     * @return boolean
     * @author mengjie_chen
     * @date 2021/1/6
     */
    public static boolean isBetweenInDate(String startDateStr, String endTimeStr, String dateFormart) {
        if (StringUtils.isEmpty(startDateStr) || StringUtils.isEmpty(endTimeStr)) {
            System.out.println("时间异常");
            return false;
        }
        Date now = new Date();
        if (StringUtils.isEmpty(dateFormart)) {
            dateFormart = DateTimeUtils.DATETIME_FORMAT;
        }
        Date startDate = DateTimeUtils.parse(startDateStr, dateFormart);
        Date endTime = DateTimeUtils.parse(endTimeStr, dateFormart);
        return now.getTime() - startDate.getTime() >= 0 && endTime.getTime() - now.getTime() >= 0;
    }

    /**
     * 将指定格式的字符串时间转为Date
     *
     * @param v
     * @param fm
     * @return java.util.Date
     * @author mengjie_chen
     * @date 2021/1/6
     */
    public static Date parse(String v, String fm) {
        try {
            return new SimpleDateFormat(fm).parse(v);
        } catch (Exception e) {
            System.out.println("时间解析异常");
        }
        return null;
    }

    /**
     * 将时间格式化为指定的格式
     *
     * @param v
     * @param fm
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2021/1/6
     */
    public static String format(Date v, String fm) {
        try {
            return new SimpleDateFormat(fm).format(v);
        } catch (Exception e) {
            System.out.println("时间解析异常");
        }
        return null;
    }

    /**
     * 指定时分秒，并加上天数
     *
     * @param hour
     * @param minute
     * @param second
     * @param addDay
     * @param args
     * @return java.util.Date
     * @author mengjie_chen
     * @date 2021/1/6
     */
    public static Date getNeedTime(int hour, int minute, int second, int addDay, int... args) {
        Calendar calendar = Calendar.getInstance();
        if (addDay != 0) {
            calendar.add(Calendar.DATE, addDay);
        }
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        if (args.length == 1) {
            calendar.set(Calendar.MILLISECOND, args[0]);
        }
        return calendar.getTime();
    }

    /**
     * 指定年月日时分秒毫秒
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @param args
     * @return java.util.Date
     * @author mengjie_chen
     * @date 2021/1/6
     */
    public static Date getAbsoluteTime(int year, int month, int day, int hour, int minute, int second, int... args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        if (args.length == 1) {
            calendar.set(Calendar.MILLISECOND, args[0]);
        }
        return calendar.getTime();
    }

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 返回日期的月份，1-12,即yyyy-MM-dd中的MM
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回日期的年,即yyyy-MM-dd中的yyyy
     *
     * @param date Date
     * @return int
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    public static int calDiffMonth(String startDate, String endDate) {
        int result = 0;
        try {
            SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
            Date start = sfd.parse(startDate);
            Date end = sfd.parse(endDate);
            int startYear = getYear(start);
            int startMonth = getMonth(start);
            int startDay = getDay(start);
            int endYear = getYear(end);
            int endMonth = getMonth(end);
            int endDay = getDay(end);
            if (startDay > endDay) { //1月17  大于 2月28
                if (endDay == getDaysOfMonth(getYear(new Date()), 2)) {   //也满足一月
                    result = (endYear - startYear) * 12 + endMonth - startMonth;
                } else {
                    result = (endYear - startYear) * 12 + endMonth - startMonth - 1;
                }
            } else {
                result = (endYear - startYear) * 12 + endMonth - startMonth;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static int totalYear(String startDate, String endDate) {
        int result = 0;
        try {
            SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
            Date start = sfd.parse(startDate);
            Date end = sfd.parse(endDate);
            int startYear = getYear(start);
            int endYear = getYear(end);
            result = endYear - startYear + 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取两个日期之间相差的月数（自然月）
     *
     * @param startDate
     * @param endDate
     * @param format
     * @param monthType (1：单月   2：双月    3：季度    4：半年    5：整年)
     * @return int
     * @author mengjie_chen
     * @date 2020/11/13
     */
    public static int calDiffMonthNaturally(String startDate, String endDate, String format, Integer monthType) {
        int result = 0;
        for (int i = 1; ; i++) {
            result++;
            startDate = DateTimeUtils.addMonths(startDate, DateTimeUtils.DATETIME_FORMAT2, monthType);
            String lastDayOfMonth = DateTimeUtils.getFirstDayOfMonth(startDate, format);
            if (DateTimeUtils.compareDate(lastDayOfMonth, endDate, format)) {
                break;
            }

        }
        return result;
    }

    public static String addDays(String source, String format, int days) {
        Date date = DateTimeUtils.parse(source, format);
        Date nextDay = add(add(add(date, Calendar.YEAR, 0), Calendar.MONTH, 0), 5, days);
        return DateTimeUtils.formatDate(format, nextDay);
    }

    /**
     * 获取周期信息
     *
     * @param startDate 整个周期的开始时间
     * @param endDate   整个周期的结束时间
     * @param format    日期格式
     * @param monthType 周期之间的间隔
     * @return java.util.List<com.cmj.example.base.vo.CycleRateResultVo>
     * @author mengjie_chen
     * @date 2021/1/6
     */
    public static List<CycleRateResultVo> getCycleRateList(String startDate, String endDate, String format, Integer monthType) throws Exception {
        List<CycleRateResultVo> cycleRateList = new ArrayList<>(10);
        String lastDayOfMonth = "";
        for (int i = 1; ; i++) {
            if (i == 1) {
                String lastDayOfCycle = DateTimeUtils.getLastDayOfCycle(startDate, DateTimeUtils.DATETIME_FORMAT2, monthType);
                lastDayOfMonth = lastDayOfCycle;
                if (DateTimeUtils.compareDate(lastDayOfCycle, endDate, format)) {
                    CycleRateResultVo cycleRateResultVo = CycleRateResultVo.CycleRateResultVoBuilder
                            .cycleRateResultVo()
                            .cycleRate(new BigDecimal("1"))
                            .cycleFirstDay(startDate)
                            .cycleLastDay(endDate)
                            .build();
                    cycleRateList.add(cycleRateResultVo);
                    break;
                }
                long distDays = DateTimeUtils.getDistanceDaysNatural(startDate, lastDayOfCycle, format);
                long totalDaysOfCycle = DateTimeUtils.getDistanceDaysNatural(DateTimeUtils.getFirstDayOfMonth(startDate, format), lastDayOfMonth, format);
                CycleRateResultVo cycleRateResultVo = CycleRateResultVo.CycleRateResultVoBuilder
                        .cycleRateResultVo()
                        .cycleRate(new BigDecimal(distDays).divide(new BigDecimal(totalDaysOfCycle), 20, BigDecimal.ROUND_HALF_UP))
                        .cycleFirstDay(startDate)
                        .cycleLastDay(lastDayOfCycle)
                        .build();
                cycleRateList.add(cycleRateResultVo);
            } else {
                String nextCycleFirstDay = DateTimeUtils.addDays(lastDayOfMonth, format, 1);
                String lastDayOfCycle = DateTimeUtils.getLastDayOfCycle(nextCycleFirstDay, format, monthType);
                lastDayOfMonth = lastDayOfCycle;
                if (DateTimeUtils.compareDate(lastDayOfCycle, endDate, format)) {
                    long distDays = DateTimeUtils.getDistanceDaysNatural(nextCycleFirstDay, endDate, format);
                    long totalDaysOfCycle = DateTimeUtils.getDistanceDaysNatural(nextCycleFirstDay, lastDayOfCycle, format);
                    CycleRateResultVo cycleRateResultVo = CycleRateResultVo.CycleRateResultVoBuilder
                            .cycleRateResultVo()
                            .cycleRate(new BigDecimal(distDays).divide(new BigDecimal(totalDaysOfCycle), 20, BigDecimal.ROUND_HALF_UP))
                            .cycleFirstDay(nextCycleFirstDay)
                            .cycleLastDay(endDate)
                            .build();
                    cycleRateList.add(cycleRateResultVo);
                    break;
                } else {
                    CycleRateResultVo cycleRateResultVo = CycleRateResultVo.CycleRateResultVoBuilder
                            .cycleRateResultVo()
                            .cycleRate(new BigDecimal("1"))
                            .cycleFirstDay(nextCycleFirstDay)
                            .cycleLastDay(lastDayOfCycle)
                            .build();
                    cycleRateList.add(cycleRateResultVo);
                }
            }
        }
        return cycleRateList;
    }

    public static String getLastDayOfCycle(String date, String format, Integer monthType) {
        return DateTimeUtils.getLastDayOfMonth(DateTimeUtils.addMonths(date, format, monthType - 1), format);
    }

    public static long getDistanceDaysNatural(String str1, String str2, String formatPattern) throws Exception {
        DateFormat df = DateTimeUtils.setAndGetDateFormat(formatPattern);
        Date one = df.parse(str1);
        Date two = df.parse(str2);
        return getDistanceDays(one, two) + 1;
    }

    public static SimpleDateFormat setAndGetDateFormat(String format) {
        localSimpleDateFormat.remove();
        SimpleDateFormat sdf = null;
        if ("yyyy-MM-dd".equals(format)) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        } else if ("yyyy".equals(format)) {
            sdf = new SimpleDateFormat("yyyy");
        } else if ("MM".equals(format)) {
            sdf = new SimpleDateFormat("MM");
        } else if ("dd".equals(format)) {
            sdf = new SimpleDateFormat("dd");
        } else if ("MM-dd".equals(format)) {
            sdf = new SimpleDateFormat("MM-dd");
        } else if ("yyyy-MM-dd HH:mm:ss".equals(format)) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else if ("yyyy-MM".equals(format)) {
            sdf = new SimpleDateFormat("yyyy-MM");
        } else if ("yyyyMMdd".equals(format)) {
            sdf = new SimpleDateFormat("yyyyMMdd");
        } else if ("yyyyMM".equals(format)) {
            sdf = new SimpleDateFormat("yyyyMM");
        } else if ("yyyy-MM-dd HH:mm".equals(format)) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        } else if ("yyyy.MM.dd".equals(format)) {
            sdf = new SimpleDateFormat("yyyy.MM.dd");
        } else if ("yyyy.MM".equals(format)) {
            sdf = new SimpleDateFormat("yyyy.MM");
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }
        localSimpleDateFormat.set(sdf);
        return localSimpleDateFormat.get();
    }

    /**
     * 时间差天
     *
     * @param str1
     * @param str2
     * @return
     * @throws Exception
     * @author huch
     * @date:May 13, 2016 3:00:44 PM
     */
    public static long getDistanceDays(String str1, String str2, String formatPattern) throws Exception {
        DateFormat df = DateTimeUtils.setAndGetDateFormat(formatPattern);
        Date one = df.parse(str1);
        Date two = df.parse(str2);
        return getDistanceDays(one, two) + 1;
    }

    public static int getDaysInCurrentMonth(String date, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateTimeUtils.parse(date, format));
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 时间差天
     *
     * @param str1
     * @param str2
     * @return
     * @throws Exception
     * @author huch
     * @date:May 13, 2016 3:00:44 PM
     */
    public static long getDistanceDays(Date date1, Date date2) throws Exception {
        long days = 0;
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        days = diff / (1000 * 60 * 60 * 24);
        return days;
    }

    /**
     * 获取某月第一天
     *
     * @param s
     * @param formatPattern
     * @return
     */
    public static String getFirstDayOfMonth(String s, String formatPattern) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(parse(s, formatPattern));
        int firstDay = cal.getActualMinimum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    public static String formatDate(String format, Date d) {
        if (d == null) {
            return null;
        }
        return new SimpleDateFormat(format).format(d);
    }

    /**
     * 加上指定月份
     *
     * @param source
     * @param format
     * @param months
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2020/10/19
     */
    public static String addMonths(String source, String format, int months) {
        Date date = DateTimeUtils.parse(source, format);
        Date nextMonth = add(add(add(date, Calendar.YEAR, 0), Calendar.MONTH, months), 5, 0);
        return DateTimeUtils.formatDate(format, nextMonth);
    }

    public static Date add(Date source, int field, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(source);
        cal.add(field, amount);
        return cal.getTime();
    }

    /**
     * 获取某月最后一天
     *
     * @param s
     * @param formatPattern
     * @return
     */
    public static String getLastDayOfMonth(String s, String formatPattern) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(parse(s, formatPattern));
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 判断第一个时间是否大于第二个时间
     *
     * @param d1
     * @param d2
     * @param format
     * @return boolean
     * @author mengjie_chen
     * @date 2019/10/10
     */
    public static boolean compareDate(String d1, String d2, String format) {
        if (StringUtils.isEmpty(d1) || StringUtils.isEmpty(d2)) {
            System.out.println("时间异常");
            return false;
        }
        if (StringUtils.isEmpty(format)) {
            format = DateTimeUtils.DATETIME_FORMAT;
        }
        Date date1 = DateTimeUtils.parse(d1, format);
        Date date2 = DateTimeUtils.parse(d2, format);
        return date1.getTime() - date2.getTime() > 0;
    }

    /**
     * 获取某月最后一天
     *
     * @param date
     * @return java.util.Date
     * @author mengjie_chen
     * @date 2020/10/19
     */
    public static Date getLastDayOfMonthHourse(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(2, 1);
        cal.add(5, -1 * cal.get(5));
        cal.set(11, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        return cal.getTime();
    }

    /**
     * 获取某月第一天
     *
     * @param date
     * @return java.util.Date
     * @author mengjie_chen
     * @date 2020/10/19
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
