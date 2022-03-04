package com.sw.juc.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Suaxi
 * @date 2022/3/4 15:17
 * 阻塞队列,四组Api：
 * 1. 抛出异常              add     remove
 * 2. 有返回值，不抛出异常    offer   poll
 * 3. 等待，阻塞（一直阻塞）   put     take
 * 4. 等待，阻塞（一直阻塞）   offer   poll（方法重载）
 */
public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        demo04();
    }

    /**
     * 抛出异常
     */
    public static void demo01() {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);
        System.out.println(queue.add(1));
        System.out.println(queue.add(2));
        System.out.println(queue.add(3));
        //java.lang.IllegalStateException: Queue full
        //System.out.println(queue.add(3));

        System.out.println("==================");
        //判断队首元素
        System.out.println(queue.element());

        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        //java.util.NoSuchElementException
        System.out.println(queue.remove());
    }

    /**
     * 有返回值，不抛出异常
     */
    public static void demo02() {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);
        System.out.println(queue.offer(1));
        System.out.println(queue.offer(2));
        System.out.println(queue.offer(3));
        //false
        //System.out.println(queue.offer(3));

        System.out.println("==================");
        //判断队首元素
        System.out.println(queue.peek());

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        //null
        System.out.println(queue.poll());
    }

    /**
     * 等待，阻塞（一直阻塞）
     */
    public static void demo03() throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);
        queue.put(1);
        queue.put(2);
        queue.put(3);
        //队列没有位置，一直阻塞
        //queue.put(4);

        System.out.println("==================");

        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        //同理，一直阻塞
        //System.out.println(queue.take());
    }

    /**
     * 等待，阻塞（等待超时）
     */
    public static void demo04() throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);
        System.out.println(queue.offer(1));
        System.out.println(queue.offer(2));
        System.out.println(queue.offer(3));
        //false 等待两秒，超时退出
        //System.out.println(queue.offer(4, 2, TimeUnit.SECONDS));

        System.out.println("==================");

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        //null 等待两秒，超时退出
        System.out.println(queue.poll(2, TimeUnit.SECONDS));
    }
}
