package com.xiangzhurui.drools.engine;

import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

import java.util.Map;

/**
 * 规则引擎服务
 */
public interface EngineService {

    /**
     * 重新构建并加载规则
     */
    void reload();

    void test();

    StatelessKieSession getStatelessKieSession(ReleaseId releaseId);

    KieSession getKieSession(ReleaseId releaseId);

}
