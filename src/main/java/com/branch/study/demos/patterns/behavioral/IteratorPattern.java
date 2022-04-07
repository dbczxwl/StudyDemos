package com.branch.study.demos.patterns.behavioral;

/**
 * Iterator：迭代子模式
 * client关联集合接口和迭代子接口，集合类和迭代子类分别实现对应接口，并相互依赖
 * 用途举例：jdk集合中使用迭代子模式，相比于for循环，主要还是为了解耦
 * 
 * @author Administrator
 */
public class IteratorPattern {

	public static void main(String[] args) {
		Object[] objArray = { "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eigth" };
		Group group = new ConcreteGroup(objArray);
		Iterator iterator = group.createIterator();
		while (!iterator.isDone()) {
			System.out.println(iterator.currentItem());
			iterator.next();
		}
	}

}

abstract class Group {
	// 工厂方法，创建相应迭代子对象的接口
	public abstract Iterator createIterator();
}

class ConcreteGroup extends Group {
	private Object[] objArray = null;

	// 构造方法，传入聚合对象的具体内容
	public ConcreteGroup(Object[] objArray) {
		this.objArray = objArray;
	}

	@Override
	public Iterator createIterator() {
		return new ConcreteIterator(this);
	}

	// 取值方法，向外界提供聚集元素
	public Object getElement(int index) {
		if (index < objArray.length) {
			return objArray[index];
		} else {
			return null;
		}
	}

	// 取值方法，向外界提供聚集的大小
	public int size() {
		return objArray.length;
	}
}

interface Iterator {
	// 迭代方法，移动至第一个元素
	public void first();

	// 迭代方法，移动至下一个元素
	public void next();

	// 迭代方法，是否为最后一个元素
	public boolean isDone();

	// 迭代方法，返回当前元素
	public Object currentItem();
}

class ConcreteIterator implements Iterator {
	// 持有被迭代的具体的聚合对象
	private ConcreteGroup group;
	// 内部索引，记录当前迭代到的索引位置
	private int index = 0;
	// 记录当前索引对象的大小
	private int size = 0;

	// 构造函数，传入具体的聚合对象，并获取聚合对象大小
	public ConcreteIterator(ConcreteGroup group) {
		this.group = group;
		this.size = group.size();
		this.index = 0;
	}

	// 移动至第一个元素
	public void first() {
		this.index = 0;
	}

	// 迭代方法，移动到下一个元素
	public void next() {
		if (this.index < this.size) {
			this.index++;
		}
	}

	// 迭代方法，是否是最后一个元素
	public boolean isDone() {
		return (this.index >= this.size);
	}

	// 迭代方法，返还当前位置元素
	public Object currentItem() {
		return this.group.getElement(this.index);
	}
}