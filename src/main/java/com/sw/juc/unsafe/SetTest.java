package com.sw.juc.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Wang Hao
 * @date 2022/2/28 16:27
 */
public class SetTest {
    public static void main(String[] args) {
        /**
         * ConcurrentModificationException 并发修改异常
         * 解决方法同理List：
         * 1.Set<String> set = Collections.synchronizedSet(new HashSet<>());
         * 2.Set<String> set = new CopyOnWriteArraySet<>();
         */

        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString());
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
