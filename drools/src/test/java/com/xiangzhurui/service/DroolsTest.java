package com.xiangzhurui.service;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * 简单示例
 *
 * @author xiangzhurui
 * @version 2017/9/29
 */
public class DroolsTest {

    @Test
    public void defualtRule() {
        // 获取 drools 实现的 KieServices 实例
        KieServices kieServices = KieServices.Factory.get();
        // kieServices 默认加载 classpath:META-INF/kmodule.xml 得到 KieContainer
        KieContainer kContainer = kieServices.getKieClasspathContainer();
        // 通过 kContainer 获取 kmodule.xml 中定义的 ksession
        KieSession kieSession = kContainer.newKieSession("ksession-rules");
        // 向 workingMemory 中加入一个对象
        kieSession.insert("Tom");
        // 通知规则引擎执行规则
        int i = kieSession.fireAllRules();
    }
}
