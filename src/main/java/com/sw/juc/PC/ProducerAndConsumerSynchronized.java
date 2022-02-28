package com.sw.juc.PC;

/**
 * @author Suaxi
 * @date 2022/2/28 11:58
 * 线程之间通信：生产者和消费者问题，等待唤醒，通知唤醒，虚假唤醒
 */
public class ProducerAndConsumerSynchronized {
    public static void main(String[] args) {
        DataSynchronized data = new DataSynchronized();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

class DataSynchronized {
    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        //if只会判断一次，存在虚假唤醒的问题，官方文档中说明等待应该放在while循环中
        while (number != 0) {
            //等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "--->" + number);
        //通知其他线程我 +1 执行完毕了
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (number == 0) {
            //等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "--->" + number);
        //通知其他线程我 -1 执行完毕了
        this.notifyAll();
    }
}