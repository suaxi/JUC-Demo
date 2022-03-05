package com.sw.juc.CAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Suaxi
 * @date 2022/3/5 15:04
 * compareAndSet （CAS）
 */
public class CASDemo01 {
    public static void main(String[] args) {

        /**
         * compareAndSet：比较当前工作内存中的值和主内存中的值，如果这个是是期望值，则执行操作，反之一直循环
         * 缺点：
         * 1.循环耗时
         * 2.一次只能保证一个共享变量的原子性（当前来说在日常使用中已经足够了）
         * 3.ABA问题
         */

        AtomicInteger atomicInteger = new AtomicInteger(2022);
        System.out.println(atomicInteger.compareAndSet(2022, 2023));
        System.out.println(atomicInteger.get());

        //捣乱的线程
        System.out.println(atomicInteger.compareAndSet(2023, 2022));
        System.out.println(atomicInteger.get());

        //期望的线程（由于捣乱线程的原因，出现ABA问题，期望线程拿到的虽然是2022，但在中间过程该值已被修改过）
        System.out.println(atomicInteger.compareAndSet(2022, 2023));
        System.out.println(atomicInteger.get());
    }
}
