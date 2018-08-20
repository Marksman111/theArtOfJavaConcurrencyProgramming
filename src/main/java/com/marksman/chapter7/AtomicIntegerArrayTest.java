package com.marksman.chapter7;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author weilb
 * @date 2018/8/20
 * @description
 */
public class AtomicIntegerArrayTest {

    static int[] value = new int[]{1,2};

    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args){
        ai.getAndSet(0,3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);
    }
}