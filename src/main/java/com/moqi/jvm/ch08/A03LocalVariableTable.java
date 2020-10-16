package com.moqi.jvm.ch08;

/**
 * 局部变量表槽复用对垃圾收集的影响之三
 *
 * -verbose:gc
 *
 * @author moqi On 10/16/20 09:46
 */

public class A03LocalVariableTable {

    /**
     * [GC (System.gc())  76062K->1000K(502784K), 0.0008206 secs]
     * [Full GC (System.gc())  1000K->768K(502784K), 0.0050550 secs]
     */
    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }

        int a = 0;
        System.gc();
    }

}
