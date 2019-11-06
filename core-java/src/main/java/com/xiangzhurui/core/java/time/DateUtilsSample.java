package com.xiangzhurui.core.java.time;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

import lombok.extern.slf4j.Slf4j;

/**
 * apache commons lang DateUtils
 *
 * @author xiangzhurui
 * @version 2018/3/22
 * @see org.apache.commons.lang3.time.DateUtils
 */
@Slf4j
public class DateUtilsSample {

  public static void main(String[] args) throws ParseException {
    parseDate();
    addDays();
  }

  private static void parseDate() throws ParseException {
    Date date1 = DateUtils.parseDate("2018-03-22 16:36:50", "YYYY-MM-DD HH:mm:ss");
    Date date2 = DateUtils.parseDate("2018-03-22 12:36:50", "YYYY-MM-DD HH:mm:ss");
    boolean b = DateUtils.isSameDay(date1, date2);
    System.out.println(b);
  }

  private static void addDays() {
    Date a = new Date();
    Date b = new DateTime("2017-08-01").toDate();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(a);
    int c = calendar.get(Calendar.YEAR);
    //年部分的值:2017
    log.info("年部分的值:{}", c);

    Date date = DateTime.parse("2017-04-01").toDate();
    Date date1 = DateUtils.addDays(date, 7);
    // 结果是：2017-04-08 00:00:00
    log.info("结果是：{}", new DateTime(date1).toString("yyyy-MM-dd HH:mm:ss"));
  }
}
