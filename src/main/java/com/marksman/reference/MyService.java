package com.marksman.reference;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author weilb
 * @date 2018/7/21
 * @description
 */
public class MyService {

    public synchronized static void serviceMethod1(){
        try {
            System.out.println(Thread.currentThread().getName()+"进入了业务方法");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void serviceMethod2(){
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName()+"进入了业务方法");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reentrantLock.unlock();
    }
}