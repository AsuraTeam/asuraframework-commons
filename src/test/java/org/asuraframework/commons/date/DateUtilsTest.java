/**
 * Copyright(c) 2018 asura
 */
package org.asuraframework.commons.date;

import org.junit.Assert;
import org.junit.Test;

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
        Date d2 = DateUtils.builder().withDate(d).plus(24*3600*1000*20L).getDate();
        Date d3 = DateUtils.builder().with(2019,1,21,0,0,0,0).getDate();
        Date d5 = DateUtils.builder().withDate(d).plus(-24*3600*1000*20L).getDate();
        Date d4 = DateUtils.builder().with(2018,12,12,0,0,0,0).getDate();
        Assert.assertEquals(d5.getTime(), d4.getTime());
        Assert.assertEquals(d2.getTime(), d3.getTime());
        int dayOfMonth1 = DateUtils.builder().withDate(d).withWeekOfYear(1).withFirstDayOfWeek().getDayOfMonth();
        Assert.assertEquals(dayOfMonth1,30);
        int dayOfMonth2 = DateUtils.builder().withDate(d).withWeekOfYear(1).withLastDayOfWeek().getDayOfMonth();
        Assert.assertEquals(dayOfMonth2,5);
        long firstMillsOfDay = DateUtils.builder().withDate(d).withLastDayOfMonth().withFirstMillsOfDay().getMillis();
        Date d6 = DateUtils.builder().with(2019,1,31,0,0,0,0).getDate();
        long lastMillsOfDay = DateUtils.builder().withDate(d).withLastDayOfMonth().withLastMillsOfDay().getMillis();
        Date d7 = DateUtils.builder().with(2019,1,31,23,59,59,999).getDate();
        Assert.assertEquals(firstMillsOfDay,d6.getTime());
        Assert.assertEquals(lastMillsOfDay,d7.getTime());
    }
}
