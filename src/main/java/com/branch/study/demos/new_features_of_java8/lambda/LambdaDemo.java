package com.branch.study.demos.new_features_of_java8.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaDemo {
	public static void main(String[] args) {
		noneParamPerform();
		withParamPerform();
	}

	private static void noneParamPerform() {

		FunctionalIF functionalIF = () -> {
			System.out.println("@FunctionalInterface can ensure there only one method in the interface");
			System.out.println("The left-hand side of an assignment must be a variable");
		};
		functionalIF.singleMethod();

		// Anonymous Inner Class
		new Thread(new Runnable() {
			public void run() {
				System.out.println("new thread1: " + Thread.currentThread().getName());
			}
		}).start();

		// The interface that only have one method can be implement by Lambda
		new Thread(() -> {
			System.out.println("new thread2: " + Thread.currentThread().getName());
		}).start();

		System.out.println("main thread: " + Thread.currentThread().getName());
	}

	private static void withParamPerform() {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("Tom", 25));
		personList.add(new Person("Bill", 13));
		personList.add(new Person("Bob", 41));
		personList.add(new Person("Lucy", 36));

		// Anonymous Inner Class
		Collections.sort(personList, new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				return p1.age - p2.age;
			}
		});

		// Lambda
		Collections.sort(personList, (Person p1, Person p2) -> {
			return p1.age - p2.age;
		});

		for (Person person : personList) {
			System.out.println(person);
		}
	}

}

class Person {
	String name;
	int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

}

/**
 * @FunctionalInterface can ensure there only one method in the interface
 */
@FunctionalInterface
interface FunctionalIF {
	void singleMethod();

}