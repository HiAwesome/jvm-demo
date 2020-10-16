package com.moqi.jvm.ch08;

/**
 * 局部变量表槽复用对垃圾收集的影响之二
 *
 * -verbose:gc
 *
 * @author moqi On 10/16/20 09:46
 */

public class A02LocalVariableTable {

    /**
     * [GC (System.gc())  76062K->66520K(502784K), 0.0311763 secs]
     * [Full GC (System.gc())  66520K->66304K(502784K), 0.0102890 secs]
     */
    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        System.gc();
    }

}
