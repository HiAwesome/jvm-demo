package com.moqi.jvm.ch08;

/**
 * 单分派、多分派演示
 *
 * @author moqi On 10/16/20 15:48
 */

public class A10Dispatch {

    static class QQ {}
    static class _360 {}

    public static class Father {
        public void hardChoice(QQ arg) {
            System.out.println("father choose QQ");
        }

        public void hardChoice(_360 arg) {
            System.out.println("father choose 360");
        }
    }

    public static class Son extends Father {
        public void hardChoice(QQ arg) {
            System.out.println("son choose QQ");
        }

        public void hardChoice(_360 arg) {
            System.out.println("son choose 360");
        }
    }

    /**
     * father choose 360
     * son choose QQ
     */
    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();

        father.hardChoice(new _360());
        son.hardChoice(new QQ());
    }


}
