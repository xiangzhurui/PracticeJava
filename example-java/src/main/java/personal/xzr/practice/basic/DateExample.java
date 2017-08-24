package personal.xzr.practice.basic;

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Date;

/**
 * @author linquan
 * @version 2017/8/23
 */
public class DateExample {
    public static void main(String[] args) {
        Date a = new Date();
        Date b = new DateTime("2017-08-01").toDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(a);
        int c = calendar.get(Calendar.YEAR)-calendar.get(Calendar.YEAR);
        System.out.println(c);
    }
}
