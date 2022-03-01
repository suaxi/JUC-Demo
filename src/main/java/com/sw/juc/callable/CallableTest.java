package com.sw.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Suaxi
 * @date 2022/3/1 17:27
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * Runnable启动：
         * new Thread(runnable).start();
         *
         * Callable启动：
         * MyThread thread = new MyThread();
         * FutureTask<Integer> futureTask = new FutureTask<>(thread);
         * new Thread(futureTask).start();
         */
        MyThread thread = new MyThread();
        FutureTask<Integer> futureTask = new FutureTask<>(thread);
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();
        //启动两个线程，控制台只输出了一个sout，callable结果会被缓存，效率高

        //获取执行结果可能会产生阻塞，所以放到最后再拿（后期可使用异步通信来处理）
        System.out.println(futureTask.get());
    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() {
        System.out.println("callable");
        return 1024;
    }
}
