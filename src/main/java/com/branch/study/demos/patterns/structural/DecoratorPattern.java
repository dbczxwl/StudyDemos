package com.branch.study.demos.patterns.structural;

/**
 * Decorator：装饰器模式（包装类）
 * 扩展一个类可以通过，继承或者关联
 * 从类图上看是Decorator接口对Component接口的扩展
 * Decorator类继承Component接口并通过传入Component的另一个实现类，拷贝或部分拷贝原实现类中的功能
 * Decorator设置为abstract，可以更方便装饰类的扩展，Decorator者中关联的Component的接口而非实现类
 * Java io流中FilterInputStream抽象类的子类通过传入其他InputStream，进行功能扩展
 * 
 * @author Administrator
 */
public class DecoratorPattern {

	public static void main(String[] args) {
		Iphone iphone = new Iphone(new OldPhone());
		iphone.sendMessage();
		iphone.callUp();
		iphone.playGame();
		iphone.watchMovie();
	}

}

// Component
interface Phone {
	void sendMessage();

	void callUp();
}

// ConcreateComponent
class OldPhone implements Phone {
	@Override
	public void sendMessage() {
		System.out.println("老式电话可以发短信");

	}

	@Override
	public void callUp() {
		System.out.println("老式电话可以打电话");
	}
}

// Decorator，可以看到这里关联的是Phone接口，保证低耦合度
abstract class SmartPhone implements Phone {
	private Phone phone;

	SmartPhone(Phone phone) {
		this.phone = phone;
	}

	@Override
	public void sendMessage() {
		System.out.println("智能电话可以用老电话的方式发短信");
		phone.sendMessage();
	}

	@Override
	public void callUp() {
		System.out.println("智能电话用自己的方式打电话");
	}

	public abstract void playGame();

	public abstract void watchMovie();
}

// ConcreateDecorator
class Iphone extends SmartPhone {
	Iphone(Phone phone) {
		super(phone);
	}

	@Override
	public void playGame() {
		System.out.println("Iphone可以玩游戏");
	}

	@Override
	public void watchMovie() {
		System.out.println("Iphone可以看电影");
	}
}