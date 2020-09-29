package com.moqi.jvm.ch02;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 借助CGLib使得方法区出现内存溢出异常
 * VM Args：-XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * 在JDK 7中的运行结果：
 * Caused by: java.lang.OutOfMemoryError: PermGen space
 *   at java.lang.ClassLoader.defineClass1(Native Method)
 *   at java.lang.ClassLoader.defineClassCond(ClassLoader.java:632)
 *   at java.lang.ClassLoader.defineClass(ClassLoader.java:616)
 *   ... 8 more
 *
 *
 *  本地无 JDK7 环境，暂时不测试
 *
 * @author moqi
 * On 9/29/20 14:42
 */
public class A05JavaMethodAreaOOM {

    public static void main(String[] args) {

        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });
            enhancer.create();

        }

    }

    static class OOMObject {
    }

}
