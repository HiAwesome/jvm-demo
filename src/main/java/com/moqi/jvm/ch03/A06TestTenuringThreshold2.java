package com.moqi.jvm.ch03;

/**
 * 动态对象年龄判定
 *
 * Before JDK9: -XX:+PrintGCDetails -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
 *
 * After JDK9: -Xlog:gc* -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -Xlog:gc+age=trace
 *
 * @author moqi On 10/13/20 10:19
 */

public class A06TestTenuringThreshold2 {

    private static final int _1MB = 1024 * 1024;

    /**
     * [0.011s][info][gc] Using Serial
     * [0.011s][info][gc,heap,coops] Heap address: 0x00000007fec00000, size: 20 MB, Compressed Oops mode: Zero based, Oop shift amount: 3
     * [0.277s][info][gc,start     ] GC(0) Pause Young (Allocation Failure)
     * [0.281s][debug][gc,age       ] GC(0) Desired survivor size 524288 bytes, new threshold 1 (max threshold 15)
     * [0.281s][trace][gc,age       ] GC(0) Age table with threshold 1 (max threshold 15)
     * [0.281s][trace][gc,age       ] GC(0) - age   1:    1048568 bytes,    1048568 total
     * [0.281s][info ][gc,heap      ] GC(0) DefNew: 8192K->1023K(9216K)
     * [0.281s][info ][gc,heap      ] GC(0) Tenured: 0K->992K(10240K)
     * [0.281s][info ][gc,metaspace ] GC(0) Metaspace: 6492K->6492K(1056768K)
     * [0.281s][info ][gc           ] GC(0) Pause Young (Allocation Failure) 8M->1M(19M) 3.136ms
     * [0.281s][info ][gc,cpu       ] GC(0) User=0.01s Sys=0.00s Real=0.00s
     * [0.305s][info ][gc,start     ] GC(1) Pause Young (Allocation Failure)
     * [0.310s][debug][gc,age       ] GC(1) Desired survivor size 524288 bytes, new threshold 1 (max threshold 15)
     * [0.310s][trace][gc,age       ] GC(1) Age table with threshold 1 (max threshold 15)
     * [0.310s][trace][gc,age       ] GC(1) - age   1:    1048576 bytes,    1048576 total
     * [0.310s][info ][gc,heap      ] GC(1) DefNew: 8021K->1024K(9216K)
     * [0.310s][info ][gc,heap      ] GC(1) Tenured: 992K->6688K(10240K)
     * [0.310s][info ][gc,metaspace ] GC(1) Metaspace: 6994K->6994K(1056768K)
     * [0.310s][info ][gc           ] GC(1) Pause Young (Allocation Failure) 8M->7M(19M) 4.518ms
     * [0.310s][info ][gc,cpu       ] GC(1) User=0.00s Sys=0.00s Real=0.00s
     * [0.310s][info ][gc,start     ] GC(2) Pause Young (Allocation Failure)
     * [0.310s][info ][gc,start     ] GC(3) Pause Full (Allocation Failure)
     * [0.310s][info ][gc,phases,start] GC(3) Phase 1: Mark live objects
     * [0.312s][info ][gc,phases      ] GC(3) Phase 1: Mark live objects 1.910ms
     * [0.312s][info ][gc,phases,start] GC(3) Phase 2: Compute new object addresses
     * [0.312s][info ][gc,phases      ] GC(3) Phase 2: Compute new object addresses 0.265ms
     * [0.312s][info ][gc,phases,start] GC(3) Phase 3: Adjust pointers
     * [0.313s][info ][gc,phases      ] GC(3) Phase 3: Adjust pointers 0.924ms
     * [0.313s][info ][gc,phases,start] GC(3) Phase 4: Move objects
     * [0.314s][info ][gc,phases      ] GC(3) Phase 4: Move objects 0.463ms
     * [0.314s][info ][gc             ] GC(3) Pause Full (Allocation Failure) 11M->7M(19M) 3.688ms
     * [0.314s][info ][gc,heap        ] GC(2) DefNew: 5160K->0K(9216K)
     * [0.314s][info ][gc,heap        ] GC(2) Tenured: 6688K->7712K(10240K)
     * [0.314s][info ][gc,metaspace   ] GC(2) Metaspace: 6995K->6995K(1056768K)
     * [0.314s][info ][gc             ] GC(2) Pause Young (Allocation Failure) 11M->7M(19M) 3.732ms
     * [0.314s][info ][gc,cpu         ] GC(2) User=0.00s Sys=0.00s Real=0.01s
     * [0.315s][info ][gc,heap,exit   ] Heap
     * [0.315s][info ][gc,heap,exit   ]  def new generation   total 9216K, used 4364K [0x00000007fec00000, 0x00000007ff600000, 0x00000007ff600000)
     * [0.315s][info ][gc,heap,exit   ]   eden space 8192K,  53% used [0x00000007fec00000, 0x00000007ff0433e0, 0x00000007ff400000)
     * [0.315s][info ][gc,heap,exit   ]   from space 1024K,   0% used [0x00000007ff400000, 0x00000007ff400000, 0x00000007ff500000)
     * [0.315s][info ][gc,heap,exit   ]   to   space 1024K,   0% used [0x00000007ff500000, 0x00000007ff500000, 0x00000007ff600000)
     * [0.315s][info ][gc,heap,exit   ]  tenured generation   total 10240K, used 7712K [0x00000007ff600000, 0x0000000800000000, 0x0000000800000000)
     * [0.315s][info ][gc,heap,exit   ]    the space 10240K,  75% used [0x00000007ff600000, 0x00000007ffd882a8, 0x00000007ffd88400, 0x0000000800000000)
     * [0.315s][info ][gc,heap,exit   ]  Metaspace       used 7026K, capacity 7158K, committed 7424K, reserved 1056768K
     * [0.315s][info ][gc,heap,exit   ]   class space    used 626K, capacity 686K, committed 768K, reserved 1048576K
     */
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;

        // allocation1 + allocation2 大于 survivor 空间一半
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB / 4];
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
    }

}
