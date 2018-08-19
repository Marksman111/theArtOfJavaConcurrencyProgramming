package com.marksman.chapter6;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author weilb
 * @date 2018/8/19
 * @description
 */
public class AtomicIntegerTest {
    
    static AtomicInteger ai = new AtomicInteger(1);
    
    public static void main(String[] args){
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.get());
    }
}