package com.branch.study.demos.patterns.structural;

/**
 * Facade：外观模式
 * client类通过Facade类调用多个子系统，Facade关联多个子系统并向外暴露简化的接口（符合迪米特法则），对子系统的复杂业务进行封装
 * 添加新的子系统可能会导致外观类的代码变更（比如加一块ssd固态硬盘需要在原有的外观类中修改代码，修改开放了），不符合开闭原则
 * 改进：将Facade类定义为abstract可以通过添加新Facade而非修改已有的Facade来实现，部分解决了外观模式不符合开闭原则的问题
 * 用途举例：通过JdbcUtils类操作数据库（封装了jdbc的复杂操作）
 * 
 * @author Administrator
 */
public class FacadePattern {

	public static void main(String[] args) {
		Computer computer = new Computer();
		computer.start();
		computer.close();

		ComputerWithSsd computerWithSsd = new ComputerWithSsd();
		computerWithSsd.start();
		computerWithSsd.close();
	}

}

// Facade
class Computer {
	private Cpu cpu = new Cpu();
	private Memory memory = new Memory();
	private Disk disk = new Disk();

	public void start() {
		System.out.println("开启computer");
		cpu.start();
		memory.start();
		disk.start();
	}

	public void close() {
		System.out.println("关闭computer");
		cpu.close();
		memory.close();
		disk.close();
	}
}

// SubSystem
class Cpu {
	public void start() {
		System.out.println("开启cpu");
	}

	public void close() {
		System.out.println("关闭cpu");
	}
}

// SubSystem
class Memory {

	public void start() {
		System.out.println("开启memory");
	}

	public void close() {
		System.out.println("关闭memory");
	}
}

// SubSystem
class Disk {
	public void start() {
		System.out.println("开启disk");
	}

	public void close() {
		System.out.println("关闭disk");
	}
}

// SubSystem
class Ssd {
	public void start() {
		System.out.println("开启ssd");
	}

	public void close() {
		System.out.println("关闭ssd");
	}
}

// AbstractFacade
abstract class AbstractComputer {
	public abstract void start();

	public abstract void close();
}

// ConcreateFacade
class ComputerWithSsd {
	private Cpu cpu = new Cpu();
	private Memory memory = new Memory();
	private Disk disk = new Disk();
	private Ssd ssd = new Ssd();

	public void start() {
		System.out.println("开启computerWithSsd");
		cpu.start();
		memory.start();
		disk.start();
		ssd.start();
	}

	public void close() {
		System.out.println("关闭computerWithSsd");
		cpu.close();
		memory.close();
		disk.close();
		ssd.close();
	}
}