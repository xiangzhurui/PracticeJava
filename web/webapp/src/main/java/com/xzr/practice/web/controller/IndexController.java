package com.xzr.practice.web.controller;

import com.xzr.practice.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 首页,登录页
 *
 * @author XiangZhuRui
 */
@Slf4j
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = {"login", "sign-in", ""}, method = RequestMethod.POST)
    public String login(@RequestParam(required = false) String username, @RequestParam(required = false) String password, Model model) {
        log.info("username=[{}],password=[{}]", username, password);
        if (loginService.login(username, password)) {

            return "manager/index";
        } else {
            model.addAttribute("msg", "用户名或密码错误");
            return "index";
        }
    }

    @RequestMapping(value = {"login", "", "sign-in"}, method = RequestMethod.GET)
    public String login() {
        log.info("登录页面");
        return "index";
    }
}
