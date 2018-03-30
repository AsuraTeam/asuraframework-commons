/**
 * Copyright(c) 2018 asura
 */
package org.asuraframework.commons.date;

import org.asuraframework.commons.util.Check;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Date;
import java.util.Objects;

/**
 * <p></p>
 *
 * @author liusq23
 * @version 1.0
 * @Date 2018/3/24 下午10:51
 * @since 1.0
 */
public class DateParser {

    /**
     * 私有化构造
     */
    private DateParser() {
    }

    /**
     * 按照默认的日期格式(yyyy-MM-dd)转换为日期
     *
     * @param dateStr
     * @return
     */
    public static Date parseDate(@Nullable String dateStr) {
        return parse(dateStr, DatePattern.DEFAULT_FORMAT_DATE_PATTERN);
    }

    /**
     * 按照默认的日期格式(yyyy-MM-dd HH:mm:ss)转换为日期
     *
     * @param dateStr
     * @return
     */
    public static Date parseDateTime(@Nullable String dateStr) {
        return parse(dateStr, DatePattern.DEFAULT_FORMAT_DATETIME_PATTERN);
    }

    /**
     * 按照指定格式匹配解析字符串为日期
     * 如果需要解析的字符串为空或者null 则返回的null
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date parse(@Nullable String dateStr, @Nonnull String pattern) {
        if (Check.isNullOrEmpty(dateStr)) {
            return null;
        }
        Objects.requireNonNull(pattern, "date pattern must not null");
        return DateUtils.builder().parse(dateStr, pattern).getDate();
    }
}
