package com.branch.study.demos.patterns.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer：观察者模式（发布订阅模式，生产者消费者模式）
 * 定义了对象间的一对多关系，当发布者状态更新，会通知所有的订阅者并自动更新
 * 用途举例：springboot中ApplicationEvent的 事件-监听机制 用到观察者模式
 * 
 * @author Administrator
 */
public class ObserverPattern {

	public static void main(String[] args) throws Exception {
		int count = 0;
		ConcreatePublisher concreatePublisher = new ConcreatePublisher();
		concreatePublisher.addSubscriber(new SubscriberA());
		concreatePublisher.addSubscriber(new SubscriberB());
		while (true) {
			Thread.sleep(1000);
			if (count == 10) {
				concreatePublisher.status = 1;
				System.out.println("内容完成度：" + count + "/10");
				concreatePublisher.notice();
				break;
			} else {
				System.out.println("内容完成度：" + count + "/10");
			}
			count++;
		}
	}

}

interface Publisher {
	void addSubscriber(Subscriber subscriber);

	void notice();
}

class ConcreatePublisher implements Publisher {
	private List<Subscriber> subscriberList = new ArrayList<>();

	public int status = 0;

	@Override
	public void addSubscriber(Subscriber subscriber) {
		// 添加订阅者
		subscriberList.add(subscriber);
	}

	@Override
	public void notice() {
		subscriberList.forEach(subscriber -> subscriber.readContent());
	}

}

interface Subscriber {
	void readContent();
}

class SubscriberA implements Subscriber {
	@Override
	public void readContent() {
		System.out.println("读者A阅读订阅内容");
	}
}

class SubscriberB implements Subscriber {
	@Override
	public void readContent() {
		System.out.println("读者B阅读订阅内容");
	}
}