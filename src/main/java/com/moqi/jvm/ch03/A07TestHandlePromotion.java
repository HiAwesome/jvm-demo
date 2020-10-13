package com.moqi.jvm.ch03;

/**
 * 空间分配担保
 *
 * Before JDK9: -XX:+PrintGCDetails -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:SurvivorRatio=8 -XX:HandlePromotionFailure=true
 *
 * After JDK9: -Xlog:gc* -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:SurvivorRatio=8 -XX:PromotionFailureALot
 *
 * 在 JDK6 Update24 之后，这个测试结果就有了差异，-XX:HandlePromotionFailure 参数不会再影响到虚拟机的空间分配担保策略，
 * 观察 OpenJDK 中的源码变化（见代码清单3-12），虽然源码中还定义了 -X:HandlePromotionFailure 参数，但是在实际虚拟机中已经不会再使用它。
 * JDK6 Update24 之后的规则变为只要老年代的连续空间大于新生代对象总大小或者历次晋升的平均大小，就会进行 MinorGC，否则将进行 FullGC。
 *
 * @author moqi On 10/13/20 10:38
 */

public class A07TestHandlePromotion {

    private static final int _1MB = 1024 * 1024;

    /**
     * Error: VM option 'PromotionFailureALot' is notproduct and is available only in debug version of VM.
     * Error: Could not create the Java Virtual Machine.
     * Error: A fatal exception has occurred. Program will exit.
     *
     * 必须以 FAST_DEBUG 模式重新构建，参考 https://stackoverflow.com/a/32325693, 暂时停止此测试
     */
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;

        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation1 = null;


        allocation4 = new byte[2 * _1MB];
        allocation5 = new byte[2 * _1MB];
        allocation6 = new byte[2 * _1MB];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[2 * _1MB];
    }

}
