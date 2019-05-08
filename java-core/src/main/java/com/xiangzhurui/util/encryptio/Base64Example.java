package com.xiangzhurui.util.encryptio;

import org.apache.commons.codec.binary.Base64;

/**
 * 使用commons codec 进行Base64编码解码
 *
 * @author XiangZhuRui
 */
public class Base64Example {
    static Base64 base64 = new Base64();

    public static String encodeStr(String plainText) {
        byte[] b = plainText.getBytes();

        b = base64.encode(b);
        String s = new String(b);
        return s;
    }

    public static String decodeStr(String encodeStr) {
        byte[] b = encodeStr.getBytes();
        b = base64.decode(b);
        String s = new String(b);
        return s;
    }
}
