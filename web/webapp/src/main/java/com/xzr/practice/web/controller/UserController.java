package com.xzr.practice.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = { "sign-up", "" }, method = RequestMethod.GET)
    public String signup(Model model) {
        log.info("进入注册页面");
        return "user/sign-up";
    }

}
