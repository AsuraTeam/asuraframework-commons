/**
 * Copyright 2018 asura
 */
package org.asuraframework.commons.util;

import com.google.common.base.Strings;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * 检查工具类
 * </p>
 *
 * @author liusq23
 * @version 1.0
 * @since 1.0
 */
public class Check {

    /**
     * 工具类:私有化构造
     */
    private Check() {
    }

    /**
     * 判断字符串是否为空
     * 注意空字符串长度不为0 返回false
     *
     * @param str
     * @return true:空字符串，false：非空字符串
     */
    public static boolean isNullOrEmpty(@Nullable String str) {
        return Strings.isNullOrEmpty(str);
    }

    /**
     * 严格判断字符串是否为空
     * 注意空字符串长度不为0 返回true
     *
     * @param str
     * @return true:空字符串，false：非空字符串
     */
    public static boolean isStrictNullOrEmpty(@Nullable String str) {
        if (Strings.isNullOrEmpty(str)) {
            return true;
        }
        return str.trim().length() == 0;
    }

    /**
     * 判断字符串是否为空
     * 1、判断字符串数组对象是否为null
     * 2、判断字符串数组对象里面的元素是否存在null
     * 3、如果字符串数组长度为0且本身不为null，返回也是false
     * @param strs
     * @return true:空字符串，false：非空字符串
     */
    public static boolean isNullOrEmpty(@Nullable String... strs) {
        if (strs.length == 0) {
            return true;
        }
        for (String str : strs) {
            if (isNullOrEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断集合是否为空
     *
     * @param collection
     * @return true:空集合，false：非空集合
     */
    public static boolean isNullOrEmpty(@Nullable Collection collection) {
        return isNull(collection) || collection.isEmpty();
    }

    /**
     * 判断映射表是否为空
     *
     * @param map
     * @return true:空map false:非空map
     */
    public static boolean isNullOrEmpty(@Nullable Map map) {
        return isNull(map) || map.isEmpty();
    }

    /**
     * 判断是否非空对象
     *
     * @param obj
     * @return true:空对象，false：非空对象
     */
    public static boolean isNull(@Nullable Object obj) {
        if (obj == null) {
            return true;
        }
        return false;
    }

    /**
     * 判断数组内是否有非空对象
     * 1、判断数组对象是否为null
     * 2、判断数组对象里面的元素是否存在null
     * 3、如果数组长度为0且本身不为null，返回也是false
     *
     * @param objs
     * @return true:空对象，false：非空对象
     */
    public static boolean isNullObjects(@Nullable Object... objs) {
        if (isNull(objs)) {
            return true;
        }
        if(objs.length == 0){
            return true;
        }
        for (Object obj : objs) {
            if (isNull(obj)) {
                return true;
            }
        }
        return false;
    }
}
