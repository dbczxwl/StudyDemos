package com.branch.study.demos.patterns.behavioral;

/**
 * TemplateMethod：模板方法模式
 * 抽象模板类中的步骤方法都是abstract的，模板方法是实现了的，用来规定步骤的顺序
 * 用途举例：spring bean初始化时候用到的AbstractApplicationContext的refresh方法是个模板方法
 * 
 * @author Administrator
 */
public class TemplateMethodPattern {

	public static void main(String[] args) {
		CookTemplate cookTemplate = new CookTemplate();
		cookTemplate.execute();
	}

}

abstract class Template {
	public abstract void step1();

	public abstract void step2();

	public abstract void step3();

	public void execute() {
		step1();
		step2();
		step3();
	}
}

class CookTemplate extends Template {
	@Override
	public void step1() {
		System.out.println("做菜第一步：洗菜");
	}

	@Override
	public void step2() {
		System.out.println("做菜第一步：切菜");
	}

	@Override
	public void step3() {
		System.out.println("做菜第一步：炒菜");
	}
}