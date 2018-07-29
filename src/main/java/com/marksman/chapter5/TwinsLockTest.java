package com.marksman.chapter5;

import com.marksman.chapter4.SleepUtils;

import java.util.concurrent.locks.Lock;

/**
 * @author weilb
 * @date 2018/7/26
 * @description
 */
public class TwinsLockTest {

    public static void main(String[] args){
        final Lock lock = new TwinsLock();
        class Worker extends Thread{
            @Override
            public void run() {
                while (true){
                    lock.lock();
                    try{
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    }finally {
                        lock.unlock();
                    }
                }
            }
        }

        for (int i = 0;i < 10; i++){
            Worker worker = new Worker();
            worker.setDaemon(true);
            worker.start();
        }

        for (int i = 0;i < 10; i++){
            SleepUtils.second(1);
            System.out.println();
        }
    }
}