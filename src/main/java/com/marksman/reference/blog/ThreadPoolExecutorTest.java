package com.marksman.reference.blog;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author weilb
 * @date 2018/7/8
 * @description
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args){
        /*int corePoolSize = 10;
        int maximumPoolSize = 20;
        long keepAliveTime = 1000;
        TimeUnit timeUnit = null;
        BlockingQueue<Runnable> workQueue = null;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,timeUnit,workQueue);*/

        //ScheduledExecutorService scheduleThreadPool = Executors.newScheduledThreadPool(3);
        //scheduleThreadPool.scheduleAtFixedRate(()->System.out.println(Thread.currentThread().getName() + ": delay 1 seconds,and execute every 3 seconds"),1,3, SECONDS);

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        //每次执行一个任务，同步执行
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++){
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName()+":"+index);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}