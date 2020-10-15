package com.moqi.jvm.ch06;

/**
 * 测试同步
 *
 * @author moqi On 10/15/20 14:56
 */

public class A03SyncTest {

    void onlyMe(Foo f) {
        synchronized (f) {
            doSomething();
        }
    }

    private void doSomething() {
        System.out.println("A03SyncTest.doSomething");
    }

    private static class Foo {
    }
}
