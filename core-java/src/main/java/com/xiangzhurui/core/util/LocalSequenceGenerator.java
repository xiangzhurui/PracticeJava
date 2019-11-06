package com.xiangzhurui.core.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * @author xiangzhurui
 * @version 2019-01-05 14:00
 */
public class LocalSequenceGenerator {

  protected static final String SEQ_DATE_PATTERN = "yyyyMMddHHmmssSSS";
  public static final DateTimeFormatter MDDMM_H_HSS_SSS = DateTimeFormatter
      .ofPattern(SEQ_DATE_PATTERN)
      .withResolverStyle(ResolverStyle.STRICT);

  private LocalSequenceGenerator() {
  }

  public static String timeSeq() {
    return LocalDateTime.now().format(MDDMM_H_HSS_SSS);
  }

  public static String timeSeq(String prefix) {
    return prefix + LocalDateTime.now().format(MDDMM_H_HSS_SSS);
  }
}
