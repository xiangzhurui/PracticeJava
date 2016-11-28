package framework.spring.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import framework.mybatis.entity.Account;
import framework.spring.service.LoginService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = { "", "/" })
    public String login(@Param(value = "username") String username, @Param(value = "password") String password) {
        Account account = loginService.getAccountById(1);
        System.out.println(account.getUsername());
        return "login";
    }
    @RequestMapping(value= {"/",""},method=RequestMethod.GET)
    public String login(){
        Account account = loginService.getAccountById(1);
        System.out.println(account.getUsername());
        return "login";
    }
}
