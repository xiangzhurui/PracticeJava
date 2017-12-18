package com.xiangzhurui.drools.controller;

import com.xiangzhurui.drools.engine.EngineService;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
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

    public void reload(String key) {
        ReleaseId releaseId = KieServices.Factory.get().newReleaseId("com.xiangzhurui", "drools-demo", key);
        try {
            engineService.refreshRules(releaseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
