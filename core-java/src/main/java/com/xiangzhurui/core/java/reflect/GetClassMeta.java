package com.xiangzhurui.core.java.reflect;

/**
 * @author xiangzhurui
 * @version 2018/6/6 15:17
 */
public class GetClassMeta {

    public static void main(String[] args) throws ClassNotFoundException {

        System.out.println(String.class.getSimpleName());
        System.out.println(Class.forName("java.math.BigDecimal").getSimpleName());
    }
}

