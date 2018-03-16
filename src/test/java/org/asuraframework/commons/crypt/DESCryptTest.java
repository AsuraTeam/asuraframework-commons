/*
 * Copyright (c) 2018. sunkaiyun1206@163.com.
 */
package org.asuraframework.commons.crypt;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2018/3/12 下午10:52
 * @since 1.0
 */
public class DESCryptTest {

    @Test
    public void getInstance() {
        Assert.assertNotEquals(DESCrypt.getInstance(), null);
    }

    @Test
    public void encrypt() {
        DESCrypt instance = DESCrypt.getInstance();
        String input = "123456";
        String keyString = "asdfghjklzxcvbnm";
        String ivString = "asdfghjk";
        String encrypt = instance.encrypt(input, keyString, ivString);
        System.out.println(encrypt);
        Assert.assertEquals(encrypt, "772edd2af8c56d8c");
    }

    @Test
    public void encryptByDefaultSalt() {
        DESCrypt instance = DESCrypt.getInstance();
        String input = "123456";
        String keyString = "asdfghjklzxcvbnm";
        String ivString = "asdfghjk";
        String encrypt = instance.encryptByDefaultSalt(input, keyString, ivString);
        System.out.println(encrypt);
        Assert.assertEquals(encrypt, "a24cf99416517e68304396a622b7415c");
    }

    @Test
    public void encryptBySalt() {
        DESCrypt instance = DESCrypt.getInstance();
        String input = "123456";
        String keyString = "asdfghjklzxcvbnm";
        String ivString = "asdfghjk";
        String salt = "qazxswedc";
        String encrypt = instance.encryptBySalt(input, keyString, ivString, salt);
        System.out.println(encrypt);
        Assert.assertEquals(encrypt, "04bc09ca807259284e7a41a868288a7e");
    }

    @Test
    public void decrypt() {
        DESCrypt instance = DESCrypt.getInstance();
        String input = "772edd2af8c56d8c";
        String keyString = "asdfghjklzxcvbnm";
        String ivString = "asdfghjk";
        String encrypt = instance.decrypt(input, keyString, ivString);
        System.out.println(encrypt);
        Assert.assertEquals(encrypt, "123456");
    }

    @Test
    public void decryptBySalt() {
        DESCrypt instance = DESCrypt.getInstance();
        String input = "a24cf99416517e68304396a622b7415c";
        String keyString = "asdfghjklzxcvbnm";
        String ivString = "asdfghjk";
        String encrypt = instance.decryptBySalt(input, keyString, ivString, true);
        System.out.println(encrypt);
        Assert.assertEquals(encrypt, "123456");
    }
}