package com.moqi.jvm.ch03;

/**
 * 长期存活的对象将进入老年代, 分别测试年龄阈值为 1 或者 15
 *
 * Before JDK9: -XX:+PrintGCDetails -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
 * Before JDK9: -XX:+PrintGCDetails -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
 *
 * After JDK9: -Xlog:gc* -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -Xlog:gc+age=trace
 * After JDK9: -Xlog:gc* -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -Xlog:gc+age=trace
 *
 * @author moqi On 10/13/20 10:19
 */

public class A05TestTenuringThreshold {

    private static final int _1MB = 1024 * 1024;

    /**
     * -XX:MaxTenuringThreshold=1
     *
     * [0.011s][info][gc] Using Serial
     * [0.011s][info][gc,heap,coops] Heap address: 0x00000007fec00000, size: 20 MB, Compressed Oops mode: Zero based, Oop shift amount: 3
     * [0.277s][info][gc,start     ] GC(0) Pause Young (Allocation Failure)
     * [0.280s][debug][gc,age       ] GC(0) Desired survivor size 524288 bytes, new threshold 1 (max threshold 1)
     * [0.280s][trace][gc,age       ] GC(0) Age table with threshold 1 (max threshold 1)
     * [0.280s][trace][gc,age       ] GC(0) - age   1:    1048576 bytes,    1048576 total
     * [0.280s][info ][gc,heap      ] GC(0) DefNew: 8192K->1024K(9216K)
     * [0.280s][info ][gc,heap      ] GC(0) Tenured: 0K->992K(10240K)
     * [0.280s][info ][gc,metaspace ] GC(0) Metaspace: 6503K->6503K(1056768K)
     * [0.280s][info ][gc           ] GC(0) Pause Young (Allocation Failure) 8M->1M(19M) 2.695ms
     * [0.280s][info ][gc,cpu       ] GC(0) User=0.00s Sys=0.00s Real=0.00s
     * [0.304s][info ][gc,start     ] GC(1) Pause Young (Allocation Failure)
     * [0.309s][debug][gc,age       ] GC(1) Desired survivor size 524288 bytes, new threshold 1 (max threshold 1)
     * [0.309s][trace][gc,age       ] GC(1) Age table with threshold 1 (max threshold 1)
     * [0.309s][trace][gc,age       ] GC(1) - age   1:    1048576 bytes,    1048576 total
     * [0.309s][info ][gc,heap      ] GC(1) DefNew: 7766K->1024K(9216K)
     * [0.309s][info ][gc,heap      ] GC(1) Tenured: 992K->6429K(10240K)
     * [0.309s][info ][gc,metaspace ] GC(1) Metaspace: 6927K->6927K(1056768K)
     * [0.309s][info ][gc           ] GC(1) Pause Young (Allocation Failure) 8M->7M(19M) 4.674ms
     * [0.309s][info ][gc,cpu       ] GC(1) User=0.01s Sys=0.00s Real=0.00s
     * [0.310s][info ][gc,start     ] GC(2) Pause Young (Allocation Failure)
     * [0.310s][info ][gc,start     ] GC(3) Pause Full (Allocation Failure)
     * [0.310s][info ][gc,phases,start] GC(3) Phase 1: Mark live objects
     * [0.312s][info ][gc,phases      ] GC(3) Phase 1: Mark live objects 2.270ms
     * [0.312s][info ][gc,phases,start] GC(3) Phase 2: Compute new object addresses
     * [0.313s][info ][gc,phases      ] GC(3) Phase 2: Compute new object addresses 0.302ms
     * [0.313s][info ][gc,phases,start] GC(3) Phase 3: Adjust pointers
     * [0.314s][info ][gc,phases      ] GC(3) Phase 3: Adjust pointers 1.178ms
     * [0.314s][info ][gc,phases,start] GC(3) Phase 4: Move objects
     * [0.314s][info ][gc,phases      ] GC(3) Phase 4: Move objects 0.703ms
     * [0.315s][info ][gc             ] GC(3) Pause Full (Allocation Failure) 11M->7M(19M) 4.666ms
     * [0.315s][info ][gc,heap        ] GC(2) DefNew: 5161K->0K(9216K)
     * [0.315s][info ][gc,heap        ] GC(2) Tenured: 6429K->7454K(10240K)
     * [0.315s][info ][gc,metaspace   ] GC(2) Metaspace: 6990K->6990K(1056768K)
     * [0.315s][info ][gc             ] GC(2) Pause Young (Allocation Failure) 11M->7M(19M) 4.714ms
     * [0.315s][info ][gc,cpu         ] GC(2) User=0.00s Sys=0.00s Real=0.01s
     * [0.315s][info ][gc,heap,exit   ] Heap
     * [0.315s][info ][gc,heap,exit   ]  def new generation   total 9216K, used 4418K [0x00000007fec00000, 0x00000007ff600000, 0x00000007ff600000)
     * [0.315s][info ][gc,heap,exit   ]   eden space 8192K,  53% used [0x00000007fec00000, 0x00000007ff050870, 0x00000007ff400000)
     * [0.315s][info ][gc,heap,exit   ]   from space 1024K,   0% used [0x00000007ff400000, 0x00000007ff400000, 0x00000007ff500000)
     * [0.315s][info ][gc,heap,exit   ]   to   space 1024K,   0% used [0x00000007ff500000, 0x00000007ff500000, 0x00000007ff600000)
     * [0.315s][info ][gc,heap,exit   ]  tenured generation   total 10240K, used 7454K [0x00000007ff600000, 0x0000000800000000, 0x0000000800000000)
     * [0.315s][info ][gc,heap,exit   ]    the space 10240K,  72% used [0x00000007ff600000, 0x00000007ffd47a00, 0x00000007ffd47a00, 0x0000000800000000)
     * [0.315s][info ][gc,heap,exit   ]  Metaspace       used 7023K, capacity 7158K, committed 7424K, reserved 1056768K
     * [0.315s][info ][gc,heap,exit   ]   class space    used 622K, capacity 686K, committed 768K, reserved 1048576K
     *
     * -XX:MaxTenuringThreshold=15
     *
     * [0.013s][info][gc] Using Serial
     * [0.013s][info][gc,heap,coops] Heap address: 0x00000007fec00000, size: 20 MB, Compressed Oops mode: Zero based, Oop shift amount: 3
     * [0.293s][info][gc,start     ] GC(0) Pause Young (Allocation Failure)
     * [0.296s][debug][gc,age       ] GC(0) Desired survivor size 524288 bytes, new threshold 1 (max threshold 15)
     * [0.296s][trace][gc,age       ] GC(0) Age table with threshold 1 (max threshold 15)
     * [0.296s][trace][gc,age       ] GC(0) - age   1:    1048568 bytes,    1048568 total
     * [0.296s][info ][gc,heap      ] GC(0) DefNew: 8192K->1023K(9216K)
     * [0.296s][info ][gc,heap      ] GC(0) Tenured: 0K->992K(10240K)
     * [0.296s][info ][gc,metaspace ] GC(0) Metaspace: 6510K->6510K(1056768K)
     * [0.296s][info ][gc           ] GC(0) Pause Young (Allocation Failure) 8M->1M(19M) 3.101ms
     * [0.296s][info ][gc,cpu       ] GC(0) User=0.00s Sys=0.00s Real=0.00s
     * [0.320s][info ][gc,start     ] GC(1) Pause Young (Allocation Failure)
     * [0.325s][debug][gc,age       ] GC(1) Desired survivor size 524288 bytes, new threshold 1 (max threshold 15)
     * [0.325s][trace][gc,age       ] GC(1) Age table with threshold 1 (max threshold 15)
     * [0.325s][trace][gc,age       ] GC(1) - age   1:    1048576 bytes,    1048576 total
     * [0.325s][info ][gc,heap      ] GC(1) DefNew: 7833K->1024K(9216K)
     * [0.325s][info ][gc,heap      ] GC(1) Tenured: 992K->6430K(10240K)
     * [0.325s][info ][gc,metaspace ] GC(1) Metaspace: 6994K->6994K(1056768K)
     * [0.325s][info ][gc           ] GC(1) Pause Young (Allocation Failure) 8M->7M(19M) 4.606ms
     * [0.325s][info ][gc,cpu       ] GC(1) User=0.01s Sys=0.00s Real=0.00s
     * [0.325s][info ][gc,start     ] GC(2) Pause Young (Allocation Failure)
     * [0.325s][info ][gc,start     ] GC(3) Pause Full (Allocation Failure)
     * [0.325s][info ][gc,phases,start] GC(3) Phase 1: Mark live objects
     * [0.327s][info ][gc,phases      ] GC(3) Phase 1: Mark live objects 1.968ms
     * [0.327s][info ][gc,phases,start] GC(3) Phase 2: Compute new object addresses
     * [0.328s][info ][gc,phases      ] GC(3) Phase 2: Compute new object addresses 0.264ms
     * [0.328s][info ][gc,phases,start] GC(3) Phase 3: Adjust pointers
     * [0.329s][info ][gc,phases      ] GC(3) Phase 3: Adjust pointers 0.950ms
     * [0.329s][info ][gc,phases,start] GC(3) Phase 4: Move objects
     * [0.329s][info ][gc,phases      ] GC(3) Phase 4: Move objects 0.497ms
     * [0.329s][info ][gc             ] GC(3) Pause Full (Allocation Failure) 11M->7M(19M) 3.831ms
     * [0.329s][info ][gc,heap        ] GC(2) DefNew: 5214K->0K(9216K)
     * [0.329s][info ][gc,heap        ] GC(2) Tenured: 6430K->7454K(10240K)
     * [0.329s][info ][gc,metaspace   ] GC(2) Metaspace: 6996K->6996K(1056768K)
     * [0.329s][info ][gc             ] GC(2) Pause Young (Allocation Failure) 11M->7M(19M) 3.920ms
     * [0.329s][info ][gc,cpu         ] GC(2) User=0.00s Sys=0.00s Real=0.01s
     * [0.330s][info ][gc,heap,exit   ] Heap
     * [0.330s][info ][gc,heap,exit   ]  def new generation   total 9216K, used 4399K [0x00000007fec00000, 0x00000007ff600000, 0x00000007ff600000)
     * [0.330s][info ][gc,heap,exit   ]   eden space 8192K,  53% used [0x00000007fec00000, 0x00000007ff04be08, 0x00000007ff400000)
     * [0.330s][info ][gc,heap,exit   ]   from space 1024K,   0% used [0x00000007ff400000, 0x00000007ff400000, 0x00000007ff500000)
     * [0.330s][info ][gc,heap,exit   ]   to   space 1024K,   0% used [0x00000007ff500000, 0x00000007ff500000, 0x00000007ff600000)
     * [0.330s][info ][gc,heap,exit   ]  tenured generation   total 10240K, used 7454K [0x00000007ff600000, 0x0000000800000000, 0x0000000800000000)
     * [0.330s][info ][gc,heap,exit   ]    the space 10240K,  72% used [0x00000007ff600000, 0x00000007ffd479f8, 0x00000007ffd47a00, 0x0000000800000000)
     * [0.330s][info ][gc,heap,exit   ]  Metaspace       used 7028K, capacity 7158K, committed 7424K, reserved 1056768K
     * [0.330s][info ][gc,heap,exit   ]   class space    used 622K, capacity 686K, committed 768K, reserved 1048576K
     */
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3;

        // 什么时候进入老年代决定于 XX:MaxTenuringThreshold 设置
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }

}
