package com.xiangzhurui.drools.service;

import com.xiangzhurui.drools.engine.EngineService;
import org.kie.api.builder.ReleaseId;
import org.kie.api.command.Command;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiangzhurui
 * @version 2017/10/9
 */
@Service
public class FireAllExampleService {
    @Autowired
    private EngineService engineService;

    public void fire(Object obj, ReleaseId releaseId) {
        StatelessKieSession session = engineService.getStatelessKieSession(releaseId);
        session.setGlobal("", new Object());

        List<? extends Command> list = new ArrayList<>();
        list.add(() -> {
            new Object();
        });
        session.execute();
    }
}
