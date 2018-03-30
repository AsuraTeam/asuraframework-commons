/**
 * Copyright(c)  2018 asura
 */
package org.asuraframework.commons.date;

import com.google.common.annotations.Beta;
import org.asuraframework.commons.util.Check;

import javax.annotation.Nonnull;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * <p>
 * 封装了jdk8的 DateTime类，使用类流式api
 * </p>
 *
 * @author liusq23
 * @version 1.0
 * @since 1.0
 */
@Beta
public class DateUtils {


    private DateUtils() {
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {

        private static TemporalField ISO_WEEKFIELDS = WeekFields.ISO.dayOfWeek();

        private LocalDateTime dateTime;

        public Builder withCurrentDate() {
            dateTime = LocalDateTime.now();
            return this;
        }

        /**
         * 按照指定日期
         *
         * @param date
         * @return
         */
        public Builder withDate(Date date) {
            Instant instant = date.toInstant();
            dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            return this;
        }

        /**
         * 按照毫秒数日期
         *
         * @param millSeconds
         * @return
         */
        public Builder withDate(long millSeconds) {
            Instant instant = Instant.ofEpochMilli(millSeconds);
            dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            return this;
        }

        /**
         * 按照字符串日期 YYYY-MM-DD
         *
         * @param dataStr
         * @return
         */
        public Builder parseDate(String dataStr) {
            return parse(dataStr, DatePattern.DEFAULT_FORMAT_DATE_PATTERN);
        }

        /**
         * 按照字符串日期 yyyy-MM-dd HH:mm:ss
         *
         * @param dataStr
         * @return
         */
        public Builder parseDateTime(String dataStr) {
            return parse(dataStr, DatePattern.DEFAULT_FORMAT_DATETIME_PATTERN);
        }

        /**
         * 按照字符串日期
         *
         * @param dataStr
         * @param pattern
         * @return
         */
        public Builder parse(String dataStr, String pattern) {
            DateTimeFormatter df1 = DateTimeFormatter.ofPattern(pattern);
            DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().append(df1)
                    .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                    .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                    .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
                    .toFormatter();
            dateTime = LocalDateTime.parse(dataStr, dateTimeFormatter);
            return this;
        }

        /**
         * 指定年份
         *
         * @param year
         * @return
         */
        public Builder withYear(int year) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withYear(year);
            return this;
        }

        /**
         * 指定月份
         *
         * @param month
         * @return
         */
        public Builder withMonth(int month) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withMonth(month);
            return this;
        }

