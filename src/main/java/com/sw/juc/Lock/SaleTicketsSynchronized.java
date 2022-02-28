package com.sw.juc.Lock;

/**
 * @author Suaxi
 * @date 2022/2/28 11:16
 * 1.传统synchronized锁
 */
public class SaleTicketsSynchronized {
    public static void main(String[] args) {
        TicketSynchronized ticket = new TicketSynchronized();

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

class TicketSynchronized {

    private int number = 50;

    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "张票，剩余：" + number);
        }
    }

}