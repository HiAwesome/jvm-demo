package com.moqi.jvm.ch10;

/**
 * 不符合规范代码示例
 *
 * @author moqi On 10/19/20 19:24
 */

public class A05_BADLY_NAMED_CODE {

    enum colors {
        red, blue, green
    }

    static final int _FORTY_TWO = 42;

    public static int NOT_A_CONSTANT = _FORTY_TWO;

    protected void Test() {
        return;
    }

    public void NOTcamelCASEmethodNAME() {
        return;
    }

}
