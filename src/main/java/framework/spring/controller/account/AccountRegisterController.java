package framework.spring.controller.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class AccountRegisterController {
    final static Logger log = LoggerFactory.getLogger(AccountRegisterController.class);

    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
    public String showRegistForm() {
        return "account/register";
    }

    @RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
    public String processRegistration(@RequestParam("fullName") String fullName, @RequestParam("email") String email,
            @RequestParam("password") String password) {
        log.info("全名:{},电子邮件:{},密码:{}",fullName,email,password);
        return "redirect:/login";
    }
}
