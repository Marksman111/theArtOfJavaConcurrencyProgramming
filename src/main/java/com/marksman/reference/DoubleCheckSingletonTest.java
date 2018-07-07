package com.marksman.reference;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试并发访问双重检验锁实现的单例模式(我的第一篇技术博客中需测试的问题)
 * 	不理解CountDownLatch类，暂未解决
 *
 */

public class DoubleCheckSingletonTest {
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch cdOrder = new CountDownLatch(1);
		final CountDownLatch cdAnswer = new CountDownLatch(3);
		
		for(int i=0;i<3;i++){
			Runnable runnable = new Runnable(){

				public void run() {
					try {
						System.out.println("线程"+Thread.currentThread().getName()+"正准备接受命令");
						cdOrder.await();
						System.out.println("线程"+Thread.currentThread().getName()+"已接受命令");
						Thread.sleep((long)(Math.random()*10000));
						System.out.println("线程"+Thread.currentThread().getName()+"回应命令处理结果");
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						cdAnswer.countDown();
					}
				}
			};
			service.execute(runnable);
		}
		
		try {
			Thread.sleep((long)Math.random()*10000);
			System.out.println("线程"+Thread.currentThread().getName()+"即将发布指令");
			cdOrder.countDown();
			System.out.println("线程"+Thread.currentThread().getName()+"已发送命令，正在等待结果");
			cdAnswer.await();
			System.out.println("线程"+Thread.currentThread().getName()+"已收到所有响应结果");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		service.shutdown();
	}
	
	
	static class Singleton implements Runnable{
		
		private static Singleton singleton;
		public void run() {
			if(singleton==null){
				synchronized(Singleton.class){
					
				}
			}
		}
		
	}
}
