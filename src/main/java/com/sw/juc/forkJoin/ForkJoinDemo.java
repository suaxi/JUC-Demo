package com.sw.juc.forkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author Suaxi
 * @date 2022/3/4 21:54
 * 求和计算：
 * 1.通过ForkJoinPool执行
 * 2.forkJoinPool.execute(ForkJoinTask<?> task)
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    private long start;
    private long end;

    //临界值
    private long temp = 10000;

    public ForkJoinDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute() {
        if ((start - end) < temp) {
            long sum = 0;
            for (int i = 0; i < 10_0000_0000; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            //拆分任务
            task1.fork();
            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);
            task2.fork();
            return task1.join() + task2.join();
        }
    }
}
