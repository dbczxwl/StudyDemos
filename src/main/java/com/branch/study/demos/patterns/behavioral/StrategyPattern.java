package com.branch.study.demos.patterns.behavioral;

import java.util.Arrays;

/**
 * Strategy：策略模式
 * 不同测策略类实现同一个策略接口，context策略使用者中聚合一个策略接口（通过setStrategy方法维护此属性），通过选择不同测策略实现执行不同的策略
 * 策略使用者可以是抽象的，通过继承抽象的策略使用者，生成真正的策略使用者，策略接口的初始化最好在构造的时候就由，避免空指针
 * 本例中Coder是一个策略使用者，Programmer是一个抽象的策略使用者，client应该清楚知晓使用了哪种策略
 * 某个策略内部更改时不影响其他策略，也不会影响策略使用者
 * 策略模式：多种算法行为选择一个就能满足，算法之间时互相独立的
 * 状态模式：状态之间，状态和状态所有者之间可以存在相互依赖关系（规定先后顺序，状态控制方法等），状态之间相互转换，可以反复
 * 与状态模式区别：从类图上看两者完全一样，但实际意图不同，策略模式会控制对象使用什么策略，而状态模式会自动改变状态（由系统控制）
 * 用途举例：jdk中ThreadPoolExecutor函数最后一个参数RejectedExecutionHandler的的四种拒绝策略
 * 
 * @author Administrator
 */
public class StrategyPattern {

	public static void main(String[] args) {
		int[] arr1 = { 185, 56, 84, 30, 12, 5, 4896, 561, 357, 96, 74 };
		int[] arr2 = { 185, 56, 84, 30, 12, 5, 4896, 561, 357, 96, 74 };
		int[] arr3 = { 185, 56, 84, 30, 12, 5, 4896, 561, 357, 96, 74 };

		Coder traineeCoder = new Coder();
		traineeCoder.setSort(new BubbleSort());
		Coder juniorCoder = new Coder();
		juniorCoder.setSort(new SelectSort());
		Coder seniorCoder = new Coder();
		seniorCoder.setSort(new QuickSort());

		System.out.println("实习coder冒泡排序结果:" + Arrays.toString(traineeCoder.sort(arr1)));
		System.out.println("初级coder选择排序结果:" + Arrays.toString(juniorCoder.sort(arr2)));
		System.out.println("高级coder快速排序结果:" + Arrays.toString(seniorCoder.sort(arr3)));

		int[] arr4 = { 185, 56, 84, 30, 12, 5, 4896, 561, 357, 96, 74 };
		int[] arr5 = { 185, 56, 84, 30, 12, 5, 4896, 561, 357, 96, 74 };
		int[] arr6 = { 185, 56, 84, 30, 12, 5, 4896, 561, 357, 96, 74 };

		Programmer traineeProgrammer = new TraineeProgrammer();
		traineeProgrammer.setSort(new BubbleSort());
		Programmer juniorProgrammer = new JuniorProgrammer();
		juniorProgrammer.setSort(new SelectSort());
		Programmer seniorProgrammer = new SeniorProgrammer();
		seniorProgrammer.setSort(new QuickSort());

		System.out.println("实习programmer冒泡排序结果:" + Arrays.toString(traineeProgrammer.sort(arr4)));
		System.out.println("初级programmer选择排序结果:" + Arrays.toString(juniorProgrammer.sort(arr5)));
		System.out.println("高级programmer快速排序结果:" + Arrays.toString(seniorProgrammer.sort(arr6)));
	}

}

interface Sort {
	int[] sort(int[] intArray);
}

class BubbleSort implements Sort {
	@Override
	public int[] sort(int[] intArray) {
		int[] arr = intArray;

		// 冒泡
		for (int i = 0; i < arr.length; i++) {
			// 外层循环，遍历次数
			for (int j = 0; j < arr.length - i - 1; j++) {
				// 内层循环，升序（如果前一个值比后一个值大，则交换）
				// 内层循环一次，获取一个最大值
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = temp;
				}
			}
		}

