package com.sw.juc.volatileDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Suaxi
 * @date 2022/3/5 13:53
 * volatile：不保证原子性
 */
public class JMMTest02 {

    //private volatile static int num = 0;
    private volatile static AtomicInteger num = new AtomicInteger();

    public static void add() {
        //num++;
        //num++ 不是一个原子性操作，可以加锁来解决这个问题，但此处不使用锁，使用juc提供的类来解决该问题
        num.getAndIncrement();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "线程的执行结果为：" + num);
    }
}
