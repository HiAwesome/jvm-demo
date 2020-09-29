package com.moqi.jvm.ch02;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author moqi
 * On 9/29/20 11:28
 */

public class A01HeapOOM {

    static class OOMObject {
    }

    /**
     * java.lang.OutOfMemoryError: Java heap space
     * Dumping heap to java_pid62747.hprof ...
     * Heap dump file created [22825142 bytes in 0.118 secs]
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     * 	at java.base/java.util.Arrays.copyOf(Arrays.java:3721)
     * 	at java.base/java.util.Arrays.copyOf(Arrays.java:3690)
     * 	at java.base/java.util.ArrayList.grow(ArrayList.java:237)
     * 	at java.base/java.util.ArrayList.grow(ArrayList.java:242)
     * 	at java.base/java.util.ArrayList.add(ArrayList.java:485)
     * 	at java.base/java.util.ArrayList.add(ArrayList.java:498)
     * 	at com.moqi.jvm.ch02.A01HeapOOM.main(A01HeapOOM.java:34)
     */
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while (true) {
            list.add(new OOMObject());
        }

    }

}
