package com.xiangzhurui.core.java.lang;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiangzhurui
 * @version 2019-01-12 17:34
 */
@Slf4j
public class MyClassFileTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        log.info("className={}", className);
        return new byte[0];
    }

    public static void main(String[] args) {
        log.info("MyClassFileTransformer==={}",MyClassFileTransformer.class.getCanonicalName());
    }
}
