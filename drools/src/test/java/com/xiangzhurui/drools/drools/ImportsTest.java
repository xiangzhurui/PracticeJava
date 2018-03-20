package com.xiangzhurui.drools.drools;

import lombok.extern.slf4j.Slf4j;
import org.drools.workbench.models.datamodel.rule.RuleAttribute;
import org.kie.soup.project.datamodel.imports.Import;
import org.kie.soup.project.datamodel.imports.Imports;

/**
 * @author xiangzhurui
 * @version 2018/3/16
 */
@Slf4j
public class ImportsTest {
    public static void main(String[] args) {
        Imports imports = new Imports();

        Import item = new Import(Long.class);

        log.info("item:[{}]", item.getType());

        imports.addImport(item);

        log.info("imports:[{}]", imports);


        RuleAttribute ruleAttribute = new RuleAttribute("no-loop","true");
        log.info(ruleAttribute.toString());

    }
}
