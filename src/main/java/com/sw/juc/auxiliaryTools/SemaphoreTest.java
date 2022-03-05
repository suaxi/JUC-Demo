package com.sw.juc.auxiliaryTools;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Suaxi
 * @date 2022/3/1 18:35
 * 抢车位
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    //获得，如果线程已经满了，则阻塞
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "停入车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放，将当前的信号量释放 +1，然后唤醒等待的线程
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
