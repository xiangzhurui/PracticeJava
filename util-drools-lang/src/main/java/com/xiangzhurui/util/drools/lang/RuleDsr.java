package com.xiangzhurui.util.drools.lang;

import java.util.List;

/**
 * @author xiangzhurui
 * @version 2018/8/13 10:03
 */
public class RuleDsr {

    private String name;

    private LeftHandSide lhs;


    public RuleDsr name(String name) {
        this.name = name;
        return this;
    }

    public LeftHandSide left() {
        if (lhs == null) {
            lhs = new LeftHandSide();
        }
        return this.lhs;
    }

    public class LeftHandSide {
        private List<And> andList;

        public And and() {
            return new And();
        }

        public class And {

            private String objName;

            private List<Or> orList;

            public And objName(String objName) {
                this.objName = objName;
                return this;
            }

            public Or or() {
                return new Or();
            }


            public class Or {
                private String lefe;
                private String oprator;
                private String right;

            }
        }
    }


    public void and() {

    }

    public class GreaterThan {

    }

    public class LessThan {

    }

    public class Equals {

    }

    public class Matchs {

        private String operator = "matches";

        public String leftStr;
        public String regexStr;

        public Matchs left(String leftStr) {
            this.leftStr = leftStr;
            return this;
        }

        public Matchs right(String regexStr) {
            this.regexStr = regexStr;
            return this;
        }


    }

}
