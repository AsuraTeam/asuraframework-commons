/**
 * Copyright(c) 2018 asura
 */
package org.asuraframework.commons.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p></p>
 *
 *
 * @author liusq23
 * @since 1.0
 * @version 1.0
 * @Date 2018/7/7 下午11:49
 */
public class MoneyUtilTest {

    @Test
    public void testGet(){
       double d =  MoneyUtil.builder().get();
       Assert.assertEquals(Double.toString(d),"0.0");
    }

    @Test
    public void testFormat01(){
        double d =  MoneyUtil.builder().with(3.759695d).round(3).get();
        String d1 =  MoneyUtil.builder().with(3.759695d).round(3).formatWithPrefix();
        Assert.assertEquals(Double.toString(d),"3.76");
        Assert.assertEquals(d1,"¥3.76");
    }

    @Test
    public void testFormat(){
        double d =  MoneyUtil.builder().with(3.759695d).round(3).get();
        String d1 =  MoneyUtil.builder().with(3.759695d).round(3).format("$%s元");
        Assert.assertEquals(Double.toString(d),"3.76");
        Assert.assertEquals(d1,"$3.76元");
    }

    @Test
    public void testDivide(){
        double d =  MoneyUtil.builder().with(10.1d).divide(3.3,3).get();
        Assert.assertEquals(Double.toString(d),"3.061");
    }

    @Test
    public void testMultiply(){
        double d =  MoneyUtil.builder().with(10.1d).multiply(3.312).round(2).get();
        Assert.assertEquals(Double.toString(d),"33.45");
    }
}
