package com.marksman.reference;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

/**
 * @author weilb
 * @date 2018/8/6
 * @description
 */
public class TwoThreadWaitNotify {

    private int start = 1;
    private boolean flag = false;

    public static void main(String[] args){
        TwoThreadWaitNotify twoThreadWaitNotify = new TwoThreadWaitNotify();

        Thread t1 = new Thread(new OuNum(twoThreadWaitNotify));
        t1.setName("A");

        Thread t2 = new Thread(new JiNum(twoThreadWaitNotify));
        t2.setName("B");

        t1.start();
        t2.start();
    }

    public static class OuNum implements Runnable{

        private TwoThreadWaitNotify number;

        public OuNum(TwoThreadWaitNotify number){
            this.number = number;
        }

        @Override
        public void run() {
            while (number.start <= 100){
                synchronized (TwoThreadWaitNotify.class){
                    System.out.print("偶数线程抢到锁了 ");
                    if (number.flag){
                        System.out.println(Thread.currentThread().getName()+"+-+偶数"+number.start);
                        number.start++;

                        number.flag = false;
                        TwoThreadWaitNotify.class.notify();
                    }else {
                        try{
                            TwoThreadWaitNotify.class.wait();
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
    }

    public static class JiNum implements Runnable{

        private TwoThreadWaitNotify number;

        public JiNum(TwoThreadWaitNotify number){
            this.number = number;
        }

        @Override
        public void run() {
            synchronized (TwoThreadWaitNotify.class) {
                while (number.start <= 100) {
                    System.out.print("奇数线程抢到锁了 ");
                    if (!number.flag) {
                        System.out.println(Thread.currentThread().getName() + "+-+奇数" + number.start);
                        number.start++;

                        number.flag = true;

                        TwoThreadWaitNotify.class.notify();
                    } else {
                        try {
                            TwoThreadWaitNotify.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}