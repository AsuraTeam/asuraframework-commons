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
 * @Date 2018/3/28 上午12:19
 * @since 1.0
 */
public class DateFormatterTest {

    @Test
    public void formatterTest() {
        String d0 = DateFormatter.format(new Date(), "yyyy");
        String d1 = DateFormatter.format(new Date(), "yyyyMM");
        String d2 = DateFormatter.format(new Date(), "yyyyMMdd");
        String d3 = DateFormatter.format(new Date(), "yyyyMMdd HH");
        String d4 = DateFormatter.format(new Date(), "yyyyMMdd HH:mm");
        String d5 = DateFormatter.format(new Date(), "yyyyMMdd HH:mm:ss");
        String d6 = DateFormatter.format(new Date(), "yyyyMMdd HH:mm:ss.SSS");
        String d7 = DateFormatter.format(new Date(), DatePattern.DEFAULT_ISO8601_FORMAT);
        Assert.assertNotNull(d0);
        Assert.assertNotNull(d1);
        Assert.assertNotNull(d2);
        Assert.assertNotNull(d3);
        Assert.assertNotNull(d4);
        Assert.assertNotNull(d5);
        Assert.assertNotNull(d6);
        Assert.assertNotNull(d7);
    }

}
