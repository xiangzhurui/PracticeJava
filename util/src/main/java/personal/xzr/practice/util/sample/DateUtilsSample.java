package personal.xzr.practice.util.sample;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * @author xiangzhurui
 * @version 2018/3/22
 */
public class DateUtilsSample {

    public static void main(String[] args) throws ParseException {
        Date date1 = DateUtils.parseDate("2018-03-22 16:36:50","YYYY-MM-DD HH:mm:ss");
        Date date2 = DateUtils.parseDate("2018-03-22 12:36:50","YYYY-MM-DD HH:mm:ss");
        boolean b = DateUtils.isSameDay(date1,date2);
        System.out.println(b);
    }
}
