package com.moqi.jvm.ch06;

/**
 * 被动引用类字段演示一：
 * 通过子类引用父类的静态字段，不会导致子类初始化
 *
 * 子类
 *
 * @author moqi On 10/15/20 15:11
 */

public class A05SubClass extends A04SuperClass {

    static {
        System.out.println("SubClass init!");
    }

}
