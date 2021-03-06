package com.marksman.chapter5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author weilb
 * @date 2018/8/13
 * @description
 */
public class BoundedQueue<T> {

    private Object[] items;

    // 添加的下标，删除的下标，数组当前数量
    private int addIndex,removeIndex,count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BoundedQueue(int size){
        items = new Object[size];
    }

    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length){
                notFull.await();
            }
            items[addIndex] = t;
            if (++addIndex == items.length){
                addIndex = 0;
            }
            ++count;
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public T remove() throws InterruptedException {
        lock.lock();
        try{
            while (count == 0){
                notEmpty.await();
            }
            Object x = items[removeIndex];
            if (++removeIndex == items.length){
                removeIndex = 0;
            }
            --count;
            notFull.signal();
            return (T) x;
        }finally {
            lock.unlock();
        }
    }
}