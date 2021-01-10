package com.example.redis.demo;

import com.example.redis.demo.lock.RedisLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 张天乐
 */
@SpringBootApplication
public class DemoApplication {

    private final static String LOCK = "redis_lock";

    private final static int EXPIRE = 2;

    private static int amount = 12;

    public static void lockTest() {
        System.out.println("lock test:: start sleep 10");
        if (!RedisLock.getInstance().lock(LOCK, EXPIRE)) {
            System.out.println("fail to get lock");
            return;
        }
        try {
            Thread.sleep(1000);
            amount -= 1;
            System.out.printf("db minus amount: %d\n", amount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RedisLock.getInstance().unlock(LOCK);
        System.out.println("lock test end");
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(DemoApplication.class, args);
        Thread thread1 = new Thread(DemoApplication::lockTest);
        Thread thread2 = new Thread(DemoApplication::lockTest);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        Thread thread3 = new Thread(DemoApplication::lockTest);
        thread3.start();
        thread3.join();
    }
}
