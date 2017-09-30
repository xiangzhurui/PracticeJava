package com.xiangzhurui.drools.engine;

/**
 * 规则生命周期
 */
public interface LifeCycle {
    void build();

    void deploy();

    /***
     * 初始化加载规则
     */
    void load();

    /**
     * 重新构建并加载规则
     */
    void reload();
}
