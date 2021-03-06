package com.marksman.chapter4;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WaitNotify {
	static boolean flag = true;
	static Object lock = new Object();
	
	public static void main(String[] args) throws InterruptedException {
		Thread waitThread = new Thread(new Wait(),"WaitThread");
		waitThread.start();
		TimeUnit.SECONDS.sleep(1);
		Thread notifyThread = new Thread(new Notify(),"NotifyThread");
		notifyThread.start();
	}
	
	static class Wait implements Runnable{

		public void run() {
			synchronized(lock){
				while(flag){
					try {
						System.out.println(Thread.currentThread() + 
								" flag is true.wait@ "+new SimpleDateFormat("HH:mm:ss").format(new Date()));
						lock.wait();
					} catch (InterruptedException e) {
						//e.printStackTrace();
					}
				}
				
				System.out.println(Thread.currentThread()+
						" flag is flase. running@ " +new SimpleDateFormat("HH:mm:ss").format(new Date()));
			}
		}
		
	}
	
	static class Notify implements Runnable{

		public void run() {
			synchronized (lock){
				System.out.println(Thread.currentThread()+
						" hold lock again. sleep@" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
				lock.notifyAll();
				flag = false;
				SleepUtils.second(5);
			}
			
			synchronized(lock){
				System.out.println(Thread.currentThread() + 
						" hold lock again. sleep@ "+new SimpleDateFormat("HH:mm:ss").format(new Date()));
				SleepUtils.second(5);
			}
			
		}
		
	}
}
