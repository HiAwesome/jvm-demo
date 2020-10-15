package com.moqi.jvm.ch06;

/**
 * 测试异常表
 *
 * @author moqi On 10/15/20 10:04
 */

public class A02TestException {

    private int m;

    public int inc() {
        int x;

        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }

    }

}
