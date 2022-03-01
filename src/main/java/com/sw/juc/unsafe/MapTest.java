package com.sw.juc.unsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Suaxi
 * @date 2022/3/1 16:59
 */
public class MapTest {
    public static void main(String[] args) {
        //map不安全
        //Map<String, String> map = new HashMap<>();
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString());
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
