package com.xiangzhurui.core.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Utils {

  private Base64Utils() {
  }

  private static final Base64.Decoder DECODER = Base64.getDecoder();

  private static final Base64.Encoder ENCODER = Base64.getEncoder();

  public static byte[] encode(String text) throws UnsupportedEncodingException {
    final byte[] textByte = text.getBytes("UTF-8");
    return ENCODER.encode(textByte);
  }

  public static String encodeToString(String text) throws UnsupportedEncodingException {
    final byte[] textByte = text.getBytes("UTF-8");
    final String encodedText = ENCODER.encodeToString(textByte);
    return encodedText;
  }

  public static String decodeToString(String text) throws UnsupportedEncodingException {
    return new String(decode(text), "UTF-8");
  }

  public static byte[] decode(String text) {
    byte[] decode = DECODER.decode(text);
    return decode;
  }

}
