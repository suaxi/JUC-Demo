package com.sw.juc.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Wang Hao
 * @date 2022/2/28 16:10
 *
 */
public class ListTest {
    public static void main(String[] args) {
        //ConcurrentModificationException 并发修改异常
        //并发情况下ArrayList不安全
        /**
         * 解决方案：
         * 1.List<String> list = new Vector<>();
         * 2.List<String> list = Collections.synchronizedList(new ArrayList<>());
         * 3.List<String> list = new CopyOnWriteArrayList<>();
         */

        //CopyOnWriteArrayList 写入时复制
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().replace("-", ""));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
