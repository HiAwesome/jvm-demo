package com.moqi.jvm.ch02;

/**
 * String.intern()返回引用的测试
 *
 * @author moqi
 * On 9/29/20 14:29
 */

public class A04RuntimeConstantPoolOOM02 {

    /**
     * true
     * true
     */
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }

}
