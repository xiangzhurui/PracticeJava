package com.xiangzhurui.drools.controller;

import com.xiangzhurui.drools.engine.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author xiangzhurui
 * @version 2017/10/9
 */
@Controller
public class EngineManageController {

    @Autowired
    private EngineService engineService;

    public void reload(){
        engineService.reload();
    }
}
