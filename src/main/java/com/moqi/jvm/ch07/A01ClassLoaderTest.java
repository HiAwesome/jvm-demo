package com.moqi.jvm.ch07;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * 类加载器与 instanceof 关键字演示
 *
 * @author moqi On 10/15/20 17:27
 */

public class A01ClassLoaderTest {

    /**
     * obj.getClass() -> class com.moqi.jvm.ch07.A01ClassLoaderTest
     * obj instanceof com.moqi.jvm.ch07.A01ClassLoaderTest -> false
     */
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);

                    if (Objects.isNull(is)) {
                        return super.loadClass(name);
                    }

                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = myLoader.loadClass("com.moqi.jvm.ch07.A01ClassLoaderTest").newInstance();

        System.out.println("obj.getClass() -> " + obj.getClass());
        System.out.println("obj instanceof com.moqi.jvm.ch07.A01ClassLoaderTest -> " + (obj instanceof com.moqi.jvm.ch07.A01ClassLoaderTest));

    }

}
