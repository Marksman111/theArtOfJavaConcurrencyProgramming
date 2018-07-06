package com.marksman.chapter3;

public class SafeDoubleCheckedLocking {
	
	private volatile static Instance instance;
	
	public static Instance getInstance(){
		if(instance == null){
			synchronized(SafeDoubleCheckedLocking.class){
				if(instance == null){
					instance = new Instance();
				}
			}
		}
		return instance;
	}
}
