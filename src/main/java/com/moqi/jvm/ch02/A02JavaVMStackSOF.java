package com.moqi.jvm.ch02;

/**
 * VM Args: -Xss128k
 * The Java thread stack size specified is too small. Specify at least 144k
 * Error: Could not create the Java Virtual Machine.
 * Error: A fatal exception has occurred. Program will exit.
 *
 * Update VM Args: -Xss144k
 *
 * @author moqi
 * On 9/29/20 11:46
 */

public class A02JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    /**
     * stack length: 498
     * Exception in thread "main" java.lang.StackOverflowError
     * 	at com.moqi.jvm.ch02.A02JavaVMStackSOF.stackLeak(A02JavaVMStackSOF.java:20)
     * 	at com.moqi.jvm.ch02.A02JavaVMStackSOF.stackLeak(A02JavaVMStackSOF.java:21)
     * 	at com.moqi.jvm.ch02.A02JavaVMStackSOF.stackLeak(A02JavaVMStackSOF.java:21)
     * 	at com.moqi.jvm.ch02.A02JavaVMStackSOF.stackLeak(A02JavaVMStackSOF.java:21)
     * 	...
     * 	at com.moqi.jvm.ch02.A02JavaVMStackSOF.main(A02JavaVMStackSOF.java:28)
     */
    public static void main(String[] args) {
        A02JavaVMStackSOF oom = new A02JavaVMStackSOF();

        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length: " + oom.stackLength);
            throw e;
        }
    }

}
