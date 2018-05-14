package com.xiangzhurui.drools.engine.facts;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class AuditFact implements Serializable {

    private String sequenceId;
    private Date executeDate;
}
