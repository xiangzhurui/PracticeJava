package framework.spring.web.controller.account;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import framework.mybatis.entity.Account;
import framework.spring.service.LoginService;
import framework.spring.service.account.AccountService;

@Controller
@RequestMapping("/account/register")
public class AccountController {
	final static Logger		log	= LoggerFactory.getLogger(AccountController.class);
	@Autowired
	private AccountService	accountservice;

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String showPage() {
		return "account/register";
	}

	@RequestMapping(value = { "/add", "/add/" }, method = RequestMethod.POST)
	public String addAccount(@RequestParam("fullName") String fullName, @RequestParam("email") String email,
	        @RequestParam("password") String password, Model mode) {
		log.info("全名:{},电子邮件:{},密码:{}", fullName, email, password);
		Account			account=new Account(email, password);
		accountservice.add(account);
		mode.addAttribute("message", "注册成功");
		return "redirect:/account/login";
	}

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = { "/login", "/login/" }, method = RequestMethod.POST)
	public String login(@Param(value = "username") String username, @Param(value = "password") String password) {
		Account account = loginService.getAccountById(1);
		log.info("{}", account != null ? account.getUsername() : 2424242);
		return "account/login";
	}

	@RequestMapping(value = { "/login", "/login/" }, method = RequestMethod.GET)
	public String login() {
		Account account = loginService.getAccountById(1);
		log.info("{}", account != null ? account.getUsername() : 333);
		return "account/login";
	}
}
