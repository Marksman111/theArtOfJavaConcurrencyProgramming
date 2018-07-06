package com.marksman.chapter3;

public class MonitorExample {
	
	int a = 0;
	public synchronized void writer(){
		a++;
	}
	
	public synchronized void reader(){
		int i = a;
	}
	
}
