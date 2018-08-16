/**
 * 
 */
package com.chnjan.senior.thread;

/**
 * @author chenjian
 * @date 2018年3月12日
 */
public class SpecificTask2 extends Atask {

	@Override
	public String call() {
		System.out.println(System.currentTimeMillis()+"t2");
		String result = "And "+user.getName()+",you looks stupid";
		return result;
	}

}
