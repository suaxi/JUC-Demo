package com.sw.juc.volatileDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author Suaxi
 * @date 2022/3/5 13:53
 * volatile：保证可见性
 */
public class JMMTest01 {

    //volatile保证可见性
    private volatile static int num = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (num == 0) {
                //线程A对main线程修改num=1后的变化（主内存的变化）不知道，一直处于运行状态
            }
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;

        System.out.println(num);
    }
}
