package com.sw.juc.Lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author Suaxi
 * @date 2022/2/28 15:24
 * 1.锁对象，synchronized锁的对象是方法的调用者，谁先拿到，谁先执行
 */
public class Demo01 {
    public static void main(String[] args) {
        Phone1 phone = new Phone1();

        new Thread(() -> {
            phone.sendSms();
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone.call();
        }).start();

    }
}

class Phone1 {

    //synchronized锁的对象是方法的调用者
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }
}
