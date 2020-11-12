package com.geekbang.week4.homework03;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * description:
 *
 * @author zhangtianle
 * @since 2020-11-12
 */
public class CyclicBarrierDemo {

    CyclicBarrier barrier;

    private volatile Integer value = null;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        final CyclicBarrierDemo method = new CyclicBarrierDemo();
        CyclicBarrier barrier = new CyclicBarrier(1, () -> {
            int result = 0; //这是得到的返回值
            try {
                result = method.getValue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步计算结果为：" + result);
            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        });
        method.setBarrier(barrier);
        Thread thread = new Thread(() -> {
            try {
                method.sum(30);
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void setBarrier(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public void sum(int num) throws BrokenBarrierException, InterruptedException {
        value = fibo(num);
        barrier.await();
    }

    private int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }

    public int getValue() throws InterruptedException {
        return value;
    }
}
