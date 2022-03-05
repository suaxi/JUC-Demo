package com.sw.juc.single;

/**
 * @author Suaxi
 * @date 2022/3/5 14:33
 * 饿汉式
 */
public class HungryMan {

    private byte[] data01 = new byte[1024*1024];
    private byte[] data02 = new byte[1024*1024];
    private byte[] data03 = new byte[1024*1024];
    private byte[] data04 = new byte[1024*1024];

    public HungryMan() {

    }

    private final static HungryMan hungryMan = new HungryMan();

    public static HungryMan getInstance() {
        return hungryMan;
    }
}
