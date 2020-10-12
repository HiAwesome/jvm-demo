package com.moqi.jvm.ch03;

/**
 * 此代码演示了亮点：
 * 1. 对象可以在被 GC 时自我拯救。
 * 2. 这种自救的机会只有一次，因为一个对象的 finalize 方法最多只会被系统自动调用一次
 *
 * @author moqi On 10/12/20 09:36
 */

public class A02FinalizeEscapeGC {

    public static A02FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("Yes, I am still alive :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Finalize method executed!");
        A02FinalizeEscapeGC.SAVE_HOOK = this;
    }

    /**
     * Finalize method executed!
     * Yes, I am still alive :)
     * No, I am dead :(
     */
    public static void main(String[] args) throws Throwable {
        SAVE_HOOK = new A02FinalizeEscapeGC();

        // 对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();

        // 因为 finalizer 方法优先级很低，暂停 0.5 秒等待它
        Thread.sleep(500);

        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("No, I am dead :(");
        }

        // 下面这段代码与上面的完全相同，但是这次自救却失败了
        SAVE_HOOK = null;
        System.gc();

        Thread.sleep(500);

        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("No, I am dead :(");
        }
    }
}
