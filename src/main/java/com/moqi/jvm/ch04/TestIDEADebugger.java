package com.moqi.jvm.ch04;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 练习 IDEA Debugger 功能
 *
 * 练习: https://moqimoqidea.github.io/2017/06/02/IDEA-Debugger/
 *
 * @author moqi On 10/15/20 16:22
 */

public class TestIDEADebugger {

    public static void main(String[] args) {
        testJavaStream();


        TestIDEADebugger t = new TestIDEADebugger();
        t.getArithmeticException(10);
    }

    private static void testJavaStream() {
        List<Integer> numberList = Arrays.asList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 0);
        System.out.println("sum is: " +
                numberList.stream()
                        .filter(Objects::nonNull)
                        .distinct()
                        .mapToInt(num -> num * 2)
                        .peek(System.out::println)
                        .skip(2)
                        .limit(4)
                        .sum()
        );
    }

    private int getMax(int a, int b) {
        return Math.min(a, b);
    }

    private int getArithmeticException(int i) {
        int max = getMax(i, 100);
        System.out.println("max = " + max);
        return i / 0;
    }

}
