package com.moqi.jvm.ch12;

/**
 * 双重检查锁单例模式
 *
 * @author moqi On 10/20/20 19:24
 */

public class A02Singleton {

    private volatile static A02Singleton instance;

    private A02Singleton() {}

    public static A02Singleton getInstance() {
        if (instance == null) {
            synchronized (A02Singleton.class) {
                if (instance == null) {
                    instance = new A02Singleton();
                }
            }
        }

        return instance;
    }

    public static void main(String[] args) {
        A02Singleton.getInstance();
    }
}
