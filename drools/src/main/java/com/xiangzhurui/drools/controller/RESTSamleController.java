package com.xiangzhurui.drools.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiangzhurui
 * @version 2018/4/8
 */
@RestController
@RequestMapping("rest/swagger2/test")
public class RESTSamleController {
    @RequestMapping("hello")
    public String helloworld(String userId) {
        return "hello " + userId;
    }
}
