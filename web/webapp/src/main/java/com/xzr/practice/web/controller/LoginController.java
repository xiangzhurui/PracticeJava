package com.xzr.practice.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xzr.practice.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller(value = "/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = { "/", "" }, method = RequestMethod.POST)
    public String login(@RequestParam String userName, @RequestParam String password, Model model) {
        log.debug("userName=[{}],password=[{}]", userName, password);
        if (loginService.login(userName, password)) {

            return "manager/index";
        } else {
            model.addAttribute("msg", "用户名或密码错误");
            return "index";
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String login() {
        log.debug("登录页面");
        return "index";
    }

}
