package com.xiangzhurui.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.builder.model.KieSessionModel;
import org.kie.api.io.KieResources;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

/**
 * 简单示例
 *
 * @author xiangzhurui
 * @version 2017/9/29
 */
@Slf4j
public class DroolsTest {

    @Test
    public void testKieModuleModel() {
        KieServices kieServices = KieServices.Factory.get();
        ReleaseId releaseId = kieServices.newReleaseId("com.xiangzhurui", "example-drools", "1.0-SNAPSHOT");

        KieModuleModel moduleModel = kieServices.newKieModuleModel()
                .setConfigurationProperty("name", "hahah");
        String baseModelName = releaseId.getGroupId() + "_" + releaseId.getArtifactId() + "_" + releaseId.getVersion();
        KieBaseModel baseModel = moduleModel.newKieBaseModel(baseModelName)
                .addPackage("rules" + "/" + releaseId.getGroupId() + "/" + releaseId.getArtifactId());

        baseModel.newKieSessionModel("session")
                .setDefault(true)
                .setType(KieSessionModel.KieSessionType.STATELESS);

        moduleModel.newKieBaseModel("kbase1")
                .addPackage(releaseId.getGroupId())
        ;

        String kmoduleXML = moduleModel.toXML();
        log.info(releaseId.toString());
        log.info("xml:\n{}", kmoduleXML);
        KieResources kieResources = kieServices.getResources();
        Resource resources = kieResources.newClassPathResource("rules/test.drl"); //实际上为虚拟路径
//        Resource resources = kieResources.newInputStreamResource(getClass().getResourceAsStream("/rules/test.drl"));
        KieFileSystem fileSystem = kieServices.newKieFileSystem();
        fileSystem.write(resources);
        fileSystem.writeKModuleXML(kmoduleXML);
        fileSystem.generateAndWritePomXML(releaseId);
        final KieRepository repository = kieServices.getRepository();
        repository.addKieModule(() -> releaseId);
        // 7. 最后通过 KieBuilder 进行构建就将该 kmodule 加入到 KieRepository 中,
        // 这样就将自定义的kmodule加入到引擎中了
        KieBuilder kieBuilder = kieServices.newKieBuilder(fileSystem)
                .buildAll(); // 编译
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            log.error("{}", results.getMessages());
        }
        log.info("默认值：\n{}", kieServices.getRepository().getDefaultReleaseId());

//        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        KieContainer kieContainer = kieServices.newKieContainer(releaseId);

        KieBase base = kieContainer.getKieBase(baseModelName);
        StatelessKieSession session = base.newStatelessKieSession();
        log.info("ss");
    }

    @Ignore
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

    @Ignore
    @Test
    public void sample() {
        // 获取 drools 实现的 KieServices 实例
        KieServices kieServices = KieServices.Factory.get();
        // 创建一个 KieFileSystem
        KieFileSystem fileSystem = kieServices.newKieFileSystem();
        // 创建一个 KieResources 对象
        KieResources kieResources = kieServices.getResources();

        // 1. 先创建 KieModuleModel, 类似于xml中的 kmodule 节点
        KieModuleModel kieModuleModel = kieServices.newKieModuleModel();
        // 2. 再创建 KieBaseModel, 类似于xml中的 kbase节点, name=kbase-rules, package=rules
        KieBaseModel baseModel = kieModuleModel.newKieBaseModel("kbase-rules").addPackage("rules");
        // 3. 再创建 KieSessionModel, 类似于xml中的 ksession 节点, name=ksession-rules
        baseModel.newKieSessionModel("ksession-rules");
        // 4. 生产一个xml文件，就是kmodule.xml文件
        String xml = kieModuleModel.toXML();
        System.out.println(xml); // 打印出来看看内容
        // 5. 将这个xml文件写入到KieFileSystem中
        fileSystem.writeKModuleXML(xml);
        // 6. 然后将规则文件等写入到 KieFileSystem 中
        // fileSystem.write("src/main/resources/rules/test.drl",
        // kieResources.newClassPathResource("rules/test.drl"));
        Resource resources = kieResources.newClassPathResource("rules/test.drl"); //实际上为虚拟路径
        fileSystem.write(resources);
        // 7. 最后通过 KieBuilder 进行构建就将该 kmodule 加入到 KieRepository 中,
        // 这样就将自定义的kmodule加入到引擎中了
        KieBuilder kieBuilder = kieServices.newKieBuilder(fileSystem);
        kieBuilder.buildAll(); // 编译
        // 下面就可以向原来一样使用了
        // 得到 KieContainer
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        // 通过 kContainer 获取 kmodule.xml 中定义的 ksession
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");
        // 向 workingMemory 中加入一个对象
        kieSession.insert("Tom");
        // 通知规则引擎执行规则
        kieSession.fireAllRules();
    }
}
