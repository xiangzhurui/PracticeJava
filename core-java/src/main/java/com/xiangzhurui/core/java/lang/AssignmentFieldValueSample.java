package com.xiangzhurui.core.java.lang;

/**
 * 各种赋值方式
 * @author xiangzhurui
 */
public class AssignmentFieldValueSample {
    //实例field，在申明时赋值了
    private final String str = "Java";

    //实例field，在申明时未赋值
    private final String str2;

    //实例field，在申明时未赋值
    private final int i;

    //类field，在申明时赋值了
    private static final int i2 = 2;

    private static final int i3;

    {
        //在普通初始化块中给实例field赋值
        str2 = "normalFinalField";
    }

    static {
        //在静态初始化块中给类field指定初始值
        i3 = 3;
    }

    public AssignmentFieldValueSample() {
        //在构造器中给实例field赋值
        i = 1;

        //在构造器中给已经在初始化中赋了值的str2重新赋值，将会报编译错。
        //str2 = "re";
    }
}
