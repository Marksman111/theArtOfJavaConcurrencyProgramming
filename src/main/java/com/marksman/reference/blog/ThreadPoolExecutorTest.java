package com.marksman.reference.blog;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author weilb
 * @date 2018/7/8
 * @description
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args){
        int corePoolSize = 10;
        int maximumPoolSize = 20;
        long keepAliveTime = 1000;
        TimeUnit timeUnit = null;
        BlockingQueue<Runnable> workQueue = null;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,timeUnit,workQueue);
    }
}