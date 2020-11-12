package com.geekbang.week4.homework03;

import java.util.concurrent.CompletableFuture;

/**
 * description:
 *
 * @author zhangtianle
 * @since 2020-11-12
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int result = CompletableFuture.supplyAsync(CompletableFutureDemo::sum).join();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static int sum() {
        return fibo(30);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }
}
