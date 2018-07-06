package com.marksman.chapter3;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
	
	int a = 0;
	ReentrantLock lock = new ReentrantLock();
	
	public void writer(){
		lock.lock();
		try {
			a++;
		} catch (Exception e) {
			lock.unlock();
		}
	}
	
	public void reader(){
		lock.lock();
		try {
			int i = a;
		} catch (Exception e) {
			lock.unlock();
		}
	}
}
