package com.xiangzhurui.core.java.time;

import java.util.Calendar;

/**
 * @author xiangzhurui
 * @version 2018/6/30 12:11
 */
public class CalendarExample {

  public static void main(String[] args) {
    calendarAdd();
  }

  private static void calendarAdd() {
    Calendar calendar = Calendar.getInstance();

    System.out.println(calendar.getTime());
    calendar.add(Calendar.MONTH, 1);
    calendar.add(Calendar.DAY_OF_MONTH, 1);
    System.out.println(calendar.getTime());
    calendar.add(Calendar.MONTH, -1);
    calendar.add(Calendar.MONTH, -1);
    System.out.println(calendar.getTime());
  }
}
