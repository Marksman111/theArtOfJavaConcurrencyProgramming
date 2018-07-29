package com.marksman.reference;

import java.util.concurrent.Callable;

/**
 * @author weilb
 * @date 2018/7/22
 * @description
 */
public class CallableTest implements Callable {
    @Override
    public Object call() throws Exception {
        return "call个锤子";
    }
}
