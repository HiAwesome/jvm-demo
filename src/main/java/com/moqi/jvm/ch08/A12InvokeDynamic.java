package com.moqi.jvm.ch08;

import java.lang.invoke.*;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * invoke dynamic 指令
 *
 * @author moqi On 10/16/20 16:58
 */

public class A12InvokeDynamic {

    public static void main(String[] args) throws Throwable {
        INDY_BootstrapMethod().invokeExact("main function");
    }

    public static void testMethod(String s) {
        // Hello world: main function
        System.out.println("Hello world: " + s);
    }

    public static CallSite BootstrapMethod(MethodHandles.Lookup lookup, String name, MethodType mt) throws NoSuchMethodException, IllegalAccessException {
        return new ConstantCallSite(lookup.findStatic(A12InvokeDynamic.class, name, mt));
    }

    private static MethodType MT_BootstrapMethod() {
        return MethodType.fromMethodDescriptorString(
                "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;",
                null);
    }

    private static MethodHandle MH_BootstrapMethod() throws NoSuchMethodException, IllegalAccessException {
        return lookup().findStatic(A12InvokeDynamic.class, "BootstrapMethod", MT_BootstrapMethod());
    }

    private static MethodHandle INDY_BootstrapMethod() throws Throwable {
        CallSite cs = (CallSite) MH_BootstrapMethod().invokeWithArguments(lookup(), "testMethod",
                MethodType.fromMethodDescriptorString("(Ljava/lang/String;)V", null));
        return cs.dynamicInvoker();
    }

}
