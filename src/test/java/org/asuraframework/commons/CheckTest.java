/**
 * @FileName: CheckTest.java
 * @Package: org.asuraframework.commons
 * @author liusq23
 * @created 2018/3/1 下午5:13
 * <p>
 * Copyright 2018 asura
 */
package org.asuraframework.commons;

import org.asuraframework.commons.util.Check;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 *     @see org.asuraframework.commons.util.Check 测试用例
 *
 * </p>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author liusq23
 * @since 1.0
 * @version 1.0
 */
public class CheckTest {

    @Test
    public void nullObjectsTest(){
        Assert.assertTrue(Check.isNull(null));
        Assert.assertTrue(!Check.isNull(new String()));
        Assert.assertTrue(Check.isNullObjects(null));
        Assert.assertTrue(Check.isNullObjects(new Object[]{}));
        Assert.assertTrue(Check.isNullObjects(new Object[2]));
        Assert.assertTrue(!Check.isNullObjects(new Object[]{"A","B"}));
    }

    @Test
    public void nullStringTest(){
        String str = null;
        Assert.assertTrue(Check.isNullOrEmpty(new String()));
        Assert.assertTrue(Check.isNullOrEmpty(""));
        Assert.assertTrue(!Check.isNullOrEmpty(" "));
        Assert.assertTrue(Check.isNullOrEmpty("   ".trim()));
        Assert.assertTrue(Check.isNullOrEmpty(str));
        Assert.assertTrue(Check.isNullOrEmpty()); //使用的是数组方法
        Assert.assertTrue(Check.isNullOrEmpty(new String[]{}));
        Assert.assertTrue(!Check.isNullOrEmpty(new String[]{"A","B"}));
        Assert.assertTrue(Check.isNullOrEmpty(new String[2]));
        Assert.assertTrue(Check.isStrictNullOrEmpty(""));
        Assert.assertTrue(Check.isStrictNullOrEmpty(null));
        Assert.assertTrue(!Check.isStrictNullOrEmpty("A"));
        Assert.assertTrue(Check.isStrictNullOrEmpty("   "));
    }

    @Test
    public void nullCollectionTest(){
        Assert.assertTrue(Check.isNullOrEmpty(new ArrayList()));
        Assert.assertTrue(Check.isNullOrEmpty(new HashMap()));
    }
}
