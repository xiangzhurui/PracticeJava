package framework.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/hello")
public class HelloController {
	@RequestMapping(value= {"/",""},method=RequestMethod.GET)
	public String getHello() {
		return "hello/hello";
	}
	
	@RequestMapping("login")
	public String login(){
	    return "index";
	}
}
