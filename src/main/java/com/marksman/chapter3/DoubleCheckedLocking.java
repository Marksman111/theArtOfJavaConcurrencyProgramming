package com.marksman.chapter3;

public class DoubleCheckedLocking {
	
	private static Instance instance;
	
	public static Instance getInstance(){
		// 双重检查锁定
		if(instance == null){ // 第一次检查
			synchronized(DoubleCheckedLocking.class){ // 加锁
				if(instance == null){ // 第二次检查
					instance = new Instance();
				}
			}
		}
		return instance;
	}
	
}
