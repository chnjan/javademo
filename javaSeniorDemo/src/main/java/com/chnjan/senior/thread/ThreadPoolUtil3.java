/**
 * 
 */
package com.chnjan.senior.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenjian
 * @date 2018年3月11日
 * 获得线程池
 * 
 */
public class ThreadPoolUtil3 {
	
	/**
	 * 线程个数
	 * */
	private static int threadCount = 20;
	
	private static ExecutorService threadPool = null;
	
	/**
	 * 获取线程池实例
	 * */
	public static ExecutorService getThreadPoolInstance() {
		if (threadPool == null) {
			//创建固定线程个数的线程池,每提交一个任务就是一个线程，直到达到线程池的最大数量，然后后面进入等待队列直到前面的任务完成才继续执行
			threadPool = Executors.newFixedThreadPool(threadCount);
			//创建单个线程的线程池，即线程池中每次只有一个线程工作，单线程串行执行任务
			//threadPool = Executors.newSingleThreadExecutor();
			//可缓存的线程池,当线程池大小超过了处理任务所需的线程，那么就会回收部分空闲（一般是60秒无执行）的线程，当有任务来时，又智能的添加新线程来执行
			//threadPool = Executors.newCachedThreadPool();
		}
		return threadPool;
	}

}
