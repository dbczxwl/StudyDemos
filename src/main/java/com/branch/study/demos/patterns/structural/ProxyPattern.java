package com.branch.study.demos.patterns.structural;

import java.lang.reflect.InvocationTargetException;

/**
 * Proxy：代理模式
 * 代理模式侧重访问控制，以及访问前后的操作，用户类不能直接访问对象，需要通过代理类访问目标对象，代理对象可以通过反射获取
 * 与装饰器模式对比（类图一模一样）：装饰侧重于功能的扩展（常常可以多次包装），而代理侧重于访问控制（间接调用），并保留被代理类的完整功能
 * 为什么要使用动态代理：静态代理中每个代理类只能代理一个接口，想通过一个代理类完成全部的代理功能需要使用动态代理
 * 用途举例：jdk中的Proxy类，实现动态代理
 * 
 * @author Administrator
 */
public class ProxyPattern {

	public static void main(String[] args) {
		Proxy proxyDefault = new Proxy();
		proxyDefault.request();
		Proxy proxy = new Proxy(new ConcreteSubject());
		proxy.request();
	}

}

// Subject
interface Subject {
	public void request();
}

// ConcreteSubject
class ConcreteSubject implements Subject {
	@Override
	public void request() {
		System.out.println("执行被代理对象的操作");
	}
}

// ConcreteSubject
class ConcreteSubjectDefault implements Subject {
	@Override
	public void request() {
		System.out.println("执行默认被代理对象的操作");
	}
}

// Proxy，这里是通过关联实现的静态代理
class Proxy implements Subject {
	private Subject subject;

	// 默认代理自己
	public Proxy() {
		try {
			// 通过反射获取默认代理
			subject = (ConcreteSubjectDefault) this.getClass().getClassLoader()
					.loadClass("com.branch.study.demos.patterns.structural.ConcreteSubjectDefault")
					.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Proxy(Subject subject) {
		this.subject = subject;
	}

	@Override
	public void request() {
		this.before();
		this.subject.request();
		this.after();
	}

	private void before() {
		System.out.println("访问代理之前开启链接");
	}

	private void after() {
		System.out.println("访问代理之后打印log：访问代理成功");
	}
}

// jdk动态代理实现
class JdkStaticProxy {

}

// Cglib动态代理（Spring的核心包）实现，除了拥有jdk动态代理的全部优点意外，glib动态代理不需要实现接口，运行效率比jdk动态代理高
class CglibStaticProxy {

}