package com.moqi.jvm.ch03;

/**
 * testGC()方法执行后，objA和objB会不会被GC呢？
 *
 * -XX:+PrintGC -XX:+PrintGCDetails
 *
 * -XX:+PrintGC is deprecated. Will use -Xlog:gc instead.
 * -XX:+PrintGCDetails is deprecated. Will use -Xlog:gc* instead.
 *
 * -Xlog:gc -Xlog:gc*
 *
 * @author moqi On 10/12/20 09:13
 */

public class A01ReferenceCountingGC {

    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    /**
     * 这个成员属性的唯一意义就是占点内存，以便能在GC日志中看清楚是否有回收过
     */
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC() {

        A01ReferenceCountingGC objA = new A01ReferenceCountingGC();
        A01ReferenceCountingGC objB = new A01ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        // 假设在这行发生GC，objA和objB是否能被回收？
        System.gc();

    }

    /**
     * [0.013s][info][gc,heap] Heap region size: 2M
     * [0.016s][info][gc     ] Using G1
     * [0.016s][info][gc,heap,coops] Heap address: 0x0000000600000000, size: 8192 MB, Compressed Oops mode: Zero based, Oop shift amount: 3
     * [0.151s][info][gc           ] Periodic GC disabled
     * [0.556s][info][gc,task      ] GC(0) Using 10 workers of 10 for full compaction
     * [0.556s][info][gc,start     ] GC(0) Pause Full (System.gc())
     * [0.556s][info][gc,phases,start] GC(0) Phase 1: Mark live objects
     * [0.557s][info][gc,stringtable ] GC(0) Cleaned string table, strings: 1427 processed, 10 removed
     * [0.557s][info][gc,phases      ] GC(0) Phase 1: Mark live objects 1.007ms
     * [0.557s][info][gc,phases,start] GC(0) Phase 2: Prepare for compaction
     * [0.558s][info][gc,phases      ] GC(0) Phase 2: Prepare for compaction 0.942ms
     * [0.558s][info][gc,phases,start] GC(0) Phase 3: Adjust pointers
     * [0.559s][info][gc,phases      ] GC(0) Phase 3: Adjust pointers 0.744ms
     * [0.559s][info][gc,phases,start] GC(0) Phase 4: Compact heap
     * [0.560s][info][gc,phases      ] GC(0) Phase 4: Compact heap 1.014ms
     * [0.561s][info][gc,heap        ] GC(0) Eden regions: 6->0(6)
     * [0.561s][info][gc,heap        ] GC(0) Survivor regions: 0->0(0)
     * [0.561s][info][gc,heap        ] GC(0) Old regions: 0->6
     * [0.561s][info][gc,heap        ] GC(0) Archive regions: 0->0
     * [0.561s][info][gc,heap        ] GC(0) Humongous regions: 4->0
     * [0.561s][info][gc,metaspace   ] GC(0) Metaspace: 6996K->6996K(1056768K)
     * [0.561s][info][gc             ] GC(0) Pause Full (System.gc()) 18M->3M(40M) 5.311ms
     * [0.561s][info][gc,cpu         ] GC(0) User=0.01s Sys=0.01s Real=0.01s
     * [0.563s][info][gc,heap,exit   ] Heap
     * [0.563s][info][gc,heap,exit   ]  garbage-first heap   total 40960K, used 3085K [0x0000000600000000, 0x0000000800000000)
     * [0.563s][info][gc,heap,exit   ]   region size 2048K, 1 young (2048K), 0 survivors (0K)
     * [0.563s][info][gc,heap,exit   ]  Metaspace       used 7026K, capacity 7126K, committed 7424K, reserved 1056768K
     * [0.563s][info][gc,heap,exit   ]   class space    used 621K, capacity 654K, committed 768K, reserved 1048576K
     *
     * 18M->3M(40M) 意味着虚拟机并没有因为这两个对象互相引用就放弃回收它们。
     */
    public static void main(String[] args) {
        testGC();
    }

}
