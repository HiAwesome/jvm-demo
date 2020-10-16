package com.moqi.jvm.ch08;

/**
 * 方法静态分派演示
 *
 * @author moqi On 10/16/20 10:24
 */

public class A06StaticDispatch {

    static abstract class Human {

    }

    static class Man extends Human {

    }

    static class Woman extends Human {

    }

    public void sayHello(Human guy) {
        System.out.println("Hello, guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("Hello, gentleman!");
    }

    public void sayHello(Woman guy) {
        System.out.println("Hello, lady!");
    }

    /**
     * Hello, guy!
     * Hello, guy!
     */
    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        A06StaticDispatch sd = new A06StaticDispatch();
        sd.sayHello(man);
        sd.sayHello(woman);
    }


}
