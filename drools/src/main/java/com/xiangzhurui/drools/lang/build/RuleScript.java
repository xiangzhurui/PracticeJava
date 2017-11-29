package com.xiangzhurui.drools.lang.build;

import com.xiangzhurui.drools.lang.build.structure.ScriptDeclareType;
import com.xiangzhurui.drools.lang.build.structure.ScriptFunction;
import com.xiangzhurui.drools.lang.build.structure.ScriptGlobal;
import com.xiangzhurui.drools.lang.build.structure.ScriptImport;
import com.xiangzhurui.drools.lang.build.structure.ScriptPackage;
import com.xiangzhurui.drools.lang.build.structure.ScriptRule;
import lombok.Builder;
import lombok.NonNull;

import java.util.Collection;
import java.util.Collections;

/**
 * 构建完整的规则脚本
 *
 * @author xiangzhurui
 * @version 2017/11/29
 */
@Builder
public class RuleScript {
    @NonNull
    private ScriptPackage pkg;

    private Collection<ScriptImport> scriptImports = Collections.EMPTY_LIST;
    private Collection<ScriptFunction> scriptFunctions = Collections.EMPTY_SET;
    private Collection<ScriptGlobal> scriptGlobals = Collections.EMPTY_SET;
    private Collection<ScriptDeclareType> scriptDeclareTypes = Collections.EMPTY_SET;

    @NonNull
    private Collection<ScriptRule> scriptRules = Collections.EMPTY_SET;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(pkg.toString());
        builder.append("\n");
        if (scriptImports != null && !scriptImports.isEmpty()) {
            for (ScriptImport anScriptImport : scriptImports) {
                builder.append(anScriptImport.toString());
            }
            builder.append("\n");
        }
        if (scriptGlobals != null && !scriptGlobals.isEmpty()) {
            for (ScriptGlobal riskScriptGlobal : scriptGlobals) {
                builder.append(riskScriptGlobal.toString());
            }
            builder.append("\n");
        }
        if (scriptDeclareTypes != null && !scriptDeclareTypes.isEmpty()) {
            for (ScriptDeclareType scriptDeclareType : scriptDeclareTypes) {
                builder.append(scriptDeclareType);
            }
            builder.append("\n");
        }
        if (scriptFunctions != null && !scriptFunctions.isEmpty()) {
            for (ScriptFunction scriptFunction : scriptFunctions) {
                builder.append(scriptFunction.toString());
            }
            builder.append("\n");
        }

        if (!scriptRules.isEmpty()) {
            for (ScriptRule scriptRule : scriptRules) {
                builder.append(scriptRule.toString());
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}
