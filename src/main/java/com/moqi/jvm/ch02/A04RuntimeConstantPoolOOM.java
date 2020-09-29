package com.moqi.jvm.ch02;


import java.util.HashSet;
import java.util.Set;

/**
 * JDK 6
 * VM Args：-XX:PermSize=6M -XX:MaxPermSize=6M
 *
 * 原文：
 * 请读者测试时首先以JDK 6来运行代码
 * Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
 *   at java.lang.String.intern(Native Method)
 *   at org.fenixsoft.oom.RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java: 18)
 *
 * 本地无 JDK6 环境不再重现。
 * 从运行结果中可以看到，运行时常量池溢出时，在OutOfMemoryError异常后面跟随的提示信息是“PermGen space”，
 * 说明运行时常量池的确是属于方法区（即JDK 6的HotSpot虚拟机中的永久代）的 一部分。
 *
 * 而使用JDK 7或更高版本的JDK来运行这段程序并不会得到相同的结果，无论是在JDK 7中继续使用-XX：MaxPermSize参数
 * 或者在JDK8及以上版本使用-XX：MaxMeta-spaceSize参数把方法区容量同样限制在6MB，也都不会重现JDK 6中的溢出异常，循环将一直进行下去，永不停歇。
 * 出现这种变化，是因为自JDK7起，原本存放在永久代的字符串常量池被移至Java堆之中，所以在JDK 7及以上版本，
 * 限制方法区的容量对该测试用例来说是毫无意义的。这时候使用-Xmx参数限制最大堆到6MB就能够看到以下两种运行结果之一，
 * 具体取决于哪里的对象分配时产生了溢出：
 * 1. Exception in thread "main" java.lang.OutOfMemoryError: Java heap space at java.base/java.lang.Integer.toString(Integer.java:440)
 * 2. Exception in thread "main" java.lang.OutOfMemoryError: Java heap space at java.base/java.util.HashMap.resize(HashMap.java:699)
 *
 * JDK 7 及其之上：-Xmx6M
 *
 * @author moqi
 * On 9/29/20 14:19
 */
public class A04RuntimeConstantPoolOOM {

    /**
     * Exception in thread "main" Exception in thread "Monitor Ctrl-Break" java.lang.OutOfMemoryError: Java heap space
     * 	at java.base/java.lang.Integer.toString(Integer.java:446)
     * 	at java.base/java.lang.String.valueOf(String.java:3149)
     * 	at com.moqi.jvm.ch02.A04RuntimeConstantPoolOOM.main(A04RuntimeConstantPoolOOM.java:32)
     * java.lang.OutOfMemoryError: Java heap space
     * 	at java.base/sun.net.spi.DefaultProxySelector$4.run(DefaultProxySelector.java:237)
     * 	at java.base/sun.net.spi.DefaultProxySelector$4.run(DefaultProxySelector.java:221)
     * 	at java.base/java.security.AccessController.executePrivileged(AccessController.java:750)
     * 	at java.base/java.security.AccessController.doPrivileged(AccessController.java:310)
     * 	at java.base/sun.net.spi.DefaultProxySelector.select(DefaultProxySelector.java:220)
     * 	at java.base/java.net.SocksSocketImpl.connect(SocksSocketImpl.java:395)
     * 	at java.base/java.net.Socket.connect(Socket.java:591)
     * 	at java.base/java.net.Socket.connect(Socket.java:540)
     * 	at java.base/java.net.Socket.<init>(Socket.java:436)
     * 	at java.base/java.net.Socket.<init>(Socket.java:213)
     * 	at com.intellij.rt.execution.application.AppMainV2$1.run(AppMainV2.java:56)
     */
    public static void main(String[] args) {
        // 使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<>();
        // 在short范围内足以让6MB的PermSize产生OOM了
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }

}
