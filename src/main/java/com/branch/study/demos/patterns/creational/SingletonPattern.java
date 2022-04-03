package com.branch.study.demos.patterns.creational;

import java.io.Serializable;

/**
 * 构造方法私有化，通过静态变量接收构造方法返回值
 * 饿汉模式，在声明静态单例变量时初始化，线程安全，内存存在额外开销
 * 懒汉模式，在使用时初始化，非线程安全，节省内存，需要通过双重检查锁缩小枷锁范围
 * 静态内部类实现，推荐用这种方法
 * 枚举类实现，效率最高，不能懒加载，防止反序列化，反射，克隆生成实例
 * 其他方法中：添加readObject()防止反序列化，在构造方法中判断INSTANCE != null防止反射（懒汉模式无效），重写clone()防止克隆
 * 单例存在的问题，只有一个实例，属性状态难以确定（当被多个调用者修改时），所以单例通常为无状态的工具类
 * 单例出现多实例的特例：分布式系统，或者一个系统中通过多个classLoader来加载单例对象
 * 推荐使用枚举和饿汉模式，理由是内存普遍比较充裕，并且懒汉模式需要DCL，或者静态内部类做优化，增加复杂度，而且对反射攻击不好处理
 * @author Administrator
 */
public class SingletonPattern {

	public static void main(String[] args) {
		SingletonHungry sh1 = SingletonHungry.getInstance();
		System.out.println(sh1);
		SingletonHungry sh2 = SingletonHungry.getInstance();
		System.out.println(sh2);
		
		SingletonLazy sl1 = SingletonLazy.getInstance();
		System.out.println(sl1);
		SingletonLazy sl2 = SingletonLazy.getInstance();
		System.out.println(sl2);
		
		SingletonInnerClass sic1 = SingletonInnerClass.getInstance();
		System.out.println(sic1);
		SingletonInnerClass sic2 = SingletonInnerClass.getInstance();
		System.out.println(sic2);
		
		Object obj1 = SingletonEnum.getInstance();
		System.out.println(obj1);
		Object obj2 = SingletonEnum.getInstance();
		System.out.println(obj2);
	}

}

// 饿汉模式，可用（这里假设对象可以序列化，因为反序列化会破坏单例）
class SingletonHungry implements Serializable, Cloneable {
	private final static SingletonHungry INSTANCE = new SingletonHungry();

	private SingletonHungry() {
		// 防止反射攻击，反射时如果类没有初始化，则需要先初始化再反射，此时已经满足INSTANCE != null
		if (INSTANCE != null) {
			throw new RuntimeException("单例模式不能重复创建对象");
		}
	}

	public static SingletonHungry getInstance() {
		return INSTANCE;
	}

	// 反序列化时readObject()方法会去寻找readResolve()方法，如果没找到返回新对象，如果找到返回readResolve()方法的返回值
	private Object readResolve() {
		return INSTANCE;
	}

	// 如果没有实现Cloneable接口会抛出CloneNotSupportedException异常
	@Override
	public Object clone() throws CloneNotSupportedException {
		return INSTANCE;
	}
}

// 双重检查锁优化过的懒汉模式，可用(普通的懒汉模式在方法上加锁，多线程访问时效低)
class SingletonLazy {
	// 这里不能使用final修饰，因为final需要在定义时就初始化并且再无法改变对象引用，volatile用来禁止指令重排
	private static volatile SingletonLazy instance = null;

	private SingletonLazy() {

	}

	public static SingletonLazy getInstance() {
		// 当实例已存在直接跳过
		if (instance == null) {
			// 当实例不存在，枷锁确保只生成一个实例
			synchronized (SingletonLazy.class) {
				if (instance == null) {
					instance = new SingletonLazy();
				}
			}
		}
		return instance;
	}
}

/*
 * 静态内部类优化过的懒汉模式，只有调用内部类的instance属性时，才对内部类初始化，是一种推荐的写法
 * 通过类加载机制保证线程安全（虚拟机类加载器在类初始化时会给<clinit>()方法上锁保证线程安全）
 */
class SingletonInnerClass {
	private SingletonInnerClass() {

	}

	public static SingletonInnerClass getInstance() {
		return SingletonHolder.instance;
	}

	// 懒加载，只有当调用getInstance()方法需要返回instance对象的时候，才进行加载
	private static class SingletonHolder {
		public static SingletonInnerClass instance = new SingletonInnerClass();
	}
}

/*
 * 所有枚举对象都是继承自java.lang.Enum类，且不能继承其他类，但可以实现接口
 * 枚举的构造方法固定为private的且可以省略，反射拿不到枚举的构造方法
 * 枚举实例都是static final类型的，并使用其构造方法进行初始化（所以枚举类中，构造方法再其他静态变量之前被调用了），并且不会再改变
 * 枚举可以有域和方法，给每个枚举实例使用
 * 调用枚举"SingletonEnum.getInstance()"，返回单例Object对象（可以是网络连接，数据库连接，线程池等）
 */
enum SingletonEnum {
	INSTANCE;

	private Object instance;

	SingletonEnum() {
		instance = new Object();
	}

	// 这是一个静态工厂，再枚举中由于枚举常量使用枚举类的构造方法初始化，所以构造方法再其他静态变量之前被调用了，此时instance有值
	public static Object getInstance() {
		return SingletonEnum.INSTANCE.instance;
	}
}
