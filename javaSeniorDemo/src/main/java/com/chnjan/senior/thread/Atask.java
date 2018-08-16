/**
 * 
 */
package com.chnjan.senior.thread;

import java.util.concurrent.Callable;

import com.chnjan.other.User;

/**
 * @author chenjian
 * @date 2018年3月12日
 * 任务
 */
public abstract class Atask implements Callable<String>{
	
	protected User user;

	@Override
	public abstract String call();

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
