package com.xiangzhurui.core.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * 日期时间辅助工具<br/>
 * java.util.Date 与 java.time.* 互相转换
 *
 * @author xiangzhurui
 * @version 2019-05-08
 */
public final class DateHelper {
    private DateHelper() {
    }

    private final static ZoneId defaultZoneId = ZoneId.systemDefault();

    /**
     * @return 今日初始时间
     */
    public static Date startOfToday() {
        LocalDateTime localDateTime = LocalDate.now().atStartOfDay();
        return fromLocalDateTime(localDateTime);
    }

    /**
     * @return 今日最末时间
     */
    public static Date endOfToday() {
        LocalDateTime localDateTime = LocalDate.now().atTime(LocalTime.MAX);
        return fromLocalDateTime(localDateTime);
    }

    /**
     * 获取一天的开始时间
     *
     * @param input
     * @return
     */
    public static Date startOfDay(Date input) {
        LocalDateTime localDateTime = toLocalDate(input).atStartOfDay();
        return fromLocalDateTime(localDateTime);
    }

    /**
     * 获取一天的结束时间
     *
     * @param input
     * @return
     */
    public static Date endOfDay(Date input) {
        LocalDateTime localDateTime = toLocalDate(input).atTime(LocalTime.MAX);
        return fromLocalDateTime(localDateTime);
    }

    /**
     * java.util.Date 转为 java.time.LocalDate
     *
     * @param input
     * @return
     */
    public static LocalDate toLocalDate(Date input) {
        ZonedDateTime zonedDateTime = getZonedDateTime(input);
        return zonedDateTime.toLocalDate();
    }

    /**
     * java.util.Date 转为 java.time.LocalDateTime
     *
     * @param input
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date input) {
        ZonedDateTime zonedDateTime = getZonedDateTime(input);
        return zonedDateTime.toLocalDateTime();
    }

    /**
     * java.time.LocalDate 转为 java.util.Date
     *
     * @param localDate
     * @return
     */
    public static Date fromLocalDate(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay(defaultZoneId).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     * java.time.LocalDateTime 转为 java.util.Date
     *
     * @param localDateTime
     * @return
     */
    public static Date fromLocalDateTime(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(defaultZoneId).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    private static ZonedDateTime getZonedDateTime(Date input) {
        if (Objects.isNull(input)) {
            new IllegalArgumentException("input can note be null!");
        }
        return input.toInstant().atZone(defaultZoneId);
    }
}
