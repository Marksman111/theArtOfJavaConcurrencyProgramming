package com.marksman.reference;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchTest implements Runnable{
	final AtomicInteger number = new AtomicInteger();
	volatile boolean bol = false;
	
	public void run() {
		System.out.println(number.getAndIncrement());
		synchronized(this){
			try {
				if(!bol){
					System.out.println(bol);
					bol = true;
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("并发数量为:"+number.intValue());
	}
	
	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		CountDownLatchTest test = new CountDownLatchTest();
		for(int i=0;i<10;i++){
			pool.execute(test);
		}
	}
}
