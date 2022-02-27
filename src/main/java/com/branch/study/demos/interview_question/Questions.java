package com.branch.study.demos.interview_question;

import java.util.Arrays;

public class Questions {
	public static void main(String[] args) {
		// Q001.ask();
		// Q002.ask();
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