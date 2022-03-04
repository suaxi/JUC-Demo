package com.sw.juc.forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

/**
 * @author Suaxi
 * @date 2022/3/4 21:59
 */
public class ForkJoinTest {
    public static void main(String[] args) {
        test1(); //293ms
        test2(); //302ms
        test3(); //130ms
    }

    public static void test1() {
        //普通累加
        long sum = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10_0000_0000; i++) {
            sum += i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("sum=" + sum + " 耗时：" + (endTime - startTime));
    }

    public static void test2() {
        //forkJoin
        long startTime = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo task = new ForkJoinDemo(0, 10_0000_0000);
        forkJoinPool.execute(task);
        long sum = 0;
        try {
            sum = task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("sum=" + sum + " 耗时：" + (endTime - startTime));
    }

    public static void test3() {
        //并行流
        long startTime = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0, 10_0000_0000).parallel().reduce(0, Long::sum);
        long endTime = System.currentTimeMillis();
        System.out.println("sum=" + sum + " 耗时：" + (endTime - startTime));
    }
}
