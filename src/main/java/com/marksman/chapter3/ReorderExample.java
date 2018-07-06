package com.marksman.chapter3;

public class ReorderExample {
	
	static int a = 0;
	static int i = 0;
	boolean flag = false;
	public void writer(){
		a = 1;
		flag = true;
	}
	
	public void reader(){
		if(flag){
			i = a + a;
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable(){

			public void run() {
				ReorderExample re1 = new ReorderExample();
				re1.writer();
			}
			
		});
		t1.start();
		
		Thread t2 = new Thread(new Runnable(){

			public void run() {
				ReorderExample re2 = new ReorderExample();
				re2.reader();
			}
			
		});
		t2.start();
		
		System.out.println("a:"+a);
		//System.out.println("flag:"+flag);
		System.out.println("i:"+i);
		
	}
}
