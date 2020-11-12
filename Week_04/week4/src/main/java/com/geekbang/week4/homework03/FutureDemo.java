package com.geekbang.week4.homework03;

import java.util.concurrent.*;

/**
 * description:
 *
 * @author zhangtianle
 * @since 2020-11-12
 */
public class FutureDemo implements Callable<Long> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Long> future = executor.submit(new FutureDemo());
        long result = future.get();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        executor.shutdown();
    }

    private long sum() {
        return fibo(30);
    }

    private long fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }

    @Override
    public Long call() throws Exception {
        return sum();
    }


}
