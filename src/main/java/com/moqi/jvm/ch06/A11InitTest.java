package com.moqi.jvm.ch06;

/**
 * 非法前向引用变量
 *
 * @author moqi On 10/15/20 17:10
 */

public class A11InitTest {

    static {
        // 给变量赋值可以正常编译通过
        i = 0;
        // 打印变量的值编译器会提示: 非法前向引用, Illegal forward reference
        // System.out.println("i = " + i);
    }

    static int i = 1;

}
