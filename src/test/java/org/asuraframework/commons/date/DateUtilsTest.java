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
}
