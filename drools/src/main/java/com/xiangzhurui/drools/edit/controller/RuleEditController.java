package com.xiangzhurui.drools.edit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiangzhurui
 * @version 2018/3/20
 */
@Controller
@RequestMapping("edit/rule")
public class RuleEditController {

    @RequestMapping("view")
    public String viewRule(Long ruleId) {
        return null;
    }

    @RequestMapping("list")
    public String listRules() {
        return null;
    }
}
