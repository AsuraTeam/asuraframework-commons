/*
 * Copyright (c) 2018. sunkaiyun1206@163.com.
 */
package org.asuraframework.commons.crypt;

import org.junit.Assert;
import org.junit.Test;


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
 * @date 2018/3/12 下午11:05
 * @since 1.0
 */
public class MD5CryptTest {

    @Test
    public void getInstance() {
        Assert.assertNotEquals(MD5Crypt.getInstance(), null);
    }

    @Test
    public void encrypt() {
        MD5Crypt instance = MD5Crypt.getInstance();
        String input = "123456";
        String encrypt = instance.encrypt(input);
        System.out.println(encrypt);
        Assert.assertEquals(encrypt, "e10adc3949ba59abbe56e057f20f883e");
    }

    @Test
    public void encryptByDefaultSalt() {
        MD5Crypt instance = MD5Crypt.getInstance();
        String input = "123456";
        String encrypt = instance.encryptByDefaultSalt(input);
        System.out.println(encrypt);
        Assert.assertEquals(encrypt, "6adf9c2c8de9fbd3f880c8f8458324d9");
    }

    @Test
    public void encryptBySalt() {
        MD5Crypt instance = MD5Crypt.getInstance();
        String input = "123456";
        String salt = "789";
        String encrypt = instance.encryptBySalt(input, salt);
        System.out.println(encrypt);
        Assert.assertEquals(encrypt, "5cbe85841901419c9fe484be85415637");
    }
}