package com.sw.juc.Lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Suaxi
 * @date 2022/3/5 15:49
 * 可重入锁
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
        Phone2 phone2 = new Phone2();

        new Thread(() -> {
            phone2.call();
        }, "A").start();

        new Thread(() -> {
            phone2.call();
        }, "b").start();
    }
}

class Phone1 {
    public synchronized void call() {
        System.out.println(Thread.currentThread().getName() + " ---> 打电话");
        //调用sms()，调用的同时也拿到了对应的锁
        sms();
    }

    public synchronized void sms() {
        System.out.println(Thread.currentThread().getName() + " ---> 发短信");
    }
}

class Phone2 {
    ReentrantLock lock = new ReentrantLock();

    public synchronized void call() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " ---> 打电话");
            //调用sms()，调用的同时也拿到了对应的锁，同理synchronized
            sms();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public synchronized void sms() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " ---> 发短信");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
