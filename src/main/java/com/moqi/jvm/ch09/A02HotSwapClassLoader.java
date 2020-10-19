package com.moqi.jvm.ch09;

/**
 * HotSwapClassLoader 的实现
 *
 * @author moqi On 10/19/20 09:30
 */

public class A02HotSwapClassLoader extends ClassLoader {

    public A02HotSwapClassLoader() {
        super(A02HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadByte(byte[] classByte) {
        return defineClass(null, classByte, 0, classByte.length);
    }
}
