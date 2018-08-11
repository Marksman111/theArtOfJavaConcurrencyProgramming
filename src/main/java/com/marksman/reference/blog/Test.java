package com.marksman.reference.blog;

import java.util.concurrent.*;

/**
 * @author weilb
 * @date 2018/8/11
 * @description
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Thread thread1 = new MyThread();
        thread1.start();

        Thread thread2 = new Thread(new MyRunnable());
        thread2.start();

        Callable callable = new MyCallable();
        FutureTask<String> future = new FutureTask<>(callable);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(future);
        System.out.println(future.get());
        executorService.shutdown();
    }

    static class MyRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("我实现了Runnable接口...");
        }
    }

    static class MyCallable implements Callable{

        @Override
        public String call() throws Exception {
            return "我实现了Callable接口...";
        }
    }
}