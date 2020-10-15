package com.moqi.jvm.ch06;

/**
 * <clinit>() 方法执行顺序
 *
 * @author moqi On 10/15/20 17:14
 */

public class A12InitOrder {

    static class Parent {
        public static int A = 1;

        static {
            A = 2;
        }
    }

    static class Sub extends Parent {
        public static int B = A;
    }

    public static void main(String[] args) {
        // Sub.B = 2
        System.out.println("Sub.B = " + Sub.B);
    }


}
