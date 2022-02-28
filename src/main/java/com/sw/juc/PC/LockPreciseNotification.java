package com.sw.juc.PC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Suaxi
 * @date 2022/2/28 13:40
 * lock锁精准通知：A执行完通知B，B执行完通知C，C执行完通知A
 */
public class LockPreciseNotification {
    public static void main(String[] args) {
        Print print = new Print();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                print.printA();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                print.printB();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                print.printC();
            }
        }, "C").start();
    }
}

class Print {
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    //1A 2B 3C
    private int number = 1;

    public void printA() {
        lock.lock();
        try {
            //业务：判断 ---> 执行 ---> 通知
            while (number != 1) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "--->执行了");
            //A执行完后通知B
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "--->执行了");
            //B执行完后通知C
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "--->执行了");
            //C执行完后通知A
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}