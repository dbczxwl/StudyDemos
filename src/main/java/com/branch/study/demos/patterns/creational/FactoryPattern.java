package com.branch.study.demos.patterns.creational;

/**
 * 关于简单工厂在末尾补充说明
 * 关于静态工厂在末尾补充说明
 * 
 * @author Administrator
 * 
 */
public class FactoryPattern {

	public static void main(String[] args) {
		CarFactory carFactory = CarFactory.newInstance();
		AirplaneFactory airplaneFactory = AirplaneFactory.newInstance();
		carFactory.produce().move();
		airplaneFactory.produce().move();
	}

}

interface Factory {
	// 买入原材料
	void purchase();

	// 生产
	Product produce();

	// 存货
	void inventory();

	// 销售
	void sell();

}

class CarFactory implements Factory {

	// 私有化构造器
	private CarFactory() {

	}

	// 使用静态工厂题带构造函数
	public static CarFactory newInstance() {
		return new CarFactory();
	}

	@Override
	public void purchase() {
		System.out.println("购买生产汽车的配件");
	}

	@Override
	public Product produce() {
		System.out.println("生产汽车");
		return new Car();
	}

	@Override
	public void inventory() {
		System.out.println("存储汽车");
	}

	@Override
	public void sell() {
		System.out.println("销售汽车");
	}

}

class AirplaneFactory implements Factory {

	// 私有化构造器
	private AirplaneFactory() {

	}

	public static AirplaneFactory newInstance() {
		return new AirplaneFactory();
	}

	@Override
	public void purchase() {
		System.out.println("购买生产飞机的配件");
	}

	@Override
	public Product produce() {
		System.out.println("生产飞机");
		return new Airplane();
	}

	@Override
	public void inventory() {
		System.out.println("存储飞机");
	}

	@Override
	public void sell() {
		System.out.println("销售飞机");
	}

}

interface Product {
	void move();
}

class Airplane implements Product {

	@Override
	public void move() {
		System.out.println("Airplane can fly");
	}

}

class Car implements Product {

	@Override
	public void move() {
		System.out.println("Car can run");
	}

}

/*
 * 简单工厂，通过传入不同的字符串判断取生产某种product，工厂模式通过不同的工厂生产不同的product
 * 相比于简单工厂，工厂模式遵守的开闭原则，即对扩展开放，对修改关闭
 */

/*
 * 静态工厂有工厂类直接调用，替代构造函数
 * valueOf和of方法，传入其他类型，返回静态工厂实例
 * PS：在包装类中parse方法与valueOf的区别在于，prase返回值为基本类型，而valueOf返回值为包装类型
 * getInstance和newInstance，区别在于newInstance体现在每个生成的对象都是新的，而getInstance可能是单例
 * getType和newType，在用A工厂创建B对象的时候使用，需要经B对象的类型作为参数传入，其他同getInstance和newInstance
 */