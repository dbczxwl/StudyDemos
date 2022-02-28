package com.branch.study.demos.interview_question;

import java.util.Arrays;

public class Questions {
	public static void main(String[] args) {

		System.out.println("Q001 Answer:");
		Q001.ask();

		System.out.println("Q002 Answer:");
		Q002.ask();

		System.out.println("Q003 Answer:");
		Q003.ask();
	}
}

/**
 * @author Administrator
 *         Q001和Q002这个提问主要考察参数传递时的值传递
 * 
 *         ①java只有值传递（传递参数的副本，对基础类型+String+封装类：变量名+栈上的值/对引用类型：变量名+地址），没有引用传递（直接传递参数本身）
 *         ②对参数副本的直接赋值，无法对函数外产生影响
 *         ③如果参数副本是引用类型，可以通过地址找到并改变堆上的值，从而对函数外产生影响
 */
class Q001 {

	/**
	 * 变量a，b，c的值会被改变么？
	 */
	public static void ask() {
		String a = "Say Hello!";
		String[] b = { "Say", "Hello!" };
		String[] c = { "Say", "Hello!" };
		Q001.sayHi(a, b, c);
		System.out.println(a);
		System.out.println(Arrays.asList(b));
		System.out.println(Arrays.asList(c));
	}

	private static void sayHi(String a, String[] b, String[] c) {
		a = "Say Hi!";
		b = new String[] { "Say", "Hi!" };
		c[1] = "Hi!";
	}
}

/**
 * @author Administrator
 *         Q001和Q002这个提问主要考察参数传递时的值传递
 * 
 *         ①java只有值传递（传递参数的副本，对基础类型+String+封装类：变量名+栈上的值/对引用类型：变量名+地址），没有引用传递（直接传递参数本身）
 *         ②对参数副本的直接赋值，无法对函数外产生影响
 *         ③如果参数副本是引用类型，可以通过地址找到并改变堆上的值，从而对函数外产生影响
 */
class Q002 {

	/**
	 * 变量p1，p2的值会被改变么？
	 */
	public static void ask() {
		Person p1 = new Person("张三", 18);
		Person p2 = new Person("李四", 81);

		Q002.change(p1, p2);
		System.out.println(p1);
		System.out.println(p2);

		Q002.changeValue(p1, p2);
		System.out.println(p1);
		System.out.println(p2);
	}

	// 给引用类型参数直接赋值
	public static void change(Person p1, Person p2) {
		Person temp = null;
		temp = p1;
		p2 = p1;
		p1 = temp;
	}

	// 通过引用类型参数，修改其堆上的值
	private static void changeValue(Person p1, Person p2) {
		p1.setAge(33);
		p2.setAge(66);
	}

	static class Person {
		private String name;
		private int age;

		public Person(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + "]";
		}
	}
}

/**
 * @author Administrator
 *         Q003这个提问考察的是代码中return和finally的执行顺序
 * 
 *         ①try代码块中代码运行到return语句之后，先将return压入栈，并寻找是否有finally代码块
 *         ②如果找到finally代码块，执行finally里边的语句，之后将return弹出栈
 *         ③如果finally中还有return，由于finally中的return后压入栈并会先弹出，所以会覆盖try中的return(catch中的也会被覆盖)
 */
class Q003 {

	/**
	 * finally中的语句和return语句那个先执行？
	 */
	public static void ask() {
		System.out.println(returnAndFinally());
	}

	@SuppressWarnings("finally")
	private static String returnAndFinally() {
		try {
			System.out.println("Code in try.");
			return "return_try";
		} catch (Exception e) {
			System.out.println("Code in catch.");
			return "return_catch";
		} finally {
			System.out.println("Code in finally.");
			// return "return_finally";
		}
	}
}