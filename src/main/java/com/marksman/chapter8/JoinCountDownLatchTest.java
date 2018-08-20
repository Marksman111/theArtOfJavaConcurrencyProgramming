package com.marksman.chapter8;

/**
 * @author weilb
 * @date 2018/8/20
 * @description
 */
public class JoinCountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {

        Thread parser1 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        Thread parser2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser2 finish");
            }
        });

        parser1.start();
        parser2.start();

        parser1.join();
        parser2.join();

        System.out.println("all parser finish");
    }
}