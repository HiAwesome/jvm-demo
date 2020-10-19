package com.moqi.jvm.ch10;

/**
 * 不符合规范代码示例
 *
 * ~/Code/jvm-demo/src/main/java(master ✔) javac com/moqi/jvm/ch10/A03NameCheck.java
 * ~/Code/jvm-demo/src/main/java(master ✔) javac com/moqi/jvm/ch10/A04NameCheckProcessor.java
 * ~/Code/jvm-demo/src/main/java(master ✔) ll
 * total 0
 * drwxr-xr-x  3 moqi  staff    96B Oct 13 20:45 com
 * ~/Code/jvm-demo/src/main/java(master ✔) javac -processor com.moqi.jvm.ch10.A04NameCheckProcessor com/moqi/jvm/ch10/A05_BADLY_NAMED_CODE.java
 * com/moqi/jvm/ch10/A05_BADLY_NAMED_CODE.java:9: warning: 名称 A05_BADLY_NAMED_CODE 应当符合驼式命名法
 * public class A05_BADLY_NAMED_CODE {
 *        ^
 * com/moqi/jvm/ch10/A05_BADLY_NAMED_CODE.java:11: warning: 名称 colors 应当以大写字母开头
 *     enum colors {
 *     ^
 * com/moqi/jvm/ch10/A05_BADLY_NAMED_CODE.java:12: warning: 常量 red 应当全部以大写字母或下划线命名，并且以字母开头
 *         red, blue, green
 *         ^
 * com/moqi/jvm/ch10/A05_BADLY_NAMED_CODE.java:12: warning: 常量 blue 应当全部以大写字母或下划线命名，并且以字母开头
 *         red, blue, green
 *              ^
 * com/moqi/jvm/ch10/A05_BADLY_NAMED_CODE.java:12: warning: 常量 green 应当全部以大写字母或下划线命名，并且以字母开头
 *         red, blue, green
 *                    ^
 * com/moqi/jvm/ch10/A05_BADLY_NAMED_CODE.java:15: warning: 常量 _FORTY_TWO 应当全部以大写字母或下划线命名，并且以字母开头
 *     static final int _FORTY_TWO = 42;
 *                      ^
 * com/moqi/jvm/ch10/A05_BADLY_NAMED_CODE.java:17: warning: 名称 NOT_A_CONSTANT 应当以小写字母开头
 *     public static int NOT_A_CONSTANT = _FORTY_TWO;
 *                       ^
 * com/moqi/jvm/ch10/A05_BADLY_NAMED_CODE.java:19: warning: 名称 Test 应当以小写字母开头
 *     protected void Test() {
 *                    ^
 * com/moqi/jvm/ch10/A05_BADLY_NAMED_CODE.java:23: warning: 名称 NOTcamelCASEmethodNAME 应当以小写字母开头
 *     public void NOTcamelCASEmethodNAME() {
 *                 ^
 * 9 warnings
 *
 * @author moqi On 10/19/20 19:24
 */

public class A05_BADLY_NAMED_CODE {

    enum colors {
        red, blue, green
    }

    static final int _FORTY_TWO = 42;

    public static int NOT_A_CONSTANT = _FORTY_TWO;

    protected void Test() {
        return;
    }

    public void NOTcamelCASEmethodNAME() {
        return;
    }

}
