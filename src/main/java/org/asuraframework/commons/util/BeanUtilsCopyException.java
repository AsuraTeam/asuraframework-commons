/**
 * Copyright 2018 asura
 */
package org.asuraframework.commons.util;

/**
 * <p>
 *     bean copy异常处理，继承RuntimeException
 * </p>
 *
 * @author liusq23
 * @since 1.0
 * @version 1.0
 */
public class BeanUtilsCopyException extends RuntimeException{


    private static final long serialVersionUID = 4711428559616923417L;

    /**
     * 构造器
     */
    public BeanUtilsCopyException() {
        super();
    }

    /**
     * 构造器
     *
     * @param message 异常详细信息
     * @param cause   异常原因
     */
    public BeanUtilsCopyException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造器
     *
     * @param message 异常详细信息
     */
    public BeanUtilsCopyException(String message) {
        super(message);
    }

    /**
     * 构造器
     *
     * @param cause 异常原因
     */
    public BeanUtilsCopyException(Throwable cause) {
        super(cause);
    }
}
