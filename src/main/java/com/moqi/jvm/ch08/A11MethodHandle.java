package com.moqi.jvm.ch08;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.util.Random;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * MethodHandle 基础用法演示
 * JSR 292 https://jcp.org/en/jsr/detail?id=292
 *
 * @author moqi On 10/16/20 16:14
 */

public class A11MethodHandle {

    static class ClassA {
        public void println(String s) {
            System.out.println("ClassA println s: " + s);
        }
    }

    /**
     * 结果在 ClassA println s: Hello world 和 Hello world 中切换
     */
    public static void main(String[] args) throws Throwable {
        Object obj = new Random().nextBoolean() ? System.out : new ClassA();
        // 无论 obj 最终是那个实现类，下面这句都能正确调用到 println 方法
        getPrintlnMH(obj).invokeExact("Hello world");
    }

    private static MethodHandle getPrintlnMH(Object receiver) throws NoSuchMethodException, IllegalAccessException {
        // MethodType 方法代表 方法类型，包含了方法的返回值（methodType() 第一个参数）和具体参数（methodType() 第二个及以后的参数）
        MethodType mt = MethodType.methodType(void.class, String.class);
        // lookup() 方法来自于 MethodHandles.lookup, 这句的作用是在指定类中查找符合给定的方法名称、方法类型
        // 并且符合调用权限的方法句柄
        // 因为这里调用的是一个虚方法，按照 Java 语言的规则，方法的第一个参数是隐式的，代表该方法的接受者，也即 this 指向的对象
        // 这个参数以前是放在参数列表中进行传递，现在提供了 bindTo() 方法来完成这件事情
        return lookup().findVirtual(receiver.getClass(), "println", mt).bindTo(receiver);
    }

}
