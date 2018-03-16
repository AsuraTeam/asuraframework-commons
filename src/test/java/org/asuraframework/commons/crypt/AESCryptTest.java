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
 * @date 2018/3/12 上午9:31
 * @since 1.0
 */
public class AESCryptTest {

    @Test
    public void getInstance() {
        Assert.assertNotEquals(AESCrypt.getInstance(), null);
    }

    @Test
    public void encrypt() {
        AESCrypt instance = AESCrypt.getInstance();
        String input = "123456";
        String keyString = "asdfghjklzxcvbnm";
        String encrypt = instance.encrypt(input, keyString);
        System.out.println(encrypt);
        Assert.assertEquals(encrypt, "d9358bc7ccf236b04759f1aa593959fe");
    }

    @Test
    public void encryptByDefaultSalt() {
        AESCrypt instance = AESCrypt.getInstance();
        String input = "123456";
        String keyString = "asdfghjklzxcvbnm";
        String encrypt = instance.encryptByDefaultSalt(input, keyString);
        System.out.println(encrypt);
        Assert.assertEquals(encrypt, "f6f0b69ac9c0020a3ad668b719f07299");
    }

    @Test
    public void encryptBySalt() {
        AESCrypt instance = AESCrypt.getInstance();
        String input = "123456";
        String keyString = "asdfghjklzxcvbnm";
        String salt = "789";
        String encrypt = instance.encryptBySalt(input, keyString, salt);
        System.out.println(encrypt);
        Assert.assertEquals(encrypt, "f3a301acbca795126166defebe2ea41e");
    }

    @Test
    public void decrypt(){
        AESCrypt instance = AESCrypt.getInstance();
        String input = "d9358bc7ccf236b04759f1aa593959fe";
        String keyString = "asdfghjklzxcvbnm";
        String decrypt = instance.decrypt(input, keyString);
        System.out.println(decrypt);
        Assert.assertEquals(decrypt, "123456");
    }

    @Test
    public void decryptBySalt() {
        AESCrypt instance = AESCrypt.getInstance();
        String input = "f6f0b69ac9c0020a3ad668b719f07299";
        String keyString = "asdfghjklzxcvbnm";
        String decrypt = instance.decryptBySalt(input, keyString, true);
        System.out.println(decrypt);
        Assert.assertEquals(decrypt, "123456");
    }
}