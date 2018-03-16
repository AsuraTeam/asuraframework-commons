/*
 * Copyright (c) 2018. sunkaiyun1206@163.com.
 */
package org.asuraframework.commons.crypt;

import org.apache.commons.codec.binary.Hex;
import org.asuraframework.commons.util.Check;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>MD5 加密</p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2018/3/11 下午11:55
 * @since 1.0
 */
public class MD5Crypt implements ICrypt {

    //public static final Logger

    private static volatile MD5Crypt md5Crypt;

    private MD5Crypt() {

    }

    public static MD5Crypt getInstance() {
        if (Check.isNull(md5Crypt)) {
            synchronized (MD5Crypt.class) {
                if (Check.isNull(md5Crypt)) {
                    md5Crypt = new MD5Crypt();
                }
            }
        }
        return md5Crypt;
    }

    /**
     * 加密
     *
     * @param input 待加密字符串
     *
     * @return 加密后字符串
     */
    public String encrypt(final String input) {
        return this.encryptBySalt(input, null);
    }

    /**
     * 加密
     *
     * @param input 待加密字符串
     *
     * @return 加密后字符串
     */
    public String encryptByDefaultSalt(final String input) {
        try {
            return this.encryptBySalt(input, new String(CryptConstant.DEFAULT_SALT_BYTE, CryptConstant.DEFAULT_CHARSET_NAME));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密-默认加盐
     *
     * @param input 待加密字符串
     * @param salt  盐（指定加盐字符串）
     *
     * @return 加密后的字符串
     */
    public String encryptBySalt(final String input, final String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance(CryptConstant.MD5_CRYPT);

            byte[] saltEncodeBytes;
            if (Check.isStrictNullOrEmpty(salt)) {
                saltEncodeBytes = input.getBytes(CryptConstant.DEFAULT_CHARSET_NAME);
            } else {
                saltEncodeBytes = saltInputEncode(input.getBytes(CryptConstant.DEFAULT_CHARSET_NAME), salt.getBytes(CryptConstant.DEFAULT_CHARSET_NAME));
            }

            byte[] encodeBytes = md.digest(saltEncodeBytes);

            return Hex.encodeHexString(encodeBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
