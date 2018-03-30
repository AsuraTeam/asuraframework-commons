/**
 * Copyright 2018 asura
 */
package org.asuraframework.commons.util;

import net.sf.cglib.beans.BeanCopier;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 由于对比 apache BeanUtils，Spring BeanUtils 和 Cglib BeanCopier性能，Cglib BeanCopier优势明显
 * Bean拷贝，核心依据Cglib BeanCopier实现
 * 1、目标对象属性名称相同，类型不一样无法copy
 * 2、源对象相对目标对象多余的属性自动忽略，不会异常
 * 3、目标对象相对源对象多余的属性会默认null，不会异常
 * </p>
 *
 * @author liusq23
 * @version 1.0
 * @since 1.0
 */
public class AsuraBeanUtils {

    /**
     * BeanCopier Map
     */
    private static final ConcurrentHashMap<String, BeanCopier> BEAN_COPIER_MAP = new ConcurrentHashMap<>();


    private AsuraBeanUtils() {
    }

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
    public static <T, K> T copyProperties(@Nonnull K k, Class<T> clazz) {
        Objects.requireNonNull(k);
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
    public static <T, K> List<T> copyProperties(List<K> ks, Class<T> clazz) {
        if (Check.isNullOrEmpty(ks)) {
            return new ArrayList<>();
        }
        List<T> ts = new ArrayList<>(ks.size());
        for (K k : ks) {
            T t = copyProperties(k, clazz);
            ts.add(t);
        }
        return ts;
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
    public static <T, K> void copyProperties(@Nonnull K k, @Nonnull T t) {
        Objects.requireNonNull(k);
        Objects.requireNonNull(t);
        BeanCopier copier = getBeanCopier(k.getClass(), t.getClass(), false);
        copier.copy(k, t, null);
    }

    /**
     * 这里不考虑多线程创建BeanCopier问题
     *
     * @param originClass
     * @param targetClass
     * @param useConverter
     * @return
     */
    public static BeanCopier getBeanCopier(Class<?> originClass, Class<?> targetClass, boolean useConverter) {
        String beanCopierKey = originClass.getName() + "#" + targetClass.getName() + "#" + useConverter;
        BeanCopier beanCopier = BEAN_COPIER_MAP.get(beanCopierKey);
        if (Check.isNull(beanCopier)) {
            beanCopier = BeanCopier.create(originClass, targetClass, useConverter);
            BEAN_COPIER_MAP.putIfAbsent(beanCopierKey, beanCopier);
        }
        return beanCopier;
    }

}
