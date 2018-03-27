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
        Assert.assertNotNull(d0);
        Assert.assertNotNull(d1);
        Assert.assertNotNull(d2);
    }

}
