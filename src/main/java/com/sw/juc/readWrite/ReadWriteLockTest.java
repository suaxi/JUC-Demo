package com.sw.juc.readWrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Suaxi
 * @date 2022/3/4 11:14
 * 读写锁
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyThreadLock myThread = new MyThreadLock();

        //写入
        for (int i = 0; i < 5; i++) {
            int temp = i;
            new Thread(() -> {
                myThread.put("key" + temp, "value" + temp);
            },String.valueOf(i)).start();
        }

        //读取
        for (int i = 0; i < 5; i++) {
            int temp = i;
            new Thread(() -> {
                myThread.get("key" + temp);
            },String.valueOf(i)).start();
        }
    }
}

class MyThreadLock {

    private volatile Map<String, String> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, String value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            String value = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取完成" + value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

}
