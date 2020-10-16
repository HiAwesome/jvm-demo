package com.moqi.jvm.ch08;

/**
 * 方法动态分派演示
 *
 * @author moqi On 10/16/20 15:32
 */

public class A08DynamicDispatch {

    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("Man say Hello");
        }
    }

    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("Woman say Hello");
        }
    }

    /**
     * Man say Hello
     * Woman say Hello
     * Woman say Hello
     */
    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();

        man.sayHello();
        woman.sayHello();

        man = new Woman();
        man.sayHello();
    }

}
