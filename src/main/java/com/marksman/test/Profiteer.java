package com.marksman.test;

import java.util.Random;

/**
 * @author weilb
 * @date 2018/8/10
 * @description
 */
public class Profiteer extends Merhant {

    @Override
    public double discountPrice(double srcPrice, Custom custom) {
        if (custom.isVIP()){
            return srcPrice * priceDiscrimination();
        }else {
            return super.discountPrice(srcPrice, custom);
        }
    }

    public static double priceDiscrimination(){
        return new Random().nextDouble() + 0.8d;
    }
}
