package com.marksman.chapter3;

public class UnsafeLazyInitialization {
	
	private static Instance instance;
	
	public static Instance getInstance(){
		if(instance == null){
			instance = new Instance();
		}
		return instance;
	}
}	

