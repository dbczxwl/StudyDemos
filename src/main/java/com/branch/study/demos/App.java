package com.branch.study.demos;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) throws Exception {
		System.out.println("Hello World!");
		/*
		 * SOILD原则
		 * • SRP：单一职责原则
		 * • OCP：开放闭合原则
		 * • LSP：里氏替换原则
		 * • ISP：接口隔离原则
		 * • DIP：依赖倒置原则
		 * + 最小知道法则
		 */
		System.out.println("写在前头的话!");

		Partner partner = new Partner("AAA");
		Book book1 = new Book("养猪密集", 100, partner);
		Book book2 = book1.clone();
		System.out.println(book1.name + "/" + book1.price + "/" + book1.partner.name);
		System.out.println(book2.name + "/" + book2.price + "/" + book2.partner.name);
		book2.name = "养鸡密集";
		book2.price = 200;
		book2.partner.name = "BBB";
		// clone方法不会递归，从打印结果可以看出partner被浅拷贝了
		// 想要深拷贝，①Partner要实现Cloneable接口，并重写clone方法，②添加代码book.partner =
		// this.partner.clone();
		System.out.println(book1.name + "/" + book1.price + "/" + book1.partner.name);
		System.out.println(book2.name + "/" + book2.price + "/" + book2.partner.name);
	}
}

class Book implements Cloneable {
	public String name; // 姓名
	public int price; // 价格
	public Partner partner; // 合作伙伴

	public Book(String name, int price, Partner partner) {
		this.name = name;
		this.price = price;
		this.partner = partner;
	}

	@Override
	protected Book clone() throws CloneNotSupportedException {
		Book book = (Book) super.clone();
		// book.partner = this.partner.clone();
		return book;
	}
}

// class Partner implements Cloneable {
class Partner {
	public String name;

	Partner(String name) {
		this.name = name;
	}

	// @Override
	// protected Partner clone() throws CloneNotSupportedException {
	// Partner partner = (Partner) super.clone();
	// return partner;
	// }
}