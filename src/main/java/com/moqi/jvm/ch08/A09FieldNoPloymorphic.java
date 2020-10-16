package com.moqi.jvm.ch08;

/**
 * 字段没有多态性
 *
 * @author moqi On 10/16/20 15:41
 */

public class A09FieldNoPloymorphic {

    static class Father {
        public int money = 1;

        public Father() {
            money = 2;
            showMeTheMoney();
        }

        public void showMeTheMoney() {
            System.out.println("I am father, I have $" + money);
        }
    }

    static class Son extends Father {
        public int money = 3;

        public Son() {
            money = 4;
            showMeTheMoney();
        }

        public void showMeTheMoney() {
            System.out.println("I am son, I have $" + money);
        }

    }

    /**
     * I am son, I have $0
     * I am son, I have $4
     * This guy has $2
     */
    public static void main(String[] args) {
        Father guy = new Son();
        System.out.println("This guy has $" + guy.money);
    }

}
