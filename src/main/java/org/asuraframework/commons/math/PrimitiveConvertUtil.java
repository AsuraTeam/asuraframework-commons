/*
 * Copyright (c) 2018. sunkaiyun1206@163.com.
 */
package org.asuraframework.commons.math;

import org.asuraframework.commons.util.Check;

/**
 * <p>原始数据类型转换</p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2018/3/20 上午10:21
 * @since 1.0
 */
public class PrimitiveConvertUtil {

    private PrimitiveConvertUtil() {

    }

    /**
     * 将字符串转化为int类型数值, 若转化失败, 则返回为默认的转化值
     *
     * @param s
     * @param defaultValue
     *
     * @return
     */
    public static int parseInt(final String s, final int defaultValue) {
        return parseInt(s, defaultValue, 10);
    }

    /**
     * 将字符串转化为int类型数值, 若转化失败, 则返回为默认的转化值
     *
     * @param s
     * @param defaultValue
     * @param radix        进制
     *
     * @return
     */
    public static int parseInt(final String s, final int defaultValue, final int radix) {
        int result = defaultValue;

        if (Check.isStrictNullOrEmpty(s)) {
            return result;
        }

        try {
            result = Integer.parseInt(s, 10);
        } catch (final NumberFormatException ignore) {
        }
        return result;
    }

    /**
     * 将字符串转化为long类型数值, 若转化失败, 则返回为默认的转化值
     *
     * @param s
     * @param defaultValue
     *
     * @return
     */
    public static long parseLong(final String s, final long defaultValue) {
        return parseLong(s, defaultValue, 10);
    }

    /**
     * 将字符串转化为long类型数值, 若转化失败, 则返回为默认的转化值
     *
     * @param s
     * @param defaultValue
     * @param radix        进制
     *
     * @return
     */
    public static long parseLong(final String s, final long defaultValue, final int radix) {
        long result = defaultValue;

        if (s == null || s.trim().isEmpty()) {
            return result;
        }

        try {
            result = Long.parseLong(s, 10);
        } catch (final NumberFormatException ignore) {
        }
        return result;
    }
}
