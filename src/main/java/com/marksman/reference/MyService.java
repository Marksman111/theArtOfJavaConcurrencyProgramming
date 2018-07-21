package com.marksman.reference;

/**
 * @author weilb
 * @date 2018/7/21
 * @description
 */
public class MyService {

    public synchronized static void serviceMethod(){
        try{
            System.out.println(Thread.currentThread().getName()+"进入了业务方法");
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class MyThread1 extends Thread{

        @Override
        public void run() {
            MyService.serviceMethod();
        }
    }

    class MyThread2 extends Thread{

        @Override
        public void run() {
            MyService.serviceMethod();
        }
    }

}