package com.marksman.chapter6;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author weilb
 * @date 2018/8/14
 * @description
 */
public class HashMapDemo {

    public static void main(String[] args) throws InterruptedException {
        final HashMap<String,String> map = new HashMap<>(2);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(),"");
                        }
                    },"ftf" + i).start();
                }
            }
        },"ftf");
        t.start();
        t.join();

    }

}