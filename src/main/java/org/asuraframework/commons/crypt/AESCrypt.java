/*
 * Copyright (c) 2018. sunkaiyun1206@163.com.
 */
package org.asuraframework.commons.crypt;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.asuraframework.commons.util.Check;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * <p>AES-加密</p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2018/3/12 上午1:26
 * @since 1.0
 */
public class AESCrypt implements ICrypt {

    private static volatile AESCrypt aesCrypt;

    private AESCrypt() {

    }

    public static AESCrypt getInstance() {
        if (Check.isNull(aesCrypt)) {
            synchronized (AESCrypt.class) {
                if (Check.isNull(aesCrypt)) {
                    aesCrypt = new AESCrypt();
                }
            }
        }
        return aesCrypt;
    }

    /**
     * AES 加密
     *
     * @param input     待加密字符串
     * @param keyString 秘钥
     *
     * @return 加密后字符串
     */
    public String encrypt(final String input, final String keyString) {
        return this.encryptBySalt(input, keyString, null);
    }

    /**
     * AES 加密（默认加盐）
     *
     * @param input     待加密字符串
     * @param keyString 秘钥
     *
     * @return 加密后字符串
     */
    public String encryptByDefaultSalt(final String input, final String keyString) {
        try {
            return this.encryptBySalt(input, keyString, new String(CryptConstant.DEFAULT_SALT_BYTE, CryptConstant.DEFAULT_CHARSET_NAME));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES 加密（指定加盐字符串）
     *
     * @param input     待加密字符串
     * @param keyString 秘钥
     *
     * @return 加密后字符串
     */
    public String encryptBySalt(final String input, final String keyString, final String salt) {
        if (Check.isNullOrEmpty(input, keyString)) {
            return null;
        }
        try {
            Key key = new SecretKeySpec(keyString.getBytes(CryptConstant.DEFAULT_CHARSET_NAME), CryptConstant.AES_CRYPT);
            Cipher cipher = Cipher.getInstance(CryptConstant.AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] saltEncodeBytes;
            if (Check.isStrictNullOrEmpty(salt)) {
                saltEncodeBytes = input.getBytes(CryptConstant.DEFAULT_CHARSET_NAME);
            } else {
                saltEncodeBytes = saltInputEncode(input.getBytes(CryptConstant.DEFAULT_CHARSET_NAME), salt.getBytes(CryptConstant.DEFAULT_CHARSET_NAME));
            }
            byte[] encodeBytes = cipher.doFinal(saltEncodeBytes);
            return Hex.encodeHexString(encodeBytes).trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * DES 解密（指定加盐字符串）
     *
     * @param input     待解密字符串
     * @param keyString 秘钥
     *
     * @return 解密后字符串
     */
    public String decrypt(final String input, final String keyString) {
        return this.decryptBySalt(input, keyString, false);
    }

    /**
     * DES 解密（指定加盐字符串）
     *
     * @param input     待解密字符串
     * @param keyString 秘钥
     * @param isSalt    是否有盐
     *
     * @return 解密后字符串
     */
    public String decryptBySalt(final String input, final String keyString, final Boolean isSalt) {
        if (Check.isNullOrEmpty(input, keyString)) {
            return null;
        }
        try {
            Key key = new SecretKeySpec(keyString.getBytes(CryptConstant.DEFAULT_CHARSET_NAME), CryptConstant.AES_CRYPT);
            Cipher cipher = Cipher.getInstance(CryptConstant.AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] decodeHexBytes = Hex.decodeHex(input.toCharArray());
            byte[] decodeBytes = cipher.doFinal(decodeHexBytes);
            byte[] saltDecodeBytes;

            if (Check.isNull(isSalt) || isSalt.booleanValue() == false) {
                saltDecodeBytes = decodeBytes;
            } else {
                saltDecodeBytes = saltInputDecode(decodeBytes);
            }
            return new String(saltDecodeBytes, CryptConstant.DEFAULT_CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (DecoderException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;
    }
}
