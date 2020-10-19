package com.moqi.jvm.ch10;

import java.util.Arrays;
import java.util.List;

/**
 * 自动装箱、拆箱与遍历循环
 *
 * @author moqi On 10/19/20 17:05
 */

public class A02AutoBox {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        int sum = 0;

        for (int i : list) {
            sum += i;
        }

        System.out.println("sum = " + sum);
    }

}
