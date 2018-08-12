package com.marksman.reference.blog;

/**
 * @author weilb
 * @date 2018/8/11
 * @description
 */
public class MyThread extends Thread{

    @Override
    public void run() {
        super.run();
        System.out.println("我继承了Thread类...");
    }
}