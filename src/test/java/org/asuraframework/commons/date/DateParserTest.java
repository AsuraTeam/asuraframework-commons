/**
 * Copyright(c) 2018 asura
 */
package org.asuraframework.commons.date;

import org.junit.Assert;
import org.junit.Test;

import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * <p></p>
 *
 *
 * @author liusq23
 * @since 1.0
 * @version 1.0
 * @Date 2018/3/27 下午11:45
 */
public class DateParserTest {

    @Test
    public void parseTest() {
        Date d0 = DateParser.parse("1970", "yyyy");
        Date d1 = DateParser.parse("1986-05", "yyyy-MM");
        Date d2 = DateParser.parse("19860504", "yyyyMMdd");
        Date d3 = DateParser.parse("19860504 00", "yyyyMMdd HH");
        Date d4 = DateParser.parse("19860504 00:00", "yyyyMMdd HH:mm");
        Date d5 = DateParser.parse("19860504 00:00:00", "yyyyMMdd HH:mm:ss");
        Assert.assertNotNull(d0);
        Assert.assertNotNull(d1);
        Assert.assertNotNull(d2);
        Assert.assertNotNull(d3);
        Assert.assertNotNull(d4);
        Assert.assertNotNull(d5);
    }

    @Test
    public void parse2Test() {
        Date d0 = DateParser.parseDate("1986-05-04");
        Date d1 = DateParser.parseDate(null);
        Date d2 = DateParser.parseDateTime("1986-05-04 00:00:00");
        Assert.assertNotNull(d0);
        Assert.assertNotNull(d2);
        Assert.assertNull(d1);
    }

    @Test(expected = DateTimeParseException.class)
    public void parse3Test() {
        Date d0 = DateParser.parseDate("asdasdsd");
        Assert.assertNotNull(d0);
    }
}
