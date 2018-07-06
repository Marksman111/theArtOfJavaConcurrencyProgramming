package com.marksman.chapter4;

import java.util.concurrent.TimeUnit;

public class Interrupted {
	
	public static void main(String[] args) throws InterruptedException {
		Thread sleepThread = new Thread(new SleepRunner(),"SleepRunner");
		sleepThread.setDaemon(true);
		
		Thread busyThread = new Thread(new BusyRunner(),"BusyRunner");
		busyThread.setDaemon(true);
		
		sleepThread.start();
		busyThread.start();
		
		TimeUnit.SECONDS.sleep(5);
		sleepThread.interrupt();
		busyThread.interrupt();
		
		System.out.println("SleepThread interrupted is "+ sleepThread.isInterrupted());
		System.out.println("BusyThread interrupted is "+busyThread.isInterrupted());
		
		SleepUtils.second(2);
	}
	
	
	static class SleepRunner implements Runnable{

		public void run() {
			SleepUtils.second(10);
		}
		
	}
	
	static class BusyRunner implements Runnable{

		public void run() {
			while(true){
				
			}
		}
		
	}
}
