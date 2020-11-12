package com.geekbang.week4.homework03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description:
 *
 * @author zhangtianle
 * @since 2020-11-12
 */
public class LockConditionDemo {

    private volatile Integer value = null;
    private final Lock lock = new ReentrantLock();
    private final Condition calComplete = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final LockConditionDemo method = new LockConditionDemo();
        Thread thread = new Thread(() -> {
            method.sum(30);
        });
        thread.start();
        int result = method.getValue(); //这是得到的返回值
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    public void sum(int num) {
        lock.lock();
        try {
            value = fibo(num);
            calComplete.signal();
        } finally {
            lock.unlock();
        }
    }

    private int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }

    public int getValue() throws InterruptedException {
        lock.lock();
        try {
            while (value == null) {
                calComplete.await();
            }
        } finally {
            lock.unlock();
        }
        return value;
    }
}
