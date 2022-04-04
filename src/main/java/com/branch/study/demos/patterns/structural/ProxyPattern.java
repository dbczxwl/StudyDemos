package com.branch.study.demos.patterns.structural;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * Proxy：代理模式
 * 代理模式侧重访问控制，以及访问前后的操作，用户类不能直接访问对象，需要通过代理类访问目标对象，代理对象可以通过反射获取
 * 与装饰器模式对比（类图一模一样）：装饰侧重于功能的扩展（常常可以多次包装），而代理侧重于访问控制（间接调用），并保留被代理类的完整功能
 * 为什么要使用动态代理：静态代理中每个代理类只能代理一个接口，想通过一个代理类代理全部接口需要使用动态代理（jdk动态代理，Cglib代理）
 * 两种动态代理：spring源码中，如果被代理类又实现接口默认使用Cglib代理（asm框架操作class字节码实现），反之使用jdk动态代理（通过反射实现）
 * 用途举例：jdk中的Proxy类，实现动态代理，远程代理、虚拟代理、保护代理、日志代理、缓存代理，当访问不一定成功的时候需要使用代理模式
 * 
 * @author Administrator
 */
public class ProxyPattern {

	public static void main(String[] args) {
		// 通过反射获取主题实现类
		StaticProxy defaultProxy = new StaticProxy();
		defaultProxy.request();

		// 通过反射传入参数主题实现类
		StaticProxy proxy = new StaticProxy(new ConcreteSubject());
		proxy.request();

		// jdk动态代理，动态代理可以适应不同接口，这里的Subject接口可以换为其他接口
		Subject subject = new ConcreteSubject();
		ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler(subject);
		// 这里代理对象是通过Proxy.newProxyInstance方法动态生成的，而不是提前定义好的，类型强转所实现的接口类型来调用其中的方法
		Subject jdkDynamicProxy = (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(),
				subject.getClass().getInterfaces(), proxyInvocationHandler);
		jdkDynamicProxy.request();

		// cjlib动态代理，不需要继承接口也可以完成代理
		CglibSubject cglibSubject = new CglibSubject();
		CglibSubject cglibDynamicProxy = (CglibSubject) new ProxyFactory(cglibSubject).getProxyInstance();
		cglibDynamicProxy.request();
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

// CglibSubject
class CglibSubject {
	public void request() {
		System.out.println("在被代理类没有实现接口的情况下使用cglib代理，执行被代理对象的操作");
	}
}

// Proxy，这里是通过关联实现的静态代理
class StaticProxy implements Subject {
	private Subject subject;

	// 默认代理自己
	public StaticProxy() {
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

	public StaticProxy(Subject subject) {
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
class ProxyInvocationHandler implements InvocationHandler {

	private Object object;

	public ProxyInvocationHandler(Object object) {
		this.object = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("在：" + new Date() + "时开始");
		Object invoke = method.invoke(object, args);
		System.out.println("在：" + new Date() + "时结束");
		return invoke;
	}
}

// Cglib动态代理（Spring的核心包）实现，除了拥有jdk动态代理的全部优点意外，glib动态代理不需要实现接口，运行效率比jdk动态代理高
class ProxyFactory implements MethodInterceptor {
	private Object object;

	public ProxyFactory(Object object) {
		this.object = object;
	}

	// 给目标对象创建一个代理对象
	public Object getProxyInstance() {
		// 1.工具类
		Enhancer en = new Enhancer();
		// 2.设置父类
		en.setSuperclass(object.getClass());
		// 3.设置回调函数
		en.setCallback(this);
		// 4.创建子类(代理对象)
		return en.create();
	}

	@Override
	public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("在：" + new Date() + "时开始");
		Object invoke = method.invoke(object, args);
		System.out.println("在：" + new Date() + "时结束");
		return invoke;
	}
}