package personal.xzr.practice.basic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Sample {

    public static void main(String[] args) {
        List list = new ArrayList();

        int size = list.size();
        int start = 0;
        int num = 500;
        //分段
        while (start < size) {
            int end = start + num < size ? start + num : size - 1;
            List sub = list.subList(start, end);
            //do ....
            if (end == size - 1) {
                break;
            } else {
                start = end;
            }
        }

    }

    public static void testInt() {
        long x = 1L;
        BigInteger bi = new BigInteger("1");
        System.out.println(bi.shiftLeft(99));
        int a = 4;
        System.out.println(one(a));
        System.out.println(two(a));
    }

    public static int one(int a) {
        return ++a + a;
    }

    public static int two(int a) {
        return a++ + a;
    }
}
