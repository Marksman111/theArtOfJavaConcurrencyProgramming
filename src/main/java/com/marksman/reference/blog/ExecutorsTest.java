package com.marksman.reference.blog;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author weilb
 * @date 2018/7/10
 * @description  线程池的分类
 */
public class ExecutorsTest {

    public static void main(String[] args){
        //cachedThreadPoolTest();
        //fixedThreadPoolTest();
        scheduledThreadPoolTest();
        //singleThreadExecutorTest();
    }

    /**
     * @author  weilb
     * @date  2018/7/10
     * @params  []
     * @returns void
     * @description  cachedThreadPool:可缓存的线程池，核心池大小为0，最大线程池为Integer.MAX_VALUE，
     *               线程的存活时间为60秒，任务队列为同步队列：SynchronousQueue。
     *               优点：有任务来时就创建一个线程，没有任务时，60秒后就销毁线程，这样节约系统资源
     *               缺点：若偶尔(超过60秒)来一个新任务，那就需要每次都创建线程，速度就会慢下来且销耗系统资源
     *               适用场景：系统资源不够充足、大量任务并且处理时间短
     */
    public static void cachedThreadPoolTest(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0;i < 10; i++){
            final int index = i;
            try {
                Thread.sleep(index*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()+":"+index));
        }
    }

    /**
     * @author  weilb
     * @date  2018/7/10
     * @params  []
     * @returns void
     * @description  核心池和最大线程池大小都是传入的参数的值，存活时间无限制，任务队列是无限制阻塞队列：LinkedBlockingQueue.
     *               优点：可控制线程最大并发数，超出的线程会在队列中等待
     *               缺点：线程池大小固定，随着业务量的变化，改起来不方便
     *               适用场景：一定数量的任务，执行所需时间长
     */
    public static void fixedThreadTest(){
        ExecutorService fixdThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0;i < 10; i++){
            final int index = i;
            fixdThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName()+":"+index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * @author  weilb
     * @date  2018/7/11
     * @params  []
     * @returns void
     * @description  核心池为传入参数大小，线程池大小为Integer.MAX_VALUE，存活时间无限制，任务队列为延迟队列:
     *                  DelayedWorkQueue，这是一种按照超时时间排序的队列结构，它实现了BlockingQueue.
     *               优点：可执行定时任务、周期性任务
     *               缺点：暂时没想到
     *               适用场景：有定时、周期性多任务需求时
     */
    public static void scheduledThreadPoolTest(){
        ScheduledExecutorService scheduledExecutorPool = Executors.newScheduledThreadPool(3);
        scheduledExecutorPool.scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName()+
                ":delay 1 seconds,and execute every 3 seconds"),1,3,TimeUnit.SECONDS);
    }

    /**
     * @author  weilb
     * @date  2018/7/11
     * @params  []
     * @returns void
     * @description  核心池和线程池的大小都是1，存活时间无限制，任务队列为：LinkedBlockingQueue。
     *               优点：线程池中一直存在一个线程，任务按顺序执行，后来的任务在队列里排队等待
     *               缺点：
     *               适用场景：线程安全无并发执行任务
     */
    public static void singleThreadExecutorTest(){
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0;i < 10; i++){
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + ":"+index);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}