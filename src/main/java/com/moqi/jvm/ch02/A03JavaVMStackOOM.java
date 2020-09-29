package com.moqi.jvm.ch02;

/**
 * VM Args：-Xss2M （这时候不妨设大些，请在32位系统下运行）
 * Exception in thread "main" java.lang.OutOfMemoryError: unable to create native thread
 *
 * 本地无 32 位系统环境，所以暂时无法出现效果
 *
 * @author moqi
 * On 9/29/20 14:11
 */

public class A03JavaVMStackOOM {

    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeakByThread() {

        while (true) {
            Thread thread = new Thread(this::dontStop);

            thread.start();
        }

    }

    public static void main(String[] args) {
        A03JavaVMStackOOM oom = new A03JavaVMStackOOM();
        oom.stackLeakByThread();
    }

}
