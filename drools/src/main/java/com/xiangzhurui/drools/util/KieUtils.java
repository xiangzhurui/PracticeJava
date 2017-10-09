package com.xiangzhurui.drools.util;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiangzhurui
 * @version 2017/9/30
 */
@Slf4j
public class KieUtils {
    private KieUtils() {
    }

    /**
     * 持有多个 Container
     */
    private static Map<ReleaseId, KieContainer> kieContainerMap = new ConcurrentHashMap<>();

    /**
     * 更新 kieContainerMap
     *
     * @param kieContainer
     */
    public static void putKieContainer(KieContainer kieContainer) {
        kieContainerMap.put(kieContainer.getReleaseId(), kieContainer);
    }

    /**
     * 获取 StatelessKieSession 实例
     *
     * @param releaseId
     *
     * @return
     */
    public static StatelessKieSession getStatelessKieSession(ReleaseId releaseId) {
        KieContainer container = kieContainerMap.get(releaseId);
        return container != null ? container.newStatelessKieSession() : null;
    }

    /**
     * 获取 KieSession 实例
     *
     * @param releaseId
     *
     * @return
     */
    public static KieSession getKieSession(ReleaseId releaseId) {
        KieContainer container = kieContainerMap.get(releaseId);
        return container != null ? container.newKieSession() : null;
    }

    /**
     * @param releaseId releaseId
     * @param ruleStr   规则脚本内容
     *
     * @return KieContainer 实例
     */
    public static KieContainer build(ReleaseId releaseId, String ruleStr) {
        String kieFilePath = releaseId.getGroupId() + File.separator + releaseId.getArtifactId() + File.separator + releaseId.getVersion() + ".drl";
        return build(releaseId, kieFilePath, ruleStr);
    }

    /**
     * 添加到 KieFileSystem，并编译构建
     *
     * @param releaseId   releaseId
     * @param kieFilePath 包路径（src/main/resources/ 的自路径）
     * @param ruleStr     规则脚本内容
     *
     * @return KieContainer 实例
     */
    public static KieContainer build(ReleaseId releaseId, String kieFilePath, String ruleStr) {
        KieServices kieServices = KieServices.Factory.get();

        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

        kieFileSystem.write("src/main/resources/" + kieFilePath, ruleStr);

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);

        kieBuilder.buildAll(); // kieModule is automatically deployed to KieRepository if successfully built.
        if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
            log.error("错误信息：{}", kieBuilder.getResults());
            throw new RuntimeException("构建错误：\n" + kieBuilder.getResults().toString());
        }

        KieContainer kContainer = kieServices.newKieContainer(releaseId);
        return kContainer;
    }
}
