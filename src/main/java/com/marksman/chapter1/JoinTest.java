package com.marksman.chapter1;

public class JoinTest {
    public static void main(String [] args) throws InterruptedException {
        ThreadJoinTest t1 = new ThreadJoinTest("С��");
        ThreadJoinTest t2 = new ThreadJoinTest("С��");
        t1.start();
        /** join����˼��ʹ�÷�����ǰ�̵߳�ִ�У������ض�Ӧ���̣߳���������������˼���ǣ�
         	������main�߳��е���t1�̵߳�join��������main�̷߳���cpu����Ȩ��������t1�̼߳���ִ��ֱ���߳�t1ִ�����
         	���Խ����t1�߳�ִ����󣬲ŵ����߳�ִ�У��൱����main�߳���ͬ��t1�̣߳�t1ִ�����ˣ�main�̲߳���ִ�еĻ���
         */
        t1.join(2);
        t2.start();
    }

}
class ThreadJoinTest extends Thread{
    public ThreadJoinTest(String name){
        super(name);
    }
    @Override
    public void run(){
        for(int i=0;i<100;i++){
            System.out.println(this.getName() + ":" + i);
        }
    }
}
