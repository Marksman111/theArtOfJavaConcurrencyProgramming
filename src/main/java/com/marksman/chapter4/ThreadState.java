package com.marksman.chapter4;

public class ThreadState {
	
	public static void main(String[] args) {
		new Thread(new TimeWaiting(),"TimeWaitingThread").start();
		new Thread(new Waiting(),"WaitingThread").start();
		new Thread(new Blocked(),"BlockedThread-1").start();
		new Thread(new Blocked(),"BlockedThread-2").start();
	}
	
	static class TimeWaiting implements Runnable{

		public void run() {
			while(true){
				SleepUtils.second(100);
			}
		}
	}
	
	static class Waiting implements Runnable{

		public void run() {
			while(true){
				synchronized(Waiting.class) {
					try {
						Waiting.class.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	static class Blocked implements Runnable{

		public void run() {
			synchronized(Blocked.class){
				SleepUtils.second(100);
			}
		}
		
	}
	
	
}
