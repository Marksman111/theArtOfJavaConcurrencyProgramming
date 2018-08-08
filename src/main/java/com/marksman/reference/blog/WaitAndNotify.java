package com.marksman.reference.blog;

/**
 * @author weilb
 * @date 2018/8/8
 * @description
 */
public class WaitAndNotify {

    public static void main(String[] args){
        Thread t1 = new Thread(new ThreadA());
        Thread t2 = new Thread(new ThreadB());

        t1.start();
        t2.start();
    }

    static class ThreadA implements Runnable{

        @Override
        public void run() {
            System.out.println("A线程开始干活...");
            try {
                new Object().wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A线程发出通知...");
            new Object().notify();
        }
    }

    static  class ThreadB implements Runnable{

        @Override
        public void run() {
            System.out.println("B线程等待...");
            System.out.println("B线程收到通知");
        }
    }
}
