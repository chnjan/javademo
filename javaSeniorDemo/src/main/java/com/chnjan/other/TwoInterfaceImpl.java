/**
 * 
 */
package com.chnjan.other;

/**
 * @author chenjian
 * @date 2018年3月11日
 */
public class TwoInterfaceImpl implements TwoInterface {

	@Override
	public Integer doOtherThing() {
		System.out.println("something has been done");
		return 666;
	}

	@Override
	public String dothing1() {
		System.out.println("do thing 1");
		return "thing1";
	}

}
