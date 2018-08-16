/**
 * 
 */
package com.chnjan.senior.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author chenjian
 * @date 2018年3月10日
 * 动态代理,使用jdk的动态代理
 * 代理类不需继承指定接口，但要继承一个固定的接口InvocationHandler
 */
public class DynamicProxy implements InvocationHandler{
	
	private Object target;
	
	//通过目标对象获得目标对象的代理
	public Object getDynamicProxy(Object target) {
		if (target == null) {
			return null;
		}
		this.target = target;
		Object targetProxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), this);
		return targetProxy;
	}
	
	/**
	 * @param proxy 被代理的对象
	 * @param method 要执行的被代理对象的方法
	 * @param args 要执行的被代理对象的方法的参数
	 * 
	 * 此方法里对被代理对象的方法进行增强
	 * */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(proxy.getClass().getName());
		System.out.println("dynamic do something befor");
		
		//利用反射原理，执行被代理对象的目标方法，并得到执行的结果
		//得到结果后也可以对结果进行处理
		Object result = method.invoke(target, args);
		
		System.out.println("dynamic do something after");
		return result;
	}

}
