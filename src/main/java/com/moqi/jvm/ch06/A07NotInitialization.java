package com.moqi.jvm.ch06;

/**
 * -XX:+TraceClassLoading
 *
 * 被动使用类字段演示二：
 * 通过数组定义来引用类，不会触发此类的初始化
 *
 * @author moqi On 10/15/20 15:12
 */

public class A07NotInitialization {

    /**
     */
    public static void main(String[] args) {
        A04SuperClass[] sc = new A04SuperClass[10];
    }

}
