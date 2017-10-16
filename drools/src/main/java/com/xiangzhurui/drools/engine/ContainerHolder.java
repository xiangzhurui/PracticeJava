package com.xiangzhurui.drools.engine;

import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xiangzhurui
 * @version 2017/10/13
 */
@Component
public class ContainerHolder implements InitializingBean {
    @Autowired
    private KieContainer container;
    private Map<ReleaseId, KieContainer> containerMap;

    public Map<ReleaseId, KieContainer> getContainerMap() {
        return containerMap;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        //TODO 初始化加载构建规则
    }

    /**
     * 更新container
     *
     * @param releaseId
     */
    public void updateContainerToVersion(ReleaseId releaseId) {
        container.updateToVersion(releaseId);
    }

    public void build() {
        //TODO 构建加载到 KieFileSystem
    }
}
