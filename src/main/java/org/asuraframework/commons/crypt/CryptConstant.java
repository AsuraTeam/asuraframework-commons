/*
 * Copyright (c) 2018. sunkaiyun1206@163.com.
 */
package org.asuraframework.commons.crypt;

/**
 * <p>加解密常量</p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2018/3/11 下午11:17
 * @since 1.0
 */
public class CryptConstant {

    private CryptConstant() {

    }

    /**
     * 默认编码
     */
    public static final String DEFAULT_CHARSET_NAME = "UTF-8";

    /**
     * 加密算法
     */
    public static final String MD5_CRYPT = "MD5";

    public static final String AES_CRYPT = "AES";

    public static final String DES_CRYPT = "DES";

    /**
     * 默认加盐字节数组
     */
    public static final byte[] DEFAULT_SALT_BYTE = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n'
            , 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'
            , 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3'
            , '4', '5', '6', '7', '8', '9'};

    /**
     * 默认加盐偏移量
     */
    public static final int DEFAULT_SALT_OFFSET = 3;

    /**
     * AES 加密模式
     */
    public static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * DES 加密模式
     */
    public static final String DES_ALGORITHM = "DES/CBC/PKCS5Padding";

}
