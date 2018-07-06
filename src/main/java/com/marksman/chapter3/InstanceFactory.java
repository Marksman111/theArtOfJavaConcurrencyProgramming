package com.marksman.chapter3;

public class InstanceFactory {
	
	private static class InstanceHolder{
		public static Instance instance = new Instance();
	}
	
	public static Instance getInstance(){
		return InstanceHolder.instance;
	}
}
