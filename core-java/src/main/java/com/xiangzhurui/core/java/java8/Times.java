package com.xiangzhurui.core.java.java8;

import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by xiangzhurui on 2017/5/17.
 */
public class Times {
    public static void main(String[] args){
        DateTimeFormatter dateTimeFormatter =DateTimeFormatter.ofPattern("yyyy-MM");
        YearMonth thisYearMonth = YearMonth.now();
        String s = thisYearMonth.format(dateTimeFormatter);

        System.out.println(s);
        System.out.println(thisYearMonth.minusMonths(1).format(dateTimeFormatter));
        YearMonth startYearMonth = YearMonth.now().minusMonths(1); //减去一个月
        LocalDate localDate = LocalDate.of(startYearMonth.getYear(),startYearMonth.getMonth(),1); //构造 LocalDate 对象

        //Instant 对象转换为 java.util.Date 对象
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        java.util.Date startTime = Date.from(instant);
        System.out.println(startTime);
        System.out.println(new Date(0));

    }
}
