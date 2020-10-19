package com.moqi.jvm.ch09;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理的简单示例
 *
 * @author moqi On 10/19/20 08:55
 */

public class A01DynamicProxyTest {

    interface IHello {
        void sayHello();
    }

    static class Hello implements IHello {
        @Override
        public void sayHello() {
            System.out.println("Hello world");
        }
    }

    static class DynamicProxy implements InvocationHandler {

        Object originalObj;

        Object bind(Object originalObj) {
            this.originalObj = originalObj;
            Class<?> aClass = originalObj.getClass();
            return Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Welcome");
            return method.invoke(originalObj, args);
        }
    }

    /**
     * Welcome
     * Hello world
     */
    public static void main(String[] args) {
        IHello hello = (IHello) new DynamicProxy().bind(new Hello());
        hello.sayHello();

        System.getProperties().put("sun.misc.proxyGenerator.saveGenerateFiles", "trur");
    }

}
