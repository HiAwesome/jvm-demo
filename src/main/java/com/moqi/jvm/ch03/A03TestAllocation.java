package com.moqi.jvm.ch03;

/**
 * 对象优先在 Eden 分配
 *
 * Before JDK9: -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 *
 * After JDK9: -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:SurvivorRatio=8 -Xlog:gc*
 *
 * @author moqi On 10/13/20 09:53
 */

public class A03TestAllocation {

    private static final int _1MB = 1024 * 1024;

    /**
     * [0.010s][info][gc] Using Serial
     * [0.010s][info][gc,heap,coops] Heap address: 0x00000007fec00000, size: 20 MB, Compressed Oops mode: Zero based, Oop shift amount: 3
     * [0.313s][info][gc,start     ] GC(0) Pause Young (Allocation Failure)
     * [0.316s][info][gc,heap      ] GC(0) DefNew: 8192K->1023K(9216K)
     * [0.316s][info][gc,heap      ] GC(0) Tenured: 0K->993K(10240K)
     * [0.316s][info][gc,metaspace ] GC(0) Metaspace: 6505K->6505K(1056768K)
     * [0.316s][info][gc           ] GC(0) Pause Young (Allocation Failure) 8M->1M(19M) 3.164ms
     * [0.316s][info][gc,cpu       ] GC(0) User=0.01s Sys=0.00s Real=0.01s
     * [0.343s][info][gc,start     ] GC(1) Pause Young (Allocation Failure)
     * [0.348s][info][gc,heap      ] GC(1) DefNew: 7577K->1023K(9216K)
     * [0.348s][info][gc,heap      ] GC(1) Tenured: 993K->6176K(10240K)
     * [0.348s][info][gc,metaspace ] GC(1) Metaspace: 7007K->7007K(1056768K)
     * [0.348s][info][gc           ] GC(1) Pause Young (Allocation Failure) 8M->7M(19M) 4.880ms
     * [0.348s][info][gc,cpu       ] GC(1) User=0.01s Sys=0.00s Real=0.01s
     * [0.349s][info][gc,heap,exit ] Heap
     * [0.349s][info][gc,heap,exit ]  def new generation   total 9216K, used 7504K [0x00000007fec00000, 0x00000007ff600000, 0x00000007ff600000)
     * [0.349s][info][gc,heap,exit ]   eden space 8192K,  79% used [0x00000007fec00000, 0x00000007ff254108, 0x00000007ff400000)
     * [0.349s][info][gc,heap,exit ]   from space 1024K,  99% used [0x00000007ff400000, 0x00000007ff4ffff8, 0x00000007ff500000)
     * [0.349s][info][gc,heap,exit ]   to   space 1024K,   0% used [0x00000007ff500000, 0x00000007ff500000, 0x00000007ff600000)
     * [0.349s][info][gc,heap,exit ]  tenured generation   total 10240K, used 6176K [0x00000007ff600000, 0x0000000800000000, 0x0000000800000000)
     * [0.349s][info][gc,heap,exit ]    the space 10240K,  60% used [0x00000007ff600000, 0x00000007ffc08098, 0x00000007ffc08200, 0x0000000800000000)
     * [0.349s][info][gc,heap,exit ]  Metaspace       used 7029K, capacity 7158K, committed 7424K, reserved 1056768K
     * [0.349s][info][gc,heap,exit ]   class space    used 622K, capacity 686K, committed 768K, reserved 1048576K
     *
     */
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;

        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB]; // 出现一次Minor GC
    }

}
