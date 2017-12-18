package com.xiangzhurui.drools.service;

import com.xiangzhurui.drools.lang.build.RuleScript;
import org.kie.api.builder.ReleaseId;

public interface RuleScriptService {
    /**
     * 加载规则数据
     *
     * @param releaseId
     *
     * @return
     */
    RuleScript loadRuleScript(ReleaseId releaseId);
}
