package com.sw.juc.Lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author Suaxi
 * @date 2022/2/28 15:24
 * 5.两个静态同步方法，锁的是class对象
 * 6.phone1、phone2两个对象，两个静态同步方法，它们锁的class类模板只有一个，所有同理第5个问题
 */
public class Demo03 {
    public static void main(String[] args) {
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();

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

class Phone3 {

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

    public static synchronized void call() {
        System.out.println("打电话");
    }

}
