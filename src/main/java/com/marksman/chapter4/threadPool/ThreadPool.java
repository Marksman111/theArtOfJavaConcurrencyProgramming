package com.marksman.chapter4.threadPool;

/**
 * @author weilb
 * @date 2018/7/7
 * @description  学习线程池
 */
public interface ThreadPool<Job extends Runnable> {

    void execute(Job job);

    void shutdown();

    void addWorkers(int num);

    void removeWorker(int num) throws IllegalAccessException;

    int getJobSize();
}