package com.marksman.chapter5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author weilb
 * @date 2018/7/30
 * @description
 */
public class FairAndUnfairTest {

    private static Lock fairLock = new ReentrantLock2(true);
    private static Lock unfairLock = new ReentrantLock2(false);

    public void fair(){
        testLock(fairLock);
    }

    public void unfair(){
        testLock(unfairLock);
    }

    private void testLock(Lock lock){

    }

    private static class Job extends Thread{
        private Lock lock;
        public Job(Lock lock){
            this.lock = lock;
        }

        public void run(){

        }
    }

    private static class ReentrantLock2 extends ReentrantLock{

        public ReentrantLock2(boolean fair){
            super(fair);
        }

        public Collection<Thread> getQueuedThreads(){
            List<Thread> list = new ArrayList<Thread>(super.getQueuedThreads());
            Collections.reverse(list);
            return list;
        }
    }
}