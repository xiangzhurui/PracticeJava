package com.xiangzhurui.drools.engine.impl;

import com.xiangzhurui.drools.dao.RuleDao;
import com.xiangzhurui.drools.dao.Strategy;
import com.xiangzhurui.drools.engine.EngineService;
import com.xiangzhurui.drools.util.KieUtils;
import com.xiangzhurui.drools.util.RuleUtils;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiangzhurui
 * @version 2017/9/29
 */
@Slf4j
@Component
public class EngineServiceImpl implements EngineService, InitializingBean {

    @Autowired
    private RuleDao ruleDao;
    @Autowired
    private Strategy strategy;

    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化，容器启动时加载
        this.load(this.getRuleMap());
    }

    public void test() {

    }

    @Override
    public StatelessKieSession getStatelessKieSession(ReleaseId releaseId) {
        return KieUtils.getStatelessKieSession(releaseId);
    }

    @Override
    public KieSession getKieSession(ReleaseId releaseId) {
        return KieUtils.getKieSession(releaseId);
    }

    @Override
    public void reload() {
        synchronized (this) { //不能同时更新构建规则
            Map ruleScriptMap = this.getRuleMap();
            load(ruleScriptMap);
        }
        log.info("规则重载成功");
    }

    private void load(Map<ReleaseId, String> ruleScriptMap) {
        for (Map.Entry<ReleaseId, String> entry : ruleScriptMap.entrySet()) {
            ReleaseId releaseId = entry.getKey();
            String ruleStr = entry.getValue();
            KieContainer kieContainer = KieUtils.build(releaseId, ruleStr);
            KieUtils.putKieContainer(kieContainer);
        }
        log.info("规则加载载成功");
    }

    private Map getRuleMap() {
        List<String> strategeys = strategy.getAll();
        Map<ReleaseId, String> map = new HashMap<>();
        for (String strategyId : strategeys) {
            List<String> ruleStrList = ruleDao.getAll(strategyId);

            KieServices kieServices = KieServices.Factory.get();
            ReleaseId releaseId = kieServices.newReleaseId("test.rule", strategyId, "defualtVersion");
            map.put(releaseId, RuleUtils.generateRuleScript(ruleStrList));
        }
        return map;
    }
}
