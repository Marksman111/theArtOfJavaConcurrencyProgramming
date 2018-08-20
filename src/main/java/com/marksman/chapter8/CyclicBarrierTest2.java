package com.marksman.chapter8;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author weilb
 * @date 2018/8/20
 * @description
 */
public class CyclicBarrierTest2 {
    
    static CyclicBarrier c = new CyclicBarrier(2,new A());
    
    public static void main(String[] args){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(1);
            }
        }).start();

        try {
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(2);
    }
    
    static class A implements Runnable{

        @Override
        public void run() {
            System.out.println(3);
        }
    }
}