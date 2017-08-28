package personal.xzr.practice.basic;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author xiangzhurui
 * @version 2017/8/28
 */
@Slf4j
public class BigDecimalExample {
    public static void main(String[] args){
        BigDecimal bigDecimal = new BigDecimal(0.8);
        BigDecimal bigDecimal1 = BigDecimal.valueOf(0.8);
        BigDecimal bigDecimal2 = new BigDecimal("0.8");
        log.info(bigDecimal.toString());
        log.info(bigDecimal1.toString());
        log.info(bigDecimal2.toString());
        log.info("bigDecimal与bigDecimal是否相等：{}",bigDecimal.equals(bigDecimal1)?"相等":"不相等");
    }
}
