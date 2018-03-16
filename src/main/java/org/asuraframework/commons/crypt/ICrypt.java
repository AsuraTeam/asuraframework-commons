/*
 * Copyright (c) 2018. sunkaiyun1206@163.com.
 */
package org.asuraframework.commons.crypt;

import org.asuraframework.commons.util.Check;

/**
 * <p>加解密接口-默认加盐方法</p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2018/3/12 上午1:17
 * @since 1.0
 */
public interface ICrypt {

    /**
     * 默认加盐算法
     *
     * @param inputBytes 待加盐字节数组
     * @param saltBytes  盐字节数组
     *
     * @return 加盐后的字节数组
     */
    default byte[] saltInputEncode(final byte[] inputBytes, final byte[] saltBytes) {
        if (Check.isNullObjects(inputBytes, saltBytes)) {
            return inputBytes;
        }
        int inputLength = inputBytes.length;
        int saltLength = saltBytes.length;
        // 每隔3个字节加一次盐，原字符首位加一次盐
        int newLength = inputLength / CryptConstant.DEFAULT_SALT_OFFSET + 1 + inputLength;
        byte[] newInputBytes = new byte[newLength];
        // 首位添加字节为原字节数组长度除以盐的字节的长度的余数，为查找下标
        newInputBytes[0] = saltBytes[inputLength % saltLength];
        int newInputBytesOffset = 1;
        for (int i = 0; i < inputLength; i++) {
            newInputBytes[newInputBytesOffset] = inputBytes[i];
            if ((i + 1) % CryptConstant.DEFAULT_SALT_OFFSET == 0) {
                newInputBytesOffset++;
                // 当前数组的下标除以盐数组的长度的余数，为查找盐字节数组的下标
                newInputBytes[newInputBytesOffset] = saltBytes[newInputBytesOffset % saltLength];
            }
            newInputBytesOffset++;
        }
        return newInputBytes;
    }

    default byte[] saltInputDecode(final byte[] inputBytes){
        if (Check.isNull(inputBytes)) {
            return inputBytes;
        }
        int inputLength = inputBytes.length;
        // 每隔4个字节加一次盐，原字符首位减一次盐
        int newLength = inputLength - (inputLength - 1) / (CryptConstant.DEFAULT_SALT_OFFSET + 1) - 1;
        byte[] newInputBytes = new byte[newLength];
        int newInputBytesOffset = 0;
        for (int i = 1; i < inputLength; i++) {
            if (i % (CryptConstant.DEFAULT_SALT_OFFSET + 1) == 0) {
                continue;
            }
            newInputBytes[newInputBytesOffset] = inputBytes[i];
            newInputBytesOffset++;
        }
        return newInputBytes;
    }
}