		return arr;
	}
}

class SelectSort implements Sort {
	@Override
	public int[] sort(int[] intArray) {
		int[] arr = intArray;

		// 选择
		for (int i = 0; i < arr.length; i++) {
			// 默认第一个是最小的。
			int min = arr[i];
			// 记录最小的下标
			int index = i;
			// 通过与后面的数据进行比较得出，最小值和下标
			for (int j = i + 1; j < arr.length; j++) {
				if (min > arr[j]) {
					min = arr[j];
					index = j;
				}
			}
			// 然后将最小值与本次循环的，开始值交换
			int temp = arr[i];
			arr[i] = min;
			arr[index] = temp;
			// 说明：将i前面的数据看成一个排好的队列，i后面的看成一个无序队列。每次只需要找无需的最小值，做替换
		}

		return arr;
	}
}

class QuickSort implements Sort {
	@Override
	public int[] sort(int[] intArray) {
		int[] arr = intArray;

		// 快速排序
		int low = 0;
		int high = arr.length - 1;
		quickSort(arr, low, high);

		return arr;
	}

	private void quickSort(int[] arr, int low, int high) {
		// 如果指针在同一位置(只有一个数据时)，退出
		if (high - low < 1) {
			return;
		}
		// 标记，从高指针开始，还是低指针（默认高指针）
		boolean flag = true;
		// 记录指针的其实位置
		int start = low;
		int end = high;
		// 默认中间值为低指针的第一个值
		int midValue = arr[low];
		while (true) {
			// 高指针移动
			if (flag) {
				// 如果列表右方的数据大于中间值，则向左移动
				if (arr[high] > midValue) {
					high--;
				} else if (arr[high] < midValue) {
					// 如果小于，则覆盖最开始的低指针值，并且移动低指针，标志位改成从低指针开始移动
					arr[low] = arr[high];
					low++;
					flag = false;
				}
			} else {
				// 如果低指针数据小于中间值，则低指针向右移动
				if (arr[low] < midValue) {
					low++;
				} else if (arr[low] > midValue) {
					// 如果低指针的值大于中间值，则覆盖高指针停留时的数据，并向左移动高指针。切换为高指针移动
					arr[high] = arr[low];
					high--;
					flag = true;
				}
			}
			// 当两个指针的位置相同时，则找到了中间值的位置，并退出循环
			if (low == high) {
				arr[low] = midValue;
				break;
			}
		}
		// 然后出现有，中间值左边的小于中间值。右边的大于中间值。
		// 然后在对左右两边的列表在进行快速排序
		quickSort(arr, start, low - 1);
		quickSort(arr, low + 1, end);
	}
}

// Context，策略使用者
class Coder {
	private Sort sortBehavior;

	public void setSort(Sort sort) {
		this.sortBehavior = sort;
	}

	public Sort getSort() {
		return this.sortBehavior;
	}

	public int[] sort(int[] intArray) {
		if (sortBehavior != null) {
			return sortBehavior.sort(intArray);
		} else {
			return null;
		}
	}
}

// AbstractContext，抽象的策略使用者
abstract class Programmer {
	// 状态需要交给继承者控制时，使用protected比较方便
	protected Sort sortBehavior;

	public void setSort(Sort sort) {
		this.sortBehavior = sort;
	}

	public Sort setSort() {
		return this.sortBehavior;
	}

	public int[] sort(int[] intArray) {
		if (sortBehavior != null) {
			return sortBehavior.sort(intArray);
		} else {
			return null;
		}
	}
}

// ConcreateContext，实际的策略使用者
class TraineeProgrammer extends Programmer {
	@Override
	public void setSort(Sort sort) {
		super.setSort(sort);
	}
}

// ConcreateContext，实际的策略使用者
class JuniorProgrammer extends Programmer {
	@Override
	public void setSort(Sort sort) {
		super.setSort(sort);
	}
}

// ConcreateContext，实际的策略使用者
class SeniorProgrammer extends Programmer {
	@Override
	public void setSort(Sort sort) {
		super.setSort(sort);
	}
}