package com.moqi.jvm.ch08;

/**
 * 未赋值的局部变量
 *
 * 在编译器就会报错
 *
 * @author moqi On 10/16/20 09:46
 */

public class A04NoValueLocalVariable {

    public static void main(String[] args) {
        int a;
        // Variable 'a' might not have been initialized
        // System.out.println("a = " + a);
    }

}
