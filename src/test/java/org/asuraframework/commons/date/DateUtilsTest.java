/**
 * Copyright(c) 2018 asura
 */
package org.asuraframework.commons.date;

import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;

/**
 * <p></p>
 *
 * @author liusq23
 * @version 1.0
 * @Date 2018/3/25 上午12:18
 * @since 1.0
 */
public class DateUtilsTest {

    @Test
    public void dateUtilsTest() {
        Date d = DateUtils.builder().getDate();
        long mills = DateUtils.builder().withDate(d).getMillis();
        Assert.assertEquals(d.getTime(), mills);
    }

    @Test
    public void dateUtils1Test() {
        Date d = DateUtils.builder().with(2019,1,1,0,0,0,0).getDate();
        int dayOfMonth1 = DateUtils.builder().withDate(d).withWeekOfYear(1).withFirstDayOfWeek().getDayOfMonth();
        Assert.assertEquals(dayOfMonth1,31);
        int dayOfMonth2 = DateUtils.builder().withDate(d).withWeekOfYear(1).withLastDayOfWeek().getDayOfMonth();
        Assert.assertEquals(dayOfMonth2,6);
        long firstMillsOfDay = DateUtils.builder().withDate(d).withLastDayOfMonth().withFirstMillsOfDay().getMillis();
        Date d6 = DateUtils.builder().with(2019,1,31,0,0,0,0).getDate();
        long lastMillsOfDay = DateUtils.builder().withDate(d).withLastDayOfMonth().withLastMillsOfDay().getMillis();
        Date d7 = DateUtils.builder().with(2019,1,31,23,59,59,999).getDate();
        Assert.assertEquals(firstMillsOfDay,d6.getTime());
        Assert.assertEquals(lastMillsOfDay,d7.getTime());
    }


    @Test
    public void dateUtils2Test() {
        long s = Instant.now().toEpochMilli();
        Date d = new Date(s);
        Assert.assertEquals(d.getTime(), s);
    }

    @Test
    public void dateUtilWeekTest() {
      int dw = DateUtils.builder().withFirstDayOfWeek().getDayOfWeek();
      Assert.assertEquals(dw,1);
    }

    @Test
    public void dateUtilWith1Test() {
        int dw = DateUtils.builder().withDate(1L).getYear();
        Assert.assertEquals(dw,1970);
    }

    @Test
    public void dateUtilWith2Test() {
        Date dw = DateUtils.builder().withDate(new Date()).getDate();
        Assert.assertTrue(DateCalculator.isTheSameDay(dw,new Date()));
    }

    @Test
    public void dateUtilWith3Test() {
        Date dw = DateUtils.builder().with(2017,12,23,23,59,59,999).getDate();
        long mills = DateUtils.builder().with(2017,12,23).withLastMillsOfDay().getMillis();
        Assert.assertEquals(dw.getTime(),1514044799999L);
        Assert.assertEquals(dw.getTime(),mills);
    }

    @Test
    public void dateUtilParseTest() {
        Date dw = DateUtils.builder().parse("2017-12-23 23:45:55",DatePattern.DEFAULT_FORMAT_DATETIME_PATTERN).getDate();
        Assert.assertEquals(dw.getTime(),1514043955000L);
    }

    @Test
    public void dateUtilParse2Test() {
        Date dw = DateUtils.builder().parseDate("2017-12-23").getDate();
        Assert.assertEquals(dw.getTime(),1513958400000L);
    }

    @Test
    public void dateUtilParse3Test() {
        Date dw = DateUtils.builder().parseDateTime("2017-12-23 08:00:00").getDate();
        Assert.assertEquals(dw.getTime(),1513987200000L);
    }

    @Test
    public void dateUtilWithTest() {
        Date dw = DateUtils.builder().withYear(2017).withMonth(12).withDayOfMonth(23).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(999).getDate();
        Date dw2 = DateUtils.builder().with(2017,12,23,23,59,59,999).getDate();
        int dw3 = DateUtils.builder().with(2017,12,23,23,59,59,999).withFirstDayOfMonth().getDayOfMonth();
        Assert.assertEquals(dw,dw2);
        Assert.assertEquals(dw3,1);
    }

    @Test
    public void dateUtilWith10Test() {
        int dw = DateUtils.builder().withYear(2017).withMonth(01).withDayOfMonth(01).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(999).getDayOfYear();
        Assert.assertEquals(dw,1);
    }

    @Test
    public void dateUtilPlusTest() {
        Date dw = DateUtils.builder().withYear(2017).withMonth(12).withDayOfMonth(23).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(999).getDate();
        Date dw2 = DateUtils.builder().withDate(dw).plusYears(1).plusMonths(1).plusDays(1).plusHours(1).plusMinutes(1).plusSeconds(1).getDate();
        Date dw3 = DateUtils.builder().with(2019,1,25,1,1,0,999).getDate();
        Assert.assertEquals(dw2,dw3);
    }

    @Test
    public void dateUtilPlusWeekTest() {
        int dw = DateUtils.builder().withYear(2017).withMonth(12).withDayOfMonth(27).plusWeeks(1).getDayOfWeek();
        int dw1 = DateUtils.builder().withYear(2018).withWeekOfYear(1).withDayOfMonth(3).getDayOfWeek();
        Assert.assertEquals(dw,dw1);
    }

    @Test
    public void dateUtilGetTest() {
        int dw = DateUtils.builder().withYear(2017).getYear();
        Assert.assertEquals(2017,dw);
    }
    @Test
    public void dateUtilGet1Test() {
        int dw2 = DateUtils.builder().with(2017,12,23,23,59,59,999).getMonth();
        Assert.assertEquals(dw2,12);
    }

    @Test
    public void dateUtilGet2Test() {
        int dw2 = DateUtils.builder().with(2017,12,23,23,59,59,999).getDayOfMonth();
        Assert.assertEquals(dw2,23);
    }

    @Test
    public void dateUtilGet3Test() {
        int dw2 = DateUtils.builder().with(2017,12,23,23,59,59,999).getHourOfDay();
        Assert.assertEquals(dw2,23);
    }


    @Test
    public void dateUtilGet4Test() {
        int dw2 = DateUtils.builder().with(2017,12,23,23,59,59,999).getMinuteOfHour();
        Assert.assertEquals(dw2,59);
    }


    @Test
    public void dateUtilGet5Test() {
        int dw2 = DateUtils.builder().with(2017,12,23,23,59,59,999).getSecondOfMinute();
        Assert.assertEquals(dw2,59);
    }


    @Test
    public void dateUtilGet6Test() {
        int dw2 = DateUtils.builder().with(2017,12,23,23,59,59,999).getMillsOfSecond();
        Assert.assertEquals(dw2,999);
    }


    @Test
    public void dateUtilFormat1Test() {
        String dw2 = DateUtils.builder().with(2017,12,23,23,59,59,999).formatDate();
        Assert.assertEquals(dw2,"2017-12-23");
    }



    @Test
    public void dateUtilFormat2Test() {
        String dw2 = DateUtils.builder().with(2017,12,23,23,59,59,999).formatDateTime();
        Assert.assertEquals(dw2,"2017-12-23 23:59:59");
    }



    @Test
    public void dateUtilFormat3Test() {
        String dw2 = DateUtils.builder().with(2017,12,23,23,59,59,999).format(DatePattern.DEFAULT_ISO8601_FORMAT);
        Assert.assertEquals(dw2,"2017-12-23T23:59:59.999+0800");
    }

}
