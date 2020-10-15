package com.moqi.jvm.ch06;

/**
 * -XX:+TraceClassLoading
 *
 * 非主动使用类字段演示
 *
 * @author moqi On 10/15/20 15:12
 */

public class A06NotInitialization {

    /**
     * SuperClass init!
     * A05SubClass.value = 123
     */
    public static void main(String[] args) {
        System.out.println("A05SubClass.value = " + A05SubClass.value);
    }

}
