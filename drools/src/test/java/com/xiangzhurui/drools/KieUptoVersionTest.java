package com.xiangzhurui.drools;

import org.kie.api.KieServices;
import org.kie.api.builder.KieRepository;

/**
 * @author xiangzhurui
 * @version 2018/6/8 14:33
 */
public class KieUptoVersionTest {
    public static final KieServices KIE_SERVICES = KieServices.get();

    public static final KieRepository REPOSITORY = KIE_SERVICES.getRepository();

}
