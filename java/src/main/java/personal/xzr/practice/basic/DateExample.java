package personal.xzr.practice.basic;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Date;

/**
 * @author xiangzhurui
 * @version 2017/8/25
 */
@Slf4j
public class DateExample {
    public static void main(String[] args) {
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
