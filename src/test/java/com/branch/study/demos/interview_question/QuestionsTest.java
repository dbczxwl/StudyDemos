package com.branch.study.demos.interview_question;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class QuestionsTest {
	// 用于测试类的初始化
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("用于测试类的初始化");
	}

	// 用于测试类的清理
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("用于测试类的清理");
	}

	// 用于每个测试方法前的初始化
	@Before
	public void setUp() throws Exception {
		System.out.println("用于每个测试方法前的初始化");
	}

	// 用于每个测试方法后的清理
	@After
	public void tearDown() throws Exception {
		System.out.println("用于每个测试方法后的清理");
	}

	// 这是一个测试方法
	@Test
	public void testMain() {
		Questions.main(null);
	}

}
