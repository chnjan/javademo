/**
 * 
 */
package com.chnjan.other;

/**
 * @author chenjian
 * @date 2018年3月8日
 */
public class ASimpleClass {

	private String s;
	
	public String b = "hello";
	
	public ASimpleClass() {
		this.s = "hi";
	}
	
	public ASimpleClass(String s) {
		this.s = s;
	}
	
	public void sayHi() {
		System.out.println(s);
	}
	
	public String doother(String s) {
		System.out.println("do something "+s);
		return s+" ok";
	}
	
	@SuppressWarnings("unused")
	private String sayHello(String some) {
		s = "hi";
		return s + " " + some + " " + b;
	}
	
	public static void dosome() {
		System.out.println("do something");
	}
}
