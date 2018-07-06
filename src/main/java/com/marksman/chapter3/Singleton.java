package com.marksman.chapter3;

public class Singleton {
	private static class SingletonHolder{
		public static Singleton singleton = new Singleton();
	}
	
	public static Singleton getSingleton(){
		return SingletonHolder.singleton;
	}
}
