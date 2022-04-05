package com.branch.study.demos.patterns.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite：组合模式
 * 树形结构的处理：Leaf叶子构件和Composite容器构件都实现了Component构建接口，Leaf为最末节点负责执行，Composite为非最末节点，执行时委派Leaf执行
 * Composite容器除了实现Component接口中的方法，还聚合了接口泛型的集合，并有对该集合进行管理方法，如该集合中还有Composite容器，则可进行递归调用
 * 用途举例：Element-UI的中组件的嵌套使用，Mybatis中SqlNode（<if><where><foreach>标签就是一个个的SqlNode节点）
 * 
 * @author Administrator
 */
public class CompositePattern {

	public static void main(String[] args) {
		Range china = new Range();
		Area beiJing = new Area(2200);
		Area shangHai = new Area(2500);
		china.add(beiJing);
		china.add(shangHai);

		Range liaoning = new Range();
		Area shenyang = new Area(900);
		Area dalian = new Area(600);
		liaoning.add(shenyang);
		liaoning.add(dalian);
		china.add(liaoning);

		int sum = china.count();
		System.out.println("统计人口数为" + sum + "万人");
	}

}

// Component抽象构件
interface Counter {
	int count();
}

// Leaf构建
class Area implements Counter {
	private int sum = 0;

	public Area(int sum) {
		this.sum = sum;
	}

	@Override
	public int count() {
		return sum;
	}
}

// Composite容器构件
class Range implements Counter {

	private List<Counter> counterList = new ArrayList<>();

	public void add(Counter counter) {
		counterList.add(counter);
	}

	public void delete(Counter counter) {
		counterList.remove(counter);
	}

	public List<Counter> getChild() {
		return counterList;
	}

	@Override
	public int count() {
		int sum = 0;
		for (Counter counter : counterList) {
			sum += counter.count();
		}
		return sum;
	}
}