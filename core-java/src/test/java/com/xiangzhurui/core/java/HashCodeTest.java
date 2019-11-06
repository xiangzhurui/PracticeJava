package com.xiangzhurui.core.java;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author xiangzhurui
 * @version 2018/8/28 19:46
 */
@Slf4j
public class HashCodeTest {

    @Test
    public void test(){
        String  sss = "1231";
        String  abc = "abc";


        int x = sss.hashCode();
        System.out.println(x);
        int x1 = abc.hashCode();
        System.out.println(x1);

        System.out.println(x%100);
        System.out.println(-11509439 % 100);

        System.out.println(x1%100);
        System.out.println(-96354 % 100);


    }
}
