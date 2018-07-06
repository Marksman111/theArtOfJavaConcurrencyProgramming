package com.marksman.chapter3;

public class VolatileFeatureExample2 {
	
	long vl = 0L;
	
	public synchronized void set(long l){
		vl = l;
	}
	
	public void getAndIncrement(){
		long temp = get();
		temp += 1L;
		set(temp);
	}
	
	public synchronized long get(){
		return vl;
	}
}
