package com.sw.juc.Lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author Suaxi
 * @date 2022/2/28 15:24
 * 3.普通方法不受锁的影响
 * 4.phone1、phone2两个对象，是不同的两把锁（非公平）
 */
public class Demo02 {
    public static void main(String[] args) {
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();

        new Thread(() -> {
            phone1.sendSms();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone2.call();
        }, "B").start();

    }
}

class Phone2 {

    //synchronized锁的对象是方法的调用者
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }

    //普通方法没有锁，不受锁的影响
    public void hello() {
        System.out.println("打招呼");
    }
}
