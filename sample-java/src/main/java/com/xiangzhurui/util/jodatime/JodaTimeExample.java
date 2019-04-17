package com.xiangzhurui.util.jodatime;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

@Slf4j
public class JodaTimeExample {

    public static void main(String[] args) {
        useCase();
    }

    private static void useCase() {
        System.out.println(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        DateTime dateTime = DateTime.now();
        System.out.println(dateTime);
        log.info("{}", new DateTime(dateTime.getYear(), dateTime.getMonthOfYear(), dateTime.getDayOfMonth(), 0, 0, 0, 0).toDate());
        log.info("{}", new DateTime(dateTime.getYear(), dateTime.getMonthOfYear(), dateTime.getDayOfMonth(), 23, 59, 59, 999).toDate());

    }

}
