package com.branch.study.demos.interview_question;



/*
 * 这个包下的文件用于验证一些有意思的面试题目
 */

public class ModuleInfo {
	static{
		System.out.println("静态代码块1 静态变量初始化之前");
	}
	static Cls cls= new Cls();
	static{
		System.out.println("静态代码块2 静态变量初始化之后");
	}
	public static void main(String[] args) {
		System.out.println("main方法");
	}
}
	
class Cls{
	Cls() {
		System.out.println("构造方法");
	}
}