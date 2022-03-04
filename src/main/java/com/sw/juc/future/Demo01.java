package com.sw.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Suaxi
 * @date 2022/3/4 22:21
 * CompletableFuture异步回调（对将来某个事件的结果进行建模）
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //runAsync （无返回值）
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "runAsync ---> Void");
        });

        System.out.println("测试谁先输出");
        //get() 获取阻塞执行结果
        completableFuture.get();
    }
}
