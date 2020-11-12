package com.geekbang.week4.homework03;

import java.util.concurrent.CountDownLatch;

/**
 * description:
 *
 * @author zhangtianle
 * @since 2020-11-12
 */
public class CountDownLatchDemo {

    private volatile Integer value = null;
    private CountDownLatch latch;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(1);
        final CountDownLatchDemo method = new CountDownLatchDemo();
        method.setLatch(latch);
        Thread thread = new Thread(() -> method.sum(30));
        thread.start();
        int result = method.getValue(); //这是得到的返回值
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    public void sum(int num) {
        value = fibo(num);
        latch.countDown();
    }

    private int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }

    public int getValue() throws InterruptedException {
        latch.await();
        return value;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

}
