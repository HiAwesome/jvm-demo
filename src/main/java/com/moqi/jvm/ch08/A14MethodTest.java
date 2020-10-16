package com.moqi.jvm.ch08;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * 掌控方法分派规则_2
 *
 * @author moqi On 10/16/20 17:22
 */

public class A14MethodTest {

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
                Field lookupImpl = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                lookupImpl.setAccessible(true);
                MethodHandle mh = ((MethodHandles.Lookup) lookupImpl.get(null)).findSpecial(GrandFather.class, "thinking", mt, GrandFather.class);
                mh.invoke(this);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    /**
     * I am grandfather
     */
    public static void main(String[] args) {
        new A14MethodTest().new Son().thinking();
    }

}
