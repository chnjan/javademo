/**
 * 
 */
package com.chnjan.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import org.junit.Test;

import com.chnjan.other.ASimpleClass;
import com.chnjan.other.AnInterface;
import com.chnjan.other.AnInterfaceImpl;
import com.chnjan.other.TwoInterface;
import com.chnjan.other.TwoInterfaceImpl;
import com.chnjan.senior.proxy.DynamicProxy;
import com.chnjan.senior.proxy.StaticProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author chenjian
 * @date 2018年3月10日
 * 代理测试
 * 几种不同的代理区别就是，生成代理对象的方式不同
 * 静态代理：通过实现被代理的对象实现过的接口来创建一个代理
 * jdk动态代理：通过jdk的api，其使用反射原理来动态生成某个对象的代理
 * cglib动态代理：通过cglib框架，生成一个被代理类的子类，在这个子类里增强被代理对象的方法
 * 
 */
public class ProxyTest {

	//静态代理测试
	@Test
	public void staticProxyTest() {
		//要被代理的对象
		AnInterface interfaceImpl = new AnInterfaceImpl();
		
		//静态代理。  
		//因为代理对象和被代理对象实现了相同的接口，所以只要把原代码中被代理对象替换成代理对象就可以增强原功能（前提是通过接口调用才好用）
		AnInterface proxy = new StaticProxy(interfaceImpl);
		
		//通过执行代理的方法，达到执行被代理对象的方法，并能在执行之前和之后做点额外的事
		String rst = proxy.dosome("aaa", "bbb");
		System.out.println(rst);
	}
	
	//jdk动态代理测试，被代理的类要实现接口，否则会出异常
	@Test
	public void dynamicProxyTest() {
		//要被代理的对象
		AnInterface interfaceImpl = new AnInterfaceImpl();
		//Class<AnInterface>[] anitf = new 
		DynamicProxy h = new DynamicProxy();
		//通过被代理的对象获得其代理对象
		AnInterface anInterfaceProxy = (AnInterface) h.getDynamicProxy(interfaceImpl);
		String result = anInterfaceProxy.dosome("dynamicproxy", "ok");
		System.out.println(result);
		
		//另一个要代理的对象
		TwoInterface twoInterface  = new TwoInterfaceImpl();
		TwoInterface twoInterfaceProxy = (TwoInterface) h.getDynamicProxy(twoInterface);
		Integer i = twoInterfaceProxy.doOtherThing();
		System.out.println(i);
		
	}
	
	//动态代理的另一种方便写法
	@Test
	public void dynamicProxyTest2() {
		//一个要被代理的对象
		TwoInterface twoInterface  = new TwoInterfaceImpl();
		//获取代理对象，  new+接口：匿名类的写法，相当于定义一个类，此类实现了这个接口，只是这个类没有名字
		TwoInterface twoInterfaceProxy = (TwoInterface) Proxy.newProxyInstance(twoInterface.getClass().getClassLoader(),
				twoInterface.getClass().getInterfaces(), new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("dynamic another do something befor");
						//执行被代理对象的方法冰得到返回结果，也可以修改结果
						Object result = method.invoke(twoInterface, args);
						System.out.println("dynamic another do something after");
						return result;
					}
				});
		
		//通过代理来执行目标方法
		int i = twoInterfaceProxy.doOtherThing();
		System.out.println(twoInterfaceProxy.getClass().getName());
		System.out.println(i);
		twoInterfaceProxy.dothing1();
	}
	
	
	//cglib实现的代理， 被代理的类可以不实现接口，但一定要是可继承的
	@Test
	public void cglibProxyTest() {
		//增强子，用这个来创建一个代理类（其实就是通过继承来增强一个类）
		Enhancer enhancer = new Enhancer();
		//设置父类
		enhancer.setSuperclass(ASimpleClass.class);
		//设置回调（也就是要增强的操作）
		enhancer.setCallback(new MethodInterceptor() {
			
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				System.out.println(obj.getClass().getName());
				System.out.println(method.getName());
				System.out.println(Arrays.toString(args));
				System.out.println("cglib dynamic proxy befor");
				Object result = proxy.invokeSuper(obj, args);
				System.out.println("cglib dynamic proxy after");
				return result;
			}
		});
		
		//创建代理类（子类）
		ASimpleClass aSimpleClass = (ASimpleClass) enhancer.create();
		
		aSimpleClass.sayHi();
		//静态方法无效
		//aSimpleClass.dosome();
		
		String r = aSimpleClass.doother("sleep");
		System.out.println(r);
	}
}
