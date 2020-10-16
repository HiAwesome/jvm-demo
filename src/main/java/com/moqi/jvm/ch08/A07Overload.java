package com.moqi.jvm.ch08;

import java.io.Serializable;

/**
 * 重载方法匹配优先级
 *
 * 重载顺序： char>int>long>float>double
 *
 * 重载 -> 自动装箱 -> 自动转型 -> 变长参数列表
 *
 * @author moqi On 10/16/20 15:24
 */

public class A07Overload {

    public static void sayHello(Object arg) {
        System.out.println("hello Object");
    }

    public static void sayHello(int arg) {
        System.out.println("hello int");
    }

    public static void sayHello(long arg) {
        System.out.println("hello long");
    }

    public static void sayHello(Character arg) {
        System.out.println("hello Character");
    }

    public static void sayHello(char arg) {
        System.out.println("hello char");
    }

    public static void sayHello(char... arg) {
        System.out.println("hello char ...");
    }

    public static void sayHello(Serializable arg) {
        System.out.println("hello Serializable");
    }

    /**
     * hello char
     */
    public static void main(String[] args) {
        sayHello('a');
    }

}
