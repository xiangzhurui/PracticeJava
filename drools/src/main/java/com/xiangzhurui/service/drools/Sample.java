package com.xiangzhurui.service.drools;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.cdi.KContainer;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

/**
 * @author xiangzhurui
 * @version 2017/9/29
 */
@Component
public class Sample {
    @KContainer
    private KieContainer kieContainer;

    @Autowired
    private KieServices kieServices;

    public void sample() {

        Date interestRate = new Date();
        System.out.println("printing session object before inserting" + interestRate.toString());

        KieSession kieSession = kieContainer.newKieSession("ksession-rules");
        kieSession.insert(interestRate);

        kieSession.fireAllRules();

        System.out.println();
    }

    public void build() throws FileNotFoundException {
        KieFileSystem kfs = kieServices.newKieFileSystem();
        FileInputStream fis = new FileInputStream("simple/simple.drl");
        kfs.write("src/main/resources/simple.drl", kieServices.getResources().newInputStreamResource(fis));
        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        // check there have been no errors for rule setup
        Results results = kieBuilder.getResults();

        if (results.hasMessages(Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new IllegalStateException("### errors ###");
        }

        KieContainer kieContainer = kieServices.newKieContainer(kieBuilder.getKieModule().getReleaseId());
        KieSession kieSession = kieContainer.newKieSession();
    }
}
