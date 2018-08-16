/**
 * 
 */
package com.chnjan.senior.reflex;

//import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author chenjian
 * @date 2018年3月8日
 * 反射
 */
public class ReflexSample {

	public static void reflex() throws Exception{
		Class<?> asimpleClass = Class.forName("com.chnjan.other.ASimpleClass");
		//使用无参构造器实例化一个对象
		Object ainstance = asimpleClass.newInstance();
		
		//使用构造函数来实例化一个对象
		/*Constructor<?> constructor = asimpleClass.getConstructor(String.class);
		Object ainstance2 = constructor.newInstance("constructor");*/
		
		//获取名字叫sayhi的方法
		Method methodSayhi = asimpleClass.getMethod("sayHi");
		//执行sayhi方法，参数为对象和方法参数
		methodSayhi.invoke(ainstance);
		
		//获取名字叫sayHello的方法,获取私有，保护等方法时使用getDeclaredMethod
		Method methodsayHello = asimpleClass.getDeclaredMethod("sayHello", String.class);
		//设置方法可以访问，因为此方法为私有，所以设置后才能执行
		methodsayHello.setAccessible(true);
		//执行sayHello方法，参数为对象和方法入参
		Object rst = methodsayHello.invoke(ainstance,"reflex");
		//执行结果
		System.out.println(rst);
		
		//执行静态方法是一样的
		Method methodDosome = asimpleClass.getMethod("dosome");
		methodDosome.invoke(ainstance);
	}
}
