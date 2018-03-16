/**
 * @FileName: SnowflakeTest.java
 * @Package: org.asuraframework.commons
 * @author liusq23
 * @created 2018/3/11 下午4:39
 * <p>
 * Copyright 2018 asura
 */
package org.asuraframework.commons;

import org.asuraframework.commons.algorithm.Snowflake;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p></p>
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
public class SnowflakeTest {

    /**
     * 测试生成1W个，平均每毫米秒可以生成个数
     */
    @Test
    public void nextIdTest() {
        Snowflake snowflake = new Snowflake(10, 12, 41, 13);
        long t = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            snowflake.nextId();
        }
        long d = System.currentTimeMillis();
        Assert.assertTrue(100000/(d-t) > 1);
    }

    @Test
    public void nextIdTest2() {
        Snowflake snowflake = new Snowflake(10, 12, 41, 13);
        long t = System.currentTimeMillis();
        long current = t-Snowflake.POSITION_TIME;
        long nextId = (current << 22) | (13 << 12) | 0;
        long nextId2 = snowflake.nextId();
        Assert.assertTrue(nextId2>=nextId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nextIdTest3() {
        new Snowflake(10, 12, 67, 13);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nextIdTest4() {
        new Snowflake(-1, 12, 67, 13);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nextIdTest5() {
        new Snowflake(1, -1, 67, 13);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nextIdTest6() {
        new Snowflake(1, 1, -1, 13);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nextIdTest7() {
        new Snowflake(1, 12, 41, 3);
    }

    @Test(expected = RuntimeException.class)
    public void nextIdTest8() {
        Snowflake snowflake = new Snowflake(10, 12, 2, 3);
        snowflake.nextId();
    }
}
