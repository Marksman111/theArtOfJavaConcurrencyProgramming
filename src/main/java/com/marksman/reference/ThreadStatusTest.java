package com.marksman.reference;

/**
 * @author weilb
 * @date 2018/7/21
 * @description
 */
public class ThreadStatusTest {

    public static void main(String[] args){
        test1();
        //test2();
    }

    public static void test1(){
        MyThread1 t1 = new MyThread1();
        t1.setName("a");
        t1.start();

        MyThread2 t2 = new MyThread2();
        t2.setName("b");
        t2.start();

        System.out.println("t2的状态:"+t2.getState());
    }

    public static void test2(){
        MyThread3 t3 = new MyThread3();
        t3.setName("a");
        t3.start();

        MyThread4 t4 = new MyThread4();
        t4.setName("b");
        t4.start();

        System.out.println("t4的状态:"+t4.getState());
    }
}