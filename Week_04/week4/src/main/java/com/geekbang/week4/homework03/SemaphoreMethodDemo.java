package com.geekbang.week4.homework03;

import java.util.concurrent.Semaphore;

/**
 * description:
 *
 * @author zhangtianle
 * @since 2020-11-12
 */
public class SemaphoreMethodDemo {

    final Semaphore semaphore = new Semaphore(1);

    private volatile Integer value = null;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final SemaphoreMethodDemo method = new SemaphoreMethodDemo();
        Thread thread = new Thread(() -> {
            try {
                method.sum(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        int result = method.getValue(); //这是得到的返回值
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    public void sum(int num) throws InterruptedException {
        semaphore.acquire();
        value = fibo(num);
        semaphore.release();
    }

    private int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }

    public int getValue() throws InterruptedException {
        int result;
        semaphore.acquire();
        result = this.value;
        semaphore.release();
        return result;
    }
}
