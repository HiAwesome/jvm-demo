package com.moqi.jvm.ch04;

import java.util.ArrayList;
import java.util.List;

/**
 * JConsole 测试 - 内存监控
 *
 * -Xms100m -Xmx100m -XX:+UseSerialGC
 *
 * @author moqi On 10/14/20 10:28
 */

public class A02MonitoringTest {

    /**
     * 内存占位符对象，一个 OOMObject 大约占据 64kb
     */
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            // 稍作延时，令监控曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
            System.out.println("i = " + i);
        }

        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }

}
