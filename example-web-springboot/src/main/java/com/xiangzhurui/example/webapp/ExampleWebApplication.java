package com.xiangzhurui.example.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class ExampleWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleWebApplication.class, args);
    }
}
