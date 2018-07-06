package com.marksman.chapter3;

public class SynchronizedExample {
	
	int a = 0;
	boolean flag = false;
	
	public synchronized void writer(){
		a = 1;
		flag = true;
	}
	
	public synchronized void reader(){
		if(flag){
			int i = a;
		}
	}
}
