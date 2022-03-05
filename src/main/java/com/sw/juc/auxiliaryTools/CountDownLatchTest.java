package com.sw.juc.auxiliaryTools;

import java.util.concurrent.CountDownLatch;

/**
 * @author Suaxi
 * @date 2022/3/1 17:45
 * 减法计数器
 * 模拟放学，学生离开教室，关门这个过程
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "走出教室");
                count.countDown();
            }, String.valueOf(i)).start();
        }

        //每次有线程调用countDown()方法，则计数器减1，如果计数器归零，则count.await()就会被唤醒，继续执行
        //需等待计数器归零，再向下执行，否则会出现学生还没走，教室门就已经关了的情况
        count.await();
        System.out.println("学生已全部离开教室，关门");
    }
}
