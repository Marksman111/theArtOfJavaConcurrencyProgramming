package com.marksman.reference;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author weilb
 * @date 2018/7/21
 * @description
 */
public class MyService {

    public synchronized static void serviceMethod1(){
        System.out.println(Thread.currentThread().getName()+"进入了业务方法");
    }

    public static void serviceMethod2(){
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName()+"进入了业务方法");
        reentrantLock.unlock();
    }
}