package com.marksman.jvm;

import java.math.BigDecimal;

/**
 * @author weilb
 * @date 2018/8/19
 * @description
 */
public class SafepointTest {

    static double sum = 0;

    public static void foo(){
        for (int i=0;i<0x77777777;i++){
            sum += Math.sqrt(i);
        }
    }

    public static void bar(){
        for (int i=0;i<50_000_000; i++){
            new Object().hashCode();
        }
    }

    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        //new Thread(SafepointTest::foo).start();
        new Thread(SafepointTest::bar).start();
        long endTime = System.currentTimeMillis();
        System.out.println(startTime-endTime);
    }

}
