package com.xiangzhurui.core.ext.jodatime;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

@Slf4j
public class Test {

    public static void main(String[] args) throws InterruptedException {
        log.info("{}", new Object[]{args});
//        if (args == null || args.length == 0) throw new AssertionError();
        log.info("{}", new Object[]{args});
        DateTime dateTime =DateTime.now();
        DateTime createTimeFrom = new DateTime(dateTime.getYear(), dateTime.getMonthOfYear(), dateTime.getDayOfMonth(), 0, 0, 0, 0).minusDays(7);
        log.info("createTimeFrom:[{}]", createTimeFrom);
    }

}
