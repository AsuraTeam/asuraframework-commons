/**
 * Copyright 2018 asura
 */
package org.asuraframework.commons.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 由于对比 apache BeanUtils，Spring BeanUtils 和 Cglib BeanCopier性能，Cglib BeanCopier优势明显
 * Bean拷贝，核心依据Cglib BeanCopier实现
 * </p>
 *
 * @author liusq23
 * @version 1.0
 * @since 1.0
 */
public class AsuraBeanUtils {

    /**
     * 执行属性拷贝
     *
     * @param k     源对象
     * @param clazz 目标对象类型Class
     * @param <T>
     * @param <K>
     * @return
     * @throws BeanUtilsCopyException
     */
    public static <T, K> T copyProperties(K k, Class<T> clazz) throws BeanUtilsCopyException {
        if (Objects.isNull(k)) {
            return null;
        }
        try {
            T t = clazz.getConstructor().newInstance();
            copyProperties(k, t);
            return t;
        } catch (Exception e) {
            throw new BeanUtilsCopyException(e);
        }
    }

    /**
     * 执行属性拷贝
     *
     * @param ks    源对象列表
     * @param clazz 目标对象类型Class
     * @param <T>
     * @param <K>
     * @return
     * @throws BeanUtilsCopyException
     */
    public static <T, K> List<T> copyProperties(List<K> ks, Class<T> clazz) throws BeanUtilsCopyException {
        if (Check.isNullOrEmpty(ks)) {
            return new ArrayList<>();
        }
        try {
            if (Check.isNullOrEmpty(ks)) {
                return new ArrayList<>();
            }
            List<T> ts = new ArrayList<>(ks.size());
            for (K k : ks) {
                T t = copyProperties(k, clazz);
                ts.add(t);
            }
            return ts;
        } catch (Exception e) {
            throw new BeanUtilsCopyException(e);
        }
    }

    /**
     * 执行属性拷贝，依据cglib BeanCopier实现
     *
     * @param k   源对象
     * @param t   接收的目标对象
     * @param <T>
     * @param <K>
     * @return
     * @throws BeanUtilsCopyException
     */
    public static <T, K> void copyProperties(K k, T t) {
        if (!Check.isNull(k)) {
            // TODO copy

        }
    }

}
