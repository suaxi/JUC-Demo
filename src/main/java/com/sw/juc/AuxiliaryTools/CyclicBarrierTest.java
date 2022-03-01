package com.sw.juc.AuxiliaryTools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Suaxi
 * @date 2022/3/1 17:57
 * 加法计数器
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(6, () -> {
            System.out.println("召唤神龙");
        });

        for (int i = 1; i <= 6; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println("收集了" + temp + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
