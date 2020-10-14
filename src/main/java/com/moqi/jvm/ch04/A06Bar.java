package com.moqi.jvm.ch04;

/**
 * 测试 HSDIS 插件
 *
 * Mac 平台 从这里下载库 https://github.com/liuzhengyang/hsdis/blob/master/build/macosx-amd64/hsdis-amd64.dylib
 * 然后放在 /Library/Java/JavaVirtualMachines/jdk1.8.0_261.jdk/Contents/Home/jre/lib/server 目录下即可。
 *
 * 1. java -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -Xcomp A06Bar.java
 * 2. java -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -Xcomp -XX:Compile-Command=compileonly,*Bar.sum A06Bar.java
 *
 * 运行命令 1 可以看到一些汇编码，写入到文件中大约 37M，67 万行。
 * 运行命令 2 会报错 zsh: no matches found: -XX:Compile-Command=compileonly,*Bar.sum
 *
 *
 * @author moqi On 10/14/20 17:03
 */

public class A06Bar {

    int a = 1;
    static int b = 2;

    public int sum(int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        new A06Bar().sum(3);
    }

}
