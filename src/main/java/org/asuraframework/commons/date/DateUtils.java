/**
 * Copyright(c)  2018 asura
 */
package org.asuraframework.commons.date;

import com.google.common.annotations.Beta;
import org.asuraframework.commons.util.Check;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

/**
 * <p>
 * 基本是封装了jodaTime的 DateTime类，使用类流式api
 * </p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
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

        private LocalDateTime dateTime;

        public Builder withCurrentDate() {
            dateTime = LocalDateTime.now();
            return this;
        }

        public Builder withDate(Date date) {
            Instant instant = date.toInstant();
            dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            return this;
        }

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

        public Builder withYear(int year) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withYear(year);
            return this;
        }

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

        public Builder withMonthOfYear(int month) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withMonth(month);
            return this;
        }

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

        public Builder withWeekOfYear(int week) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            dateTime = dateTime.with(weekFields.weekOfYear(), week);
            return this;
        }

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

        public Builder withDayOfMonth(int dayOfMonth) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withDayOfMonth(dayOfMonth);
            return this;
        }

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

        public Builder withHourOfDay(int hourOfDay) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withHour(hourOfDay);
            return this;
        }

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

        public Builder withMinuteOfHour(int minuteOfHour) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withMinute(minuteOfHour);
            return this;
        }

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

        public Builder withSecondOfMinute(int secondOfMinute) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withSecond(secondOfMinute);
            return this;
        }

        public Builder withMillisOfSecond(int millisOfSecond) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.with(ChronoField.MILLI_OF_SECOND, millisOfSecond);
            return this;
        }

        public Builder plus(long duration) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            if (duration == 0) {
                return this;
            }
            dateTime = dateTime.plus(duration, ChronoField.MILLI_OF_DAY.getBaseUnit());
            return this;
        }

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

        public Builder with(int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute, int milliOfSecond) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withYear(monthOfYear)
                    .withDayOfMonth(dayOfMonth)
                    .withHour(hourOfDay)
                    .withMinute(minuteOfHour)
                    .withSecond(secondOfMinute)
                    .with(ChronoField.MILLI_OF_SECOND, milliOfSecond);
            return this;
        }

        public Builder with(int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute, int milliOfSecond) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withDayOfMonth(dayOfMonth)
                    .withHour(hourOfDay)
                    .withMinute(minuteOfHour)
                    .withSecond(secondOfMinute)
                    .with(ChronoField.MILLI_OF_SECOND, milliOfSecond);
            return this;
        }

        public Builder withLastDayOfWeek() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            TemporalField field = WeekFields.of(Locale.getDefault()).dayOfWeek();
            dateTime = dateTime.with(field, 7);
            return this;
        }

        public Builder withFirstDayOfWeek() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            TemporalField field = WeekFields.of(Locale.getDefault()).dayOfWeek();
            dateTime = dateTime.with(field, 1);
            return this;
        }

        public Builder withLastMillsOfDay() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.with(LocalTime.MAX);
            return this;
        }

        public Builder withFirstMillsOfDay() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.with(LocalTime.MIN);
            return this;
        }

        public Builder withLastDayOfMonth() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.with(TemporalAdjusters.lastDayOfMonth());
            return this;
        }

        public Builder withFirstDayOfMonth() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.with(TemporalAdjusters.firstDayOfMonth());
            return this;
        }

        public Builder with(int hourOfDay, int minuteOfHour, int secondOfMinute, int milliOfSecond) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withHour(hourOfDay)
                    .withMinute(minuteOfHour)
                    .withSecond(secondOfMinute)
                    .with(ChronoField.MILLI_OF_SECOND, milliOfSecond);
            return this;
        }


        public Builder with(int minuteOfHour, int secondOfMinute, int milliOfSecond) {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withMinute(minuteOfHour)
                    .withSecond(secondOfMinute)
                    .with(ChronoField.MILLI_OF_SECOND, milliOfSecond);
            return this;
        }

        public int getYear() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getYear();
        }

        public int getMonthOfYear() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getMonthValue();
        }

        public int getDayOfYear() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getDayOfYear();
        }

        public int getDayOfMonth() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getDayOfMonth();
        }

        public int getDayOfWeek() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getDayOfWeek().getValue();
        }

        public int getHourOfDay() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getHour();
        }

        public int getMinuteOfHour() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getMinute();
        }

        public int getSecondOfMinute() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getSecond();
        }

        public int getMillsOfSecond() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getSecond();
        }

        public Date getDate() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        }

        public long getMillis() {
            if (Check.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }

//        public String formatDate() {
//            if (Check.isNull(dateTime)) {
//                withCurrentDate();
//            }
//            return DateFormatter.formatDate(dateTime.toDate());
//        }
//
//        public String formatDateTime() {
//            if (Check.isNull(dateTime)) {
//                withCurrentDate();
//            }
//            return DateFormatter.formatDateTime(dateTime.toDate());
//        }
//
//        public String format(String datePattern) {
//            if (Check.isNull(dateTime)) {
//                withCurrentDate();
//            }
//            return DateFormatter.format(dateTime.toDate(), datePattern);
//        }
    }

}
