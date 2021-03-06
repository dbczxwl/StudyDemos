package com.branch.study.demos.patterns;

public class otherPatterns {

	public static void main(String[] args) {
		// 接口的存在是为了规范类的行为，比如享元模式中享元接口需要对享元的状态进行管理，是使用/使用完毕，需要两个方法
		
		// 建造者模式，使用方法链（返回值为也是builder对象，只有build方法返回要构造的对象）的方式构造对象

		// 桥接模式 参考 装饰器模式
		// 享元模式 参考 单例模式（享元为共享的元素，相当是多例，通过一个集合管理多例的状态，是池技术的原理）

		// 原型模式：原型类通过clone方法传入自身，并重写clone，实现深拷贝，克隆完整对象（属性+方法）。原型模式和备忘录模式都通过传入自身防止破坏封装
		// 备忘录模式：文档类传入文档备忘录类（和文档类有同样属性，用来记录属性的历史变更，并实现备忘录接口提供版本信息等），通过一个stack管理备份，只管属性
		// 责任链模式 参考 状态模式
		// 命令模式 是常用模式，命令类继承命令接口，将命令传入触发器，出发器在自己的方法中调用命令类的函数操作业务逻辑（软件分层解耦），可排队执行，可撤销
		// 访问者模式 用于升级，最复杂的一种设计模式，访问者传入自身到被访问者，被访问者将自己（this）托管给传入的访问者，访问者操作升级
		// 解释器模式 用于解释固定的语法
		// 中介者模式 参考 装饰器模式，代理模式（1对1），桥接模式（1对n），中介者（n对n）
	}

}
