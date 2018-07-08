package com.marksman.chapter4.threadPool;

import javafx.concurrent.Worker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author weilb
 * @date 2018/7/7
 * @description  测试线程池
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job>{

    //最大线程数量
    private static final int MAX_WORKER_NUMBER = 10;
    //默认线程数量
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    //最小线程数量
    private static final int MIN_WORKER_NUMBERS = 1;
    //任务列表
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    //工作者列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    //默认工作者线程数量
    private int workerNum = DEFAULT_WORKER_NUMBERS;
    //线程编号生成
    private AtomicLong threadNum = new AtomicLong();

    private DefaultThreadPool(){
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num){
        workerNum = num > MAX_WORKER_NUMBER ? MAX_WORKER_NUMBER : num < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : num;
        initializeWorkers(num);
    }

    public void execute(Job job) {
        if (job != null){
            synchronized (jobs){
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    public void shutdown() {
        for (Worker worker: workers){
            worker.shutdown();
        }
    }

    public void addWorkers(int num) {
        synchronized (jobs){
            if (num + this.workerNum>MAX_WORKER_NUMBER){
                num = MAX_WORKER_NUMBER - this.workerNum;
            }
        }
        initializeWorkers(num);
        this.workerNum += num;
    }

    public void removeWorker(int num) throws IllegalAccessException {
        synchronized (jobs){
            if (num >= this.workerNum){
                throw new IllegalAccessException("beyond workNum");
            }
        }
        int count = 0;
        while (count < num){
            Worker worker = workers.get(count);
            if (workers.remove(worker)){
                worker.shutdown();
                count++;
            }
        }
        this.workerNum -= count;
    }

    public int getJobSize() {
        return jobs.size();
    }

    private void initializeWorkers(int num){
        for (int i=0;i<num;i++){
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker,"ThreadPool-Worker-"+threadNum.incrementAndGet());
            thread.start();
        }
    }

    //工作者，负责消费任务
    class Worker implements Runnable{

        private volatile boolean running = true;
        public void run() {
            while (true){
                Job job = null;
                synchronized (jobs){
                    while (jobs.isEmpty()){
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }

                if (job != null){
                    try{
                        job.run();
                    }catch (Exception e){
                    }
                }
            }
        }

        public void shutdown(){
            running = false;
        }
    }
}