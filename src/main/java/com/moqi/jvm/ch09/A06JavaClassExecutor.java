package com.moqi.jvm.ch09;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Java Class Executor 的实现
 * <p>
 * Java Class 执行工具
 *
 * @author moqi On 10/19/20 10:29
 */

public class A06JavaClassExecutor {

    public static String execute(byte[] classByte) {
        A05HackSystem.clearBuffer();
        A04ClassModifier cm = new A04ClassModifier(classByte);
        byte[] modifyBytes = cm.modifyUTF8Constant("java/lang/System", "com/moqi/jvm/ch09/A05HackSystem");
        A02HotSwapClassLoader loader = new A02HotSwapClassLoader();
        Class clazz = loader.loadByte(modifyBytes);

        try {
            Method method = clazz.getMethod("main", new Class[]{String[].class});
            method.invoke(null, new String[]{null});
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(A05HackSystem.out);
        }

        return A05HackSystem.getBufferString();
    }

}
