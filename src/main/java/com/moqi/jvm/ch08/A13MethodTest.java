package com.moqi.jvm.ch08;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * 掌控方法分派规则
 *
 * @author moqi On 10/16/20 17:22
 */

public class A13MethodTest {

    class GrandFather {
        void thinking() {
            System.out.println("I am grandfather");
        }
    }

    class Father extends GrandFather {
        void thinking() {
            System.out.println("I am father");
        }
    }

    class Son extends Father {
        void thinking() {
            try {
                MethodType mt = MethodType.methodType(void.class);
                MethodHandle mh = MethodHandles.lookup().findSpecial(GrandFather.class, "thinking", mt, getClass());
                mh.invoke(this);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // oracle 8u261 I am father
        new A13MethodTest().new Son().thinking();
    }

}
