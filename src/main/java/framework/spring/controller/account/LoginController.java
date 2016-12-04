package framework.spring.controller.account;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import framework.mybatis.entity.Account;
import framework.spring.service.LoginService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = { "", "/" },method=RequestMethod.POST)
    public String login(@Param(value = "username") String username, @Param(value = "password") String password) {
        Account account = loginService.getAccountById(1);
        log.info("{}",account!=null?account.getUsername():2424242);
        return "login";
    }
    @RequestMapping(value= {"/",""},method=RequestMethod.GET)
    public String login(){
        Account account = loginService.getAccountById(1);
        log.info("{}",account!=null?account.getUsername():333);
        return "login";
    }
}
