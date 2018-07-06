package com.marksman.chapter4;

public class Daemon {
	
	static class DaemonRunner implements Runnable{
		
		public static void main(String[] args) {
			Thread thread = new Thread(new DaemonRunner(),"DaemonRunner");
			thread.setDaemon(true);
			thread.start();
		}
		
		public void run() {
			try {
				SleepUtils.second(10);
			} finally {
				System.out.println("DaemonThread finally run.");
			}
		}
		
	}
}
