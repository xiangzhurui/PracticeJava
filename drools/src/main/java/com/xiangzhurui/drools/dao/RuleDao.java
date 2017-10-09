package com.xiangzhurui.drools.dao;

import java.util.List;

public interface RuleDao {

    String getRuleScript(Integer id);

    List<String> getAll(String strategyId);
}
