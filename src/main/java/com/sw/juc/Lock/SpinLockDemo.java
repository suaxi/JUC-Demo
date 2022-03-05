package com.sw.juc.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Suaxi
 * @date 2022/3/5 15:57
 * 自旋锁（CAS原则构建简单实例）
 */
public class SpinLockDemo {

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo lock = new SpinLockDemo();

        new Thread(() -> {
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }
        }, "A").start();

        TimeUnit.SECONDS.sleep(2);

        new Thread(() -> {
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }
        }, "B").start();
    }

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " ---> 拿到锁");
        //自旋
        while (!atomicReference.compareAndSet(null, thread)) {
            System.out.println(thread.getName() + " ---> 进入自旋，尝试加锁");
        }
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " ---> 解锁");
        atomicReference.compareAndSet(thread, null);
    }
}