        /**
         * 指定一年中的周数
         *
         * @param week
         * @return
         */
        public Builder withWeekOfYear(int week) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            dateTime = dateTime.with(weekFields.weekOfYear(), week);
            return this;
        }

        /**
         * 指定月份中的日期
         *
         * @param dayOfMonth
         * @return
         */
        public Builder withDayOfMonth(int dayOfMonth) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withDayOfMonth(dayOfMonth);
            return this;
        }

        /**
         * 指定一天中的小时
         *
         * @param hourOfDay
         * @return
         */
        public Builder withHourOfDay(int hourOfDay) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withHour(hourOfDay);
            return this;
        }

        /**
         * 指定小时中的分钟
         *
         * @param minuteOfHour
         * @return
         */
        public Builder withMinuteOfHour(int minuteOfHour) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withMinute(minuteOfHour);
            return this;
        }

        /**
         * 指定分钟的秒数
         *
         * @param secondOfMinute
         * @return
         */
        public Builder withSecondOfMinute(int secondOfMinute) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withSecond(secondOfMinute);
            return this;
        }

        /**
         * 指定毫秒数
         *
         * @param millisOfSecond
         * @return
         */
        public Builder withMillisOfSecond(int millisOfSecond) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.with(ChronoField.MILLI_OF_SECOND, millisOfSecond);
            return this;
        }

        /**
         * 指定年月日时分秒毫秒
         *
         * @param year
         * @param monthOfYear
         * @param dayOfMonth
         * @param hourOfDay
         * @param minuteOfHour
         * @param secondOfMinute
         * @param milliOfSecond
         * @return
         */
        public Builder with(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute, int milliOfSecond) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withYear(year)
                    .withMonth(monthOfYear)
                    .withDayOfMonth(dayOfMonth)
                    .withHour(hourOfDay)
                    .withMinute(minuteOfHour)
                    .withSecond(secondOfMinute)
                    .with(ChronoField.MILLI_OF_SECOND, milliOfSecond);
            return this;
        }

        /**
         * 指定年月日
         *
         * @param year
         * @param monthOfYear
         * @param dayOfMonth
         * @return
         */
        public Builder with(int year, int monthOfYear, int dayOfMonth) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withYear(year)
                    .withMonth(monthOfYear)
                    .withDayOfMonth(dayOfMonth);
            return this;
        }

        /**
         * 新增年份
         *
         * @param years
         * @return
         */
        public Builder plusYears(int years) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            if (years == 0) {
                return this;
            }
            dateTime = dateTime.plusYears(years);
            return this;
        }


        /**
         * 增加月份
         *
         * @param months
         * @return
         */
        public Builder plusMonths(int months) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            if (months == 0) {
                return this;
            }
            dateTime = dateTime.plusMonths(months);
            return this;
        }


        /**
         * 增加周数
         *
         * @param weeks
         * @return
         */
        public Builder plusWeeks(int weeks) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            if (weeks == 0) {
                return this;
            }
            dateTime = dateTime.plusWeeks(weeks);
            return this;
        }

        /**
         * 增加天数
         *
         * @param days
         * @return
         */
        public Builder plusDays(int days) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            if (days == 0) {
                return this;
            }
            dateTime = dateTime.plusDays(days);
            return this;
        }

        /**
         * 增加小时
         *
         * @param hours
         * @return
         */
        public Builder plusHours(int hours) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            if (hours == 0) {
                return this;
            }
            dateTime = dateTime.plusHours(hours);
            return this;
        }

        /**
         * 增加分钟数
         *
         * @param minutes
         * @return
         */
        public Builder plusMinutes(int minutes) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            if (minutes == 0) {
                return this;
            }
            dateTime = dateTime.plusMinutes(minutes);
            return this;
        }

        /**
         * 增加秒数
         *
         * @param seconds
         * @return
         */
        public Builder plusSeconds(int seconds) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            if (seconds == 0) {
                return this;
            }
            dateTime = dateTime.plusSeconds(seconds);
            return this;
        }

        /**
         * 当月的第一天
         *
         * @return
         */
        public Builder withFirstDayOfMonth() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.with(TemporalAdjusters.firstDayOfMonth());
            return this;
        }

        /**
         * 当月的最后一天
         *
         * @return
         */
        public Builder withLastDayOfMonth() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.with(TemporalAdjusters.lastDayOfMonth());
            return this;
        }

        /**
         * 一周的第一天 周一为第一天
         *
         * @return
         */
        public Builder withFirstDayOfWeek() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.with(ISO_WEEKFIELDS, 1);
            return this;
        }

        /**
         * 一周的最后一天 周日为第七天
         *
         * @return
         */
        public Builder withLastDayOfWeek() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.with(ISO_WEEKFIELDS, 7);
            return this;
        }


        /**
         * 一天的最后一毫秒
         *
         * @return
         */
        public Builder withLastMillsOfDay() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.with(LocalTime.MAX);
            return this;
        }

        /**
         * 一天的第0秒
         *
         * @return
         */
        public Builder withFirstMillsOfDay() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.with(LocalTime.MIN);
            return this;
        }

        /**
         * 获取年分
         *
         * @return
         */
        public int getYear() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getYear();
        }

        /**
         * 获取月份
         *
         * @return
         */
        public int getMonth() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getMonthValue();
        }

        /**
         * 获取日期在一年中的多少天
         *
         * @return
         */
        public int getDayOfYear() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getDayOfYear();
        }

        /**
         * 获取日期在一个月中的第几天
         *
         * @return
         */
        public int getDayOfMonth() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getDayOfMonth();
        }

        /**
         * 获取日期是一周中的第几天
         *
         * @return
         */
        public int getDayOfWeek() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.get(ISO_WEEKFIELDS);
        }

        /**
         * 获取日期中的小时
         *
         * @return
         */
        public int getHourOfDay() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getHour();
        }

        /**
         * 获取日期中的分钟
         *
         * @return
         */
        public int getMinuteOfHour() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getMinute();
        }

        /**
         * 获取日期中的秒
         *
         * @return
         */
        public int getSecondOfMinute() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getSecond();
        }

        /**
         * 获取日期中的毫秒
         *
         * @return
         */
        public int getMillsOfSecond() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.get(ChronoField.MILLI_OF_SECOND);
        }

        /**
         * 获取当前日期
         *
         * @return
         */
        public Date getDate() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        }

        /**
         * 获取当前日期毫秒数
         *
         * @return
         */
        public long getMillis() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }

        /**
         * 格式化为 YYYY-MM-DD
         *
         * @return
         */
        public String formatDate() {
            return format(DatePattern.DEFAULT_FORMAT_DATE_PATTERN);
        }

        /**
         * 格式化为 yyyy-MM-dd HH:mm:ss
         *
         * @return
         */
        public String formatDateTime() {
            return format(DatePattern.DEFAULT_FORMAT_DATETIME_PATTERN);
        }

        /**
         * 按照指定日期格式化
         *
         * @param datePattern
         * @return
         */
        public String format(@Nonnull String datePattern) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            Objects.requireNonNull(datePattern, "date pattern must not null");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datePattern, Locale.getDefault());
            return ZonedDateTime.of(dateTime, ZoneId.systemDefault()).format(dateTimeFormatter);
        }
    }

}
