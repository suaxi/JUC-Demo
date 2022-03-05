package com.sw.juc.single;

/**
 * @author Suaxi
 * @date 2022/3/5 14:33
 * 懒汉式
 */
public class LazyMan {

    public LazyMan() {
        System.out.println(Thread.currentThread().getName() + " 加载完成");
    }

    private volatile static LazyMan lazyMan;

    //双重检测锁模式（DCL懒汉式）
    public static LazyMan getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan();
                    /**
                     * lazyMan = new LazyMan()不是一个原子性操作
                     * 1.分配内存空间
                     * 2.执行构造方法，初始化对象
                     * 3.将对象指向这个空间
                     * 此处可能存在指令重排的问题，所以必须使用volatile
                     */
                }
            }
        }
        return lazyMan;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                getInstance();
            }).start();
        }
    }
}
