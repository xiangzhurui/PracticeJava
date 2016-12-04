package framework.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value="WebcomeIndexController")
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping(value = { "index", "" })
    public String getIndex() {
        return "index";
    }

    @RequestMapping("logout")
    public String toLogin() {
        return "login";
    }

}
