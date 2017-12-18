package com.xiangzhurui.drools.engine.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.xiangzhurui.drools.engine.EngineService;
import com.xiangzhurui.drools.lang.build.RuleScript;
import com.xiangzhurui.drools.service.RuleScriptService;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.builder.model.KieSessionModel;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

/**
 * KieContainer 缓存服务
 *
 * @author xiangzhurui
 * @version 2017/9/29
 */
@Slf4j
@Service
public class EngineServiceImpl implements EngineService {


    private LoadingCache<ReleaseId, KieContainer> loadingCache = CacheBuilder.newBuilder()
            .maximumSize(1024)
            .build(new CacheLoader<ReleaseId, KieContainer>() {
                @Override
                public KieContainer load(ReleaseId key) throws Exception {
                    return rebuild(key);
                }
            });

    @Autowired
    private RuleScriptService ruleScriptService;

    @Override
    public void refreshRules(ReleaseId releaseId) throws Exception {
        KieContainer kieContainer = rebuild(releaseId);
        loadingCache.put(releaseId, kieContainer);
    }

    @Override
    public void asyncRefreshRules(ReleaseId releaseId) {
        loadingCache.refresh(releaseId);
    }

    @Override
    public StatelessKieSession getStatelessKieSession(ReleaseId releaseId) {
        try {
            return loadingCache.get(releaseId).newStatelessKieSession();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public KieSession getKieSession(ReleaseId releaseId) {

        try {
            return loadingCache.get(releaseId).newKieSession();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }


    private KieContainer rebuild(ReleaseId releaseId) throws Exception {
        RuleScript ruleScript = ruleScriptService.loadRuleScript(releaseId);
        return build(releaseId, ruleScript);
    }

    private static KieContainer build(ReleaseId releaseId, RuleScript ruleScript) {
        KieServices kieServices = KieServices.Factory.get();

        KieModuleModel moduleModel = kieServices.newKieModuleModel();

        KieBaseModel baseModel = moduleModel.newKieBaseModel(releaseId.getGroupId() + "-" + releaseId.getArtifactId() + "-" + releaseId.getVersion())
                .addPackage("rules" + "/" + releaseId.getGroupId() + "/" + releaseId.getArtifactId());

        KieSessionModel kieSessionModel = baseModel.newKieSessionModel("session")
                .setDefault(true)
                .setType(KieSessionModel.KieSessionType.STATELESS);

        String kModuleXML = moduleModel.toXML();

        if (log.isDebugEnabled()) {
            log.debug("kmodule.xml 内容：\n", kModuleXML);
        }

        KieFileSystem fileSystem = kieServices.newKieFileSystem();

        fileSystem.writeKModuleXML(kModuleXML);
        String ruleStr = ruleScript.toString();
        String kieFilePath = "rules" + "/" + releaseId.getGroupId() + "/" + releaseId.getArtifactId() + releaseId.getVersion() + ".drl";
        fileSystem.write("src/main/resources/" + kieFilePath, ruleStr);

        KieBuilder kieBuilder = kieServices.newKieBuilder(fileSystem);
        kieBuilder.buildAll(); // kieModule is automatically deployed to KieRepository if successfully built.

        if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
            log.error("错误信息：{}", kieBuilder.getResults());
            throw new RuntimeException("构建错误：\n" + kieBuilder.getResults().toString());
        }

        KieContainer kContainer = kieServices.newKieContainer(releaseId);
        return kContainer;
    }
}
