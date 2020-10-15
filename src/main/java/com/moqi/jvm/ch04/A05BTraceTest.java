package com.moqi.jvm.ch04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Btrace 动态加入逻辑练习
 * <p>
 * 暂时，对 JDK 11 支持有问题，https://github.com/btraceio/btrace/issues/374
 * 使用 Oracle 1.8.161 进行测试没有问题
 * <p>
 * 参考:
 * 1. https://gist.github.com/yulewei/53339ccced8837686895e3c9f45557cc
 * 2. https://github.com/btraceio/btrace
 * 3. https://01ms.blogspot.com/2012/08/java-application-profiling-using-visual.html
 *
 * @author moqi On 10/14/20 15:36
 */

public class A05BTraceTest {

    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) throws IOException {

        A05BTraceTest test = new A05BTraceTest();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            reader.readLine();
            int a = (int) Math.round(Math.random() * 1000);
            int b = (int) Math.round(Math.random() * 1000);
            System.out.println(test.add(a, b));
        }

    }

}

/*
Btrace 代码

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class TracingScript {
    @OnMethod(
    clazz="com.moqi.jvm.ch04.A05BTraceTest",
    method="add",
    location=@Location(Kind.RETURN)
)

public static void func(@Self com.moqi.jvm.ch04.A05BTraceTest instance, int a, int b, @Return int result) {
    println("调用堆栈:");
    jstack();
    println(strcat("方法参数A:",str(a)));
    println(strcat("方法参数B:",str(b)));
    println(strcat("方法结果:",str(result)));
}
}
*/

/**
Starting BTrace task
Compiling the BTrace script ...
Compiled
Instrumenting 1 classes ...
Done
BTrace up&running

Done
BTrace up&running

调用堆栈:
com.moqi.jvm.ch04.A05BTraceTest.add(A05BTraceTest.java:24)
com.moqi.jvm.ch04.A05BTraceTest.main(A05BTraceTest.java:36)
方法参数A:670
方法参数B:251
方法结果:921
调用堆栈:
com.moqi.jvm.ch04.A05BTraceTest.add(A05BTraceTest.java:24)
com.moqi.jvm.ch04.A05BTraceTest.main(A05BTraceTest.java:36)
方法参数A:889
方法参数B:717
方法结果:1606
调用堆栈:
com.moqi.jvm.ch04.A05BTraceTest.add(A05BTraceTest.java:24)
com.moqi.jvm.ch04.A05BTraceTest.main(A05BTraceTest.java:36)
方法参数A:130
方法参数B:23
方法结果:153
调用堆栈:
com.moqi.jvm.ch04.A05BTraceTest.add(A05BTraceTest.java:24)
com.moqi.jvm.ch04.A05BTraceTest.main(A05BTraceTest.java:36)
方法参数A:461
方法参数B:861
方法结果:1322
调用堆栈:
com.moqi.jvm.ch04.A05BTraceTest.add(A05BTraceTest.java:24)
com.moqi.jvm.ch04.A05BTraceTest.main(A05BTraceTest.java:36)
方法参数A:738
方法参数B:623
方法结果:1361
调用堆栈:
com.moqi.jvm.ch04.A05BTraceTest.add(A05BTraceTest.java:24)
com.moqi.jvm.ch04.A05BTraceTest.main(A05BTraceTest.java:36)
方法参数A:785
方法参数B:861
方法结果:1646
调用堆栈:
com.moqi.jvm.ch04.A05BTraceTest.add(A05BTraceTest.java:24)
com.moqi.jvm.ch04.A05BTraceTest.main(A05BTraceTest.java:36)
方法参数A:388
方法参数B:487
方法结果:875
调用堆栈:
com.moqi.jvm.ch04.A05BTraceTest.add(A05BTraceTest.java:24)
com.moqi.jvm.ch04.A05BTraceTest.main(A05BTraceTest.java:36)
方法参数A:266
方法参数B:424
** BTrace has stopped
方法结果:690
** BTrace has stopped
*/
