/**
 * 
 */
package com.chnjan.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import org.junit.Test;

import com.chnjan.other.User;
import com.chnjan.senior.thread.Atask;
import com.chnjan.senior.thread.SpecificTask;
import com.chnjan.senior.thread.SpecificTask2;
import com.chnjan.senior.thread.ThreadPoolUtil;

/**
 * @author chenjian
 * @date 2018年3月12日
 */
public class ThreadTest extends Thread {
	
	public User user1;
	
	public void setUser(User user) {
		this.user1 = user;
	}
	
	@Override
	public void run() {
		try {
			testPool();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPool() throws InterruptedException, ExecutionException {
		//User user1 = new User("tom",2);
		Atask task1 = new SpecificTask();
		task1.setUser(user1);
		Atask task2 = new SpecificTask2();
		task2.setUser(user1);
		
		List<Atask> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);
		ExecutorService pool = ThreadPoolUtil.getThreadPool();
		System.out.println(pool);
		List<Future<String>> result = null;
		try {
			//在规定时间内完成所有任务，如果完成不了则取消任务，
			//最后得到的future结果顺序与传入的任务集合里的顺序相同
			//方法invokeAll()是阻塞方法，只有所有的任务都有结果（成功或失败或异常）后才返回，
			result = pool.invokeAll(tasks);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println(System.currentTimeMillis());
		/*String r0 = result.get(0).get();
		String r1 = result.get(1).get();
		
		System.out.println(r0+"0");
		System.out.println(r1+"1");*/
		StringBuilder sb = new StringBuilder();
		for (Future<String> future : result) {
			try {
				if (!future.isCancelled()) {
					sb.append(future.get());
				}
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println(sb);
	}

}
