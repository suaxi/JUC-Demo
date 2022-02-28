package com.sw.juc.Lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author Suaxi
 * @date 2022/2/28 15:24
 * 6.调用同一个phone对象，一个锁class类模板，一个锁调用者，先执行打电话，后执行发短信
 * 7.phone1、phone2两个对象，一个锁class类模板，一个锁调用者，先执行打电话，后执行发短信
 */
public class Demo04 {
    public static void main(String[] args) {
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();

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

class Phone4 {

    //synchronized锁的对象是方法的调用者
    //static修饰，锁的是一个class类模板
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    //普通同步方法，锁的是调用者
    public synchronized void call() {
        System.out.println("打电话");
    }

}
