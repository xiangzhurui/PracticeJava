package io.prictice;

import java.io.Console;
import java.util.Arrays;

public class ConsoleTest {
    public static void main(String[] args) {
        Console c = System.console();
        if (c == null) {
            System.err.println("No Console.");
            System.exit(1);
        }
        String login = c.readLine("输入密码：");
        char[] password = c.readPassword("password:");
        System.out.println("login=" + login);
        System.out.println("password=" + Arrays.toString(password));
    }
}
