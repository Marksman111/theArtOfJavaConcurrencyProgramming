package com.marksman.chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * p18
 * @author Marksman
 *
 */
public class Counter {
	
	private AtomicInteger atomicI = new AtomicInteger();
	private int i = 0;
	
	public static void main(String[] args) {
		final Counter cas = new Counter();
		List<Thread> ts = new ArrayList<Thread>(600);
		long start = System.currentTimeMillis();
		for(int j=0;j<100;j++){
			Thread t = new Thread(new Runnable(){
				//计数
				public void run() {
					for(int i=0;i<10000;i++){
						cas.count();
						cas.safeCount();
					}
				}
				
			});
			ts.add(t);
		}
		for(Thread t : ts){
			t.start();
		}
		//等待所有线程执行完成
		for(Thread t : ts){
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("非线程安全计数:"+cas.i);
		System.out.println("使用CAS实现线程安全计数:"+cas.atomicI.get());
		System.out.println("耗时:" + String.valueOf(System.currentTimeMillis() - start));
	}
	
	/**
	 * 使用CAS实现线程安全计数器
	 */
	private void safeCount(){
		for(;;){
			int i = atomicI.get();
			boolean suc = atomicI.compareAndSet(i, ++i);
			if(suc){
				break;
			}
		}
	}
	
	/**
	 * 非线程安全计数器
	 */
	private void count(){
		i++;
	}
}
