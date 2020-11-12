package com.geekbang.week4.homework03;

/**
 * description:
 *
 * @author zhangtianle
 * @since 2020-11-12
 */
public class SynchronizedMethodDemo {

    private volatile Integer value = null;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final SynchronizedMethodDemo method = new SynchronizedMethodDemo();
        Thread thread = new Thread(() -> {
            method.sum(30);
        });
        thread.start();
        int result = method.getValue();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    synchronized public void sum(int num) {
        value = fibo(num);
        notifyAll();
    }

    private int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }

    synchronized public int getValue() throws InterruptedException {
        while (value == null) {
            wait();
        }
        return value;
    }
}
