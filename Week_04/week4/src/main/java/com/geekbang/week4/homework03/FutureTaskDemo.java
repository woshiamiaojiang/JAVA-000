package com.geekbang.week4.homework03;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * description:
 *
 * @author zhangtianle
 * @since 2020-11-12
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        FutureTask<Integer> task = new FutureTask(() -> new FutureTaskDemo().fibo(30));
        Thread thread = new Thread(task);
        thread.start();
        int result = task.get();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }


    private int sum() {
        return fibo(30);
    }

    private int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
