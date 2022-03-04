package com.sw.juc.threadPool;


import java.util.concurrent.*;

/**
 * @author Suaxi
 * @date 2022/3/4 16:55
 * Executors工具类
 */
public class Demo01 {
    public static void main(String[] args) {

//        三大方法：
//        //单个线程
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        //固定大小的线程池
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//        //可伸缩的
//        ExecutorService executor = Executors.newCachedThreadPool();

        /**
         * 四种拒绝策略：
         * AbortPolicy 银行坐席和等待区都满了，还有人进来，不处理后续进来的人的请求，并抛出异常
         * CallerRunsPolicy 从哪来回哪去（该例子中回到main主线程）
         * DiscardPolicy 队列满了，丢掉任务，且不会抛出异常
         * DiscardOldestPolicy 队列满了，将队列最前的任务丢弃，重新尝试执行当前任务，且不会抛出异常
         */

        /**
         * 最大线程如何定义
         * 1.CPU密集型： 根据cpu核心数填写
         * 2.IO密集型： 判断程序中十分耗IO的线程数，大于等于该数量
         */

        //自定义线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                8,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        try {
            for (int i = 0; i < 100; i++) {
                executor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 执行");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
