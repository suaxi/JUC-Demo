package com.sw.juc.blockingQueue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Suaxi
 * @date 2022/3/4 15:55
 * 同步队列
 */
public class SynchronousQueueTest {
    public static void main(String[] args) {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        //put之后必须取出put的值，才能进行下一次put
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " put 1");
                queue.put(1);
                System.out.println(Thread.currentThread().getName() + " put 2");
                queue.put(2);
                System.out.println(Thread.currentThread().getName() + " put 3");
                queue.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread 1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " get " + queue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " get " + queue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " get " + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread 2").start();
    }
}
