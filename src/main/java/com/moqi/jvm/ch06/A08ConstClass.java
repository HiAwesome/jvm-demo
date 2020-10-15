package com.moqi.jvm.ch06;

/**
 * 被动使用类字段演示三：
 * 常量在编译阶段会存入调用类的常量池中，本质上没有直接引用到定义常量的类，因此不会触发定义常量类的初始化
 *
 * @author moqi On 10/15/20 15:22
 */

public class A08ConstClass {

    static {
        System.out.println("A08ConstClass init!");
    }

    public static final String HELLO_WORLD = "Hello world";

}
