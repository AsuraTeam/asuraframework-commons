/**
 * Copyright(c) 2018 asura
 */
package org.asuraframework.commons.date;

import org.asuraframework.commons.util.Check;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 日期计算类
 * </p>
 *
 * @author liusq23
 * @version 1.0
 * @Date 2018/3/25 上午12:49
 * @since 1.0
 */
public class DateCalculator {

    /**
     * 简称
     */
    private static final String[] WEEK_NAME_CHINESE_SIMPLE = new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    /**
     * 正式称呼
     */
    private static final String[] WEEK_NAME_CHINESE = new String[]{"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};

    /**
     * 私有化构造
     */
    private DateCalculator() {

    }

    /**
     * 是否同一天 年月日是否一致
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isTheSameDay(@Nullable Date date1, @Nullable Date date2) {
        if (Check.isNullObjects(date1, date2)) {
            return false;
        }
        LocalDateTime dateTime1 = LocalDateTime.ofInstant(date1.toInstant(), ZoneId.systemDefault());
        LocalDateTime dateTime2 = LocalDateTime.ofInstant(date2.toInstant(), ZoneId.systemDefault());
        //年份是否一样
        if (dateTime1.getYear() != dateTime2.getYear()) {
            return false;
        }
        //日期是否一样
        if (dateTime1.getDayOfYear() != dateTime2.getDayOfYear()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否同一月份 年月是否一致
     *
     * @param dateStr1
     * @param pattern1
     * @param dateStr2
     * @param pattern2
     * @return
     */
    public static boolean isTheSameDay(@Nullable String dateStr1, @Nonnull String pattern1, @Nullable String dateStr2, @Nonnull String pattern2) {
        if (Check.isNullObjects(dateStr1, dateStr2)) {
            return false;
        }
        Objects.requireNonNull(pattern1, "pattern1 can not null");
        Objects.requireNonNull(pattern2, "pattern2 can not null");
        return isTheSameDay(DateParser.parse(dateStr1, pattern1), DateParser.parse(dateStr2, pattern2));
    }

    /**
     * 是否同一天 年月日是否一致
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isTheSameMonth(@Nullable Date date1, @Nullable Date date2) {
        if (Check.isNullObjects(date1, date2)) {
            return false;
        }
        LocalDateTime dateTime1 = LocalDateTime.ofInstant(date1.toInstant(), ZoneId.systemDefault());
        LocalDateTime dateTime2 = LocalDateTime.ofInstant(date2.toInstant(), ZoneId.systemDefault());
        //年份是否一样
        if (dateTime1.getYear() != dateTime2.getYear()) {
            return false;
        }
        //月份是否一样
        if (dateTime1.getMonthValue() != dateTime2.getMonthValue()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否同一月份 年月是否一致
     *
     * @param dateStr1
     * @param pattern1
     * @param dateStr2
     * @param pattern2
     * @return
     */
    public static boolean isTheSameMonth(@Nullable String dateStr1, @Nonnull String pattern1, @Nullable String dateStr2, @Nonnull String pattern2) {
        if (Check.isNullObjects(dateStr1, dateStr2)) {
            return false;
        }
        Objects.requireNonNull(pattern1, "pattern1 can not null");
        Objects.requireNonNull(pattern2, "pattern2 can not null");
        return isTheSameMonth(DateParser.parse(dateStr1, pattern1), DateParser.parse(dateStr2, pattern2));
    }

    /**
     * 获取当前日期是周几
     *
     * @param date
     * @return
     */
    public static String getChineseSimpleWeekName(@Nonnull Date date) {
        Objects.requireNonNull(date, "date must not null");
        int dw = DateUtils.builder().withDate(date).getDayOfWeek();
        return WEEK_NAME_CHINESE_SIMPLE[dw - 1];
    }

    /**
     * 获取当前日期是周几
     *
     * @param epochMilli
     * @return
     */
    public static String getChineseSimpleWeekName(long epochMilli) {
        int dw = DateUtils.builder().withDate(epochMilli).getDayOfWeek();
        return WEEK_NAME_CHINESE_SIMPLE[dw - 1];
    }

    /**
     * 获取当前日期是星期几
     *
     * @param date
     * @return
     */
    public static String getChineseWeekName(@Nonnull Date date) {
        Objects.requireNonNull(date, "date must not null");
        int dw = DateUtils.builder().withDate(date).getDayOfWeek();
        return WEEK_NAME_CHINESE[dw - 1];
    }

    /**
     * 获取当前日期是星期几
     *
     * @param epochMilli
     * @return
     */
    public static String getChineseWeekName(long epochMilli) {
        int dw = DateUtils.builder().withDate(epochMilli).getDayOfWeek();
        return WEEK_NAME_CHINESE[dw - 1];
    }
}
