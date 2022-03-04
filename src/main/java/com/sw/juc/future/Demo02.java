package com.sw.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Suaxi
 * @date 2022/3/4 22:21
 * CompletableFuture异步回调
 */
public class Demo02 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //supplyAsync （有返回值）
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "supplyAsync ---> Integer");
            int i = 1 / 0;
            return 200;
        });

        System.out.println(completableFuture.whenComplete((t, u) -> {
            //正常的返回结果
            System.out.println("t: " + t);
            //错误信息 java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
            System.out.println("u: " + u);
        }).exceptionally((e) -> {
            //异常信息 java.lang.ArithmeticException: / by zero
            System.out.println(e.getMessage());
            return 500;
        }).get());
    }
}
