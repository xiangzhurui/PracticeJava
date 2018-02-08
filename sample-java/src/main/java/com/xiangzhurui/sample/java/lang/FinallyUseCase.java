package com.xiangzhurui.sample.java.lang;

public class FinallyUseCase {
    public static int testFinally() {
        int a = 1;
        try {
            if (a == 1) {
                throw new RuntimeException("REA");
            }
        } finally {
            if (a == 1) {
                throw new RuntimeException("REB");
            }
            a = 2;
        }
        return a;
    }

    public static void main(String args[]) {
        System.out.println(testFinally());
    }
}
