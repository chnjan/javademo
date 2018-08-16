/**
 * 
 */
package com.chnjan.test;

import com.chnjan.other.User;

/**
 * @author chenjian
 * @date 2018年3月12日
 * main方法测
 */
public class ThreadMainTest extends Thread {
	
	public static void main(String[] args) {
		ThreadTest t1 = new ThreadTest();
		t1.setUser(new User("tom",2));
		ThreadTest t2 = new ThreadTest();
		t2.setUser(new User("jerry",1));
		//t1.user1 = new User("jerry",1);
		t1.start();
		t2.start();
	}
	
}
