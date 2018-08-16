/**
 * 
 */
package com.chnjan.senior.thread;

/**
 * @author chenjian
 * @date 2018年8月12日
 */
public class SpecificTask extends Atask {

	@Override
	public String call() {
		System.out.println(System.currentTimeMillis()+"t1bf");
		String result = null;
		try {
			Thread.sleep(3000);
			System.out.println(System.currentTimeMillis()+"t1ok");
			result = "hello "+user.getName()+",you are "+String.valueOf(user.getAge())+" years old,";
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}

}
