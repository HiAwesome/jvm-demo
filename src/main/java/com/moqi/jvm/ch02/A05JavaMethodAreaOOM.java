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
 * 本地无 JDK7 环境，暂时不测试
 *
 * JDK 8 参数：
 *
 * VM Args：-XX:MaxMetaspaceSize=10M -XX:MetaspaceSize=10M
 *
 * @author moqi
 * On 9/29/20 14:42
 */
public class A05JavaMethodAreaOOM {

    /**
     * WARNING: An illegal reflective access operation has occurred
     * WARNING: Illegal reflective access by net.sf.cglib.core.ReflectUtils$1 (file:/Users/moqi/Applications/maven/repository/cglib/cglib/3.3.0/cglib-3.3.0.jar) to method java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain)
     * WARNING: Please consider reporting this to the maintainers of net.sf.cglib.core.ReflectUtils$1
     * WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
     * WARNING: All illegal access operations will be denied in a future release
     * Exception in thread "main" net.sf.cglib.core.CodeGenerationException: java.lang.reflect.InvocationTargetException-->null
     * 	at net.sf.cglib.core.AbstractClassGenerator.generate(AbstractClassGenerator.java:348)
     * 	at net.sf.cglib.proxy.Enhancer.generate(Enhancer.java:492)
     * 	at net.sf.cglib.core.AbstractClassGenerator$ClassLoaderData.get(AbstractClassGenerator.java:117)
     * 	at net.sf.cglib.core.AbstractClassGenerator.create(AbstractClassGenerator.java:294)
     * 	at net.sf.cglib.proxy.Enhancer.createHelper(Enhancer.java:480)
     * 	at net.sf.cglib.proxy.Enhancer.create(Enhancer.java:305)
     * 	at com.moqi.jvm.ch02.A05JavaMethodAreaOOM.main(A05JavaMethodAreaOOM.java:42)
     * Caused by: java.lang.reflect.InvocationTargetException
     * 	at java.base/jdk.internal.reflect.GeneratedMethodAccessor1.invoke(Unknown Source)
     * 	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * 	at java.base/java.lang.reflect.Method.invoke(Method.java:567)
     * 	at net.sf.cglib.core.ReflectUtils.defineClass(ReflectUtils.java:459)
     * 	at net.sf.cglib.core.AbstractClassGenerator.generate(AbstractClassGenerator.java:339)
     * 	... 6 more
     * Caused by: java.lang.OutOfMemoryError: Metaspace
     * 	at java.base/java.lang.ClassLoader.defineClass1(Native Method)
     * 	at java.base/java.lang.ClassLoader.defineClass(ClassLoader.java:1016)
     * 	... 11 more
     */
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
