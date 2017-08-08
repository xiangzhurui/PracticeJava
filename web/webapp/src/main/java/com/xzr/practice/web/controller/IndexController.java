package com.xzr.practice.web.controller;

import com.xzr.practice.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = {"/", ""})
public class IndexController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = {"/login", ""}, method = RequestMethod.POST)
    public String login(@RequestParam String userName, @RequestParam String password, Model model) {
        log.info("userName=[{}],password=[{}]", userName, password);
        if (loginService.login(userName, password)) {

            return "manager/index";
        } else {
            model.addAttribute("msg", "用户名或密码错误");
            return "index";
        }
    }

    @RequestMapping(value = {"login", "","index"}, method = RequestMethod.GET)
    public String login() {
        log.info("登录页面");
        return "index";
    }
}
