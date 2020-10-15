package com.moqi.jvm.ch06;

/**
 * -XX:+TraceClassLoading
 *
 * 非主动使用类字段演示
 *
 * @author moqi On 10/15/20 15:22
 */

public class A09NotInitialization {

    public static void main(String[] args) {
        System.out.println(A08ConstClass.HELLO_WORLD);
    }

}
