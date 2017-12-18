package com.xiangzhurui.drools.engine;

import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.scheduling.annotation.Async;

import java.util.Map;

/**
 * 规则引擎KieContainer 缓存服务
 */
public interface EngineService {

    /**
     * 重新构建并加载规则
     */
    void refreshRules(ReleaseId releaseId) throws Exception;

    @Async
    void asyncRefreshRules(ReleaseId releaseId);

    StatelessKieSession getStatelessKieSession(ReleaseId releaseId);

    KieSession getKieSession(ReleaseId releaseId);

}
