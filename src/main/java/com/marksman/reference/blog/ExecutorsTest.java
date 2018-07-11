package com.marksman.reference.blog;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author weilb
 * @date 2018/7/10
 * @description  线程池的分类
 */
public class ExecutorsTest {

    public static void main(String[] args){
        //ExecutorsTest.cachedThreadPoolTest();
        ExecutorsTest.fixdThreadPoolTest();
    }

    /**
     * @author  weilb
     * @date  2018/7/10
     * @params  []
     * @returns void
     * @description  cachedThreadPool:可缓存的线程池，核心池大小为0，最大线程池为Integer.MAX_VALUE，
     *               线程的存活时间为60秒，任务队列为 SynchronousQueue。
     *
     *               优点：有任务来时就创建一个线程，没有任务时，60秒后就销毁线程，这样节约系统资源
     *               缺点：新任务执行效率会下降
     *               适用场景：系统资源不够充足
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
     * @description  核心池和最大线程池大小都是传入的参数的值，存活时间为0，任务队列是LinkedBlockingQueue.
     *               优点：可控制线程最大并发数，超出的线程会在队列中等待
     *               缺点：
     *               适用场景：
     */
    public static void fixdThreadPoolTest(){
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



}