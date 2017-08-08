package com.xzr.practice.web.controller;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 应用首页
 * 
 * @author XiangZhuRui
 * 
 */
@Controller
@RequestMapping("/")
public class IndexController {
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String showIndexPage(Model model) {
		model.addAttribute("msg", "Hello 首页");
		String timeNow = new DateTime().toString();
		log.info("IndexController里的时间是{}",timeNow);
		model.addAttribute("timeNow", timeNow);
		return "index";
	}
	@RequestMapping("login")
	public String login(){
		return "index";
	}
}
