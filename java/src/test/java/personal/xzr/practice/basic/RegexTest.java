package personal.xzr.practice.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiangzhurui
 * @version 2017/10/24
 */
@Slf4j
public class RegexTest {
    public static void main(String[] args) throws Exception {

        String regEx = "count(\\d+)(df)";
        String s = "count000dfdfsdffaaaa1";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(s);
        if (mat.find()) {
            System.out.println(mat.group(1));
        }
    }

    @Test
    public void test() {

    }
}
