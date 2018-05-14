package com.xiangzhurui.drools;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

public class ParamMapTest {

    public void testSet(){
        Map<String,Object> testParam = new HashMap(){{
            put("b",Boolean.FALSE);
            put("i",123);
            put("s","StringTest");
        }};

        Obj obj = new Obj();
        obj.setB((Boolean) testParam.get("b"));

    }

    @Data
    @NoArgsConstructor
    public static class Obj{
        private Boolean b;
        private Integer i;
        private String s;
    }
}
