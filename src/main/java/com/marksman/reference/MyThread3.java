package com.marksman.reference;

/**
 * @author weilb
 * @date 2018/7/21
 * @description
 */
public class MyThread3 extends Thread{
    @Override
    public void run() {
        MyService.serviceMethod2();
}
}