package com.moqi.jvm.ch08;

/**
 * 方法静态解析展示
 *
 * @author moqi On 10/16/20 10:17
 */

public class A05StaticResolution {

    public static void sayHello() {
        System.out.println("Hello World");
    }

    public static void main(String[] args) {
        A05StaticResolution.sayHello();
    }

}
