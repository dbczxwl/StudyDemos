package com.branch.study.demos.new_features_of_java8.enhance_interface;

/**
 * Everything must be public in interface
 */
public interface InterfaceNewFeature {
	// Static Constant (omit: static final)
	int CONSTNUM = 1;

	// Abstract Method (omit: abstract)
	void abstractMethod();

	// Default Method needn't to be rewrite forcedly in implementation class
	default void defaultMethod() {
		System.out.println("Can called by implementation class");
		System.out.println("Can rewrited by implementation class");
		System.out.println("Distinguish from default method, needn't to be rewrite forcedly");
	}

	// Static method here can called by interface and can't be rewrote
	static void staticMethod() {
		System.out.println("Can called by interface");
	}

}
