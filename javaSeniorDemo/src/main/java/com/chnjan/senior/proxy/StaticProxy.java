/**
 * 
 */
package com.chnjan.senior.proxy;

import com.chnjan.other.AnInterface;

/**
 * @author chenjian
 * @date 2018年3月8日
 * 静态代理
 * 代理类要继承指定的接口
 */
public class StaticProxy implements AnInterface {
	
	//被代理对象
	private AnInterface target;
	
	//构造函数，参数为被代理的对象
	public StaticProxy(AnInterface target) {
		this.target = target;
	}

	@Override
	public String dosome(String a, String b) {
		System.out.println("static do something befor");
		//调用被代理对象的方法，这里得到结果后也可以对结果进行处理
		String result = target.dosome(a, b);
		System.out.println("static do something after");
		return result;
	}

}
