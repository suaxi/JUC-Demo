package com.sw.juc.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Suaxi
 * @date 2022/2/28 11:16
 * 2.Lock锁
 */
public class SaleTicketsLock {
    public static void main(String[] args) {
        TicketLock ticket = new TicketLock();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}

class TicketLock {

    private int number = 50;

    /**
     * 公平锁：先来后到
     * 非公平锁：可以插队（默认采用这种方式）
     */
    Lock lock = new ReentrantLock();

    public void sale() {
        //1.加锁
        lock.lock();

        try {
            //2.业务代码
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "张票，剩余：" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //3.解锁
            lock.unlock();
        }
    }

}