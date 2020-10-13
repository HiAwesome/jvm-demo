package com.moqi.jvm.ch03;

/**
 * 大对象直接进入老年代
 *
 * Before JDK9: -XX:+PrintGCDetails -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
 *
 * After JDK9: -Xlog:gc* -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
 *
 * @author moqi On 10/13/20 09:53
 */

public class A04TestPretenureSizeThreshold {

    private static final int _1MB = 1024 * 1024;

    /**
     * [0.010s][info][gc] Using Serial
     * [0.010s][info][gc,heap,coops] Heap address: 0x00000007fec00000, size: 20 MB, Compressed Oops mode: Zero based, Oop shift amount: 3
     * [0.290s][info][gc,start     ] GC(0) Pause Young (Allocation Failure)
     * [0.294s][info][gc,heap      ] GC(0) DefNew: 8192K->1024K(9216K)
     * [0.294s][info][gc,heap      ] GC(0) Tenured: 0K->993K(10240K)
     * [0.294s][info][gc,metaspace ] GC(0) Metaspace: 6481K->6481K(1056768K)
     * [0.294s][info][gc           ] GC(0) Pause Young (Allocation Failure) 8M->1M(19M) 3.481ms
     * [0.294s][info][gc,cpu       ] GC(0) User=0.00s Sys=0.00s Real=0.00s
     * [0.322s][info][gc,heap,exit ] Heap
     * [0.322s][info][gc,heap,exit ]  def new generation   total 9216K, used 3563K [0x00000007fec00000, 0x00000007ff600000, 0x00000007ff600000)
     * [0.322s][info][gc,heap,exit ]   eden space 8192K,  31% used [0x00000007fec00000, 0x00000007fee7afd8, 0x00000007ff400000)
     * [0.322s][info][gc,heap,exit ]   from space 1024K, 100% used [0x00000007ff500000, 0x00000007ff600000, 0x00000007ff600000)
     * [0.322s][info][gc,heap,exit ]   to   space 1024K,   0% used [0x00000007ff400000, 0x00000007ff400000, 0x00000007ff500000)
     * [0.322s][info][gc,heap,exit ]  tenured generation   total 10240K, used 5089K [0x00000007ff600000, 0x0000000800000000, 0x0000000800000000)
     * [0.322s][info][gc,heap,exit ]    the space 10240K,  49% used [0x00000007ff600000, 0x00000007ffaf85b8, 0x00000007ffaf8600, 0x0000000800000000)
     * [0.322s][info][gc,heap,exit ]  Metaspace       used 7024K, capacity 7158K, committed 7424K, reserved 1056768K
     * [0.322s][info][gc,heap,exit ]   class space    used 626K, capacity 686K, committed 768K, reserved 1048576K
     */
    public static void main(String[] args) {
        byte[] allocation4;

        // 直接分配在老年代中
        allocation4 = new byte[4 * _1MB];
    }

}
