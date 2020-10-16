package com.moqi.jvm.ch08;

/**
 * 局部变量表槽复用对垃圾收集的影响之一
 *
 * -verbose:gc
 *
 * @author moqi On 10/16/20 09:46
 */

public class A01LocalVariableTable {

    /**
     * [GC (System.gc())  76062K->66536K(502784K), 0.0327328 secs]
     * [Full GC (System.gc())  66536K->66304K(502784K), 0.0107196 secs]
     */
    public static void main(String[] args) {
        byte[] placeholder = new byte[64 * 1024 * 1024];
        System.gc();
    }

}
