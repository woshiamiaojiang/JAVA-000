package com.geekbang.week4.homework03;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * description:
 *
 * @author zhangtianle
 * @since 2020-11-12
 */
public class ThreadJoinDemo {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        AtomicInteger value = new AtomicInteger();
        Thread thread = new Thread(() -> value.set(sum()));
        thread.start();
        thread.join();
        int result = value.get();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
