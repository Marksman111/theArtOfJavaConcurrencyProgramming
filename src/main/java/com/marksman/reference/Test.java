package com.marksman.reference;

import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * @author weilb
 * @date 2018/7/22
 * @description
 */
public class Test {

    public static void main(String[] args){
        HashMap<String,Object> map = new HashMap<>();
    }

    public static void test1(){
        Thread thread = new Thread();
        thread.start();
    }
}
