package com.branch.study.demos.patterns.behavioral;

/**
 * State：状态模式
 * 将状态作为接口剥离，状态实现类中的方法代表这个状态下的行为，状态拥有者聚合状态接口，状态类之间，状态类和状态拥有者之间存在依赖
 * 状态模式：状态之间，状态和状态所有者之间可以存在相互依赖关系（规定先后顺序，状态控制方法等），状态之间相互转换，可以反复
 * 策略模式：多种算法行为选择一个就能满足，算法之间时互相独立的
 * 与策略模式区别：从类图上看两者完全一样，但实际意图不同，策略模式会控制对象使用什么策略，而状态模式会自动改变状态（由系统控制）
 * 
 * @author Administrator
 */
public class StatePattern {

	public static void main(String[] args) {
		// 初始化及其中放15个糖果
		GumballMachine gumballMachine = new GumballMachine(15);
		System.out.println(gumballMachine.getState());

		// 有糖果，投币，主动退币
		gumballMachine.insertQuarter();
		System.out.println(gumballMachine.getState());
		gumballMachine.ejectQuarter();
		System.out.println(gumballMachine.getState());

		// 有糖果，投币，转摇杆，分发一个糖果
		gumballMachine.insertQuarter();
		System.out.println(gumballMachine.getState());
		gumballMachine.turnCrank();
		System.out.println(gumballMachine.getState());
		gumballMachine.dispense();
		System.out.println(gumballMachine.getState());

		// 初始化机器中放0个糖果
		gumballMachine = new GumballMachine(0);
		System.out.println(gumballMachine.getState());

		// 无糖果，投币，被动退币
		gumballMachine.insertQuarter();
		System.out.println(gumballMachine.getState());
	}

}

abstract class State {
	/**
	 * 投币
	 */
	public abstract void insertQuarter();

	/**
	 * 退币
	 */
	public abstract void ejectQuarter();

	/**
	 * 转动出糖曲轴
	 */
	public abstract void turnCrank();

	/**
	 * 发糖
	 */
	public abstract void dispense();

	/**
	 * 退还硬币
	 */
	protected void returnQuarter() {
		System.out.println("退币……");
	}
}

/**
 * 没有硬币的状态
 */
class NoQuarterState extends State {
	GumballMachine gumballMachine;

	public NoQuarterState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	@Override
	public void insertQuarter() {
		System.out.println("你投入了一个硬币");
		// 转换为有硬币状态
		gumballMachine.setState(gumballMachine.hasQuarterState);
	}

	@Override
	public void ejectQuarter() {
		System.out.println("没有硬币，无法弹出");
	}

	@Override
	public void turnCrank() {
		System.out.println("请先投币");
	}

	@Override
	public void dispense() {
		System.out.println("没有投币，无法发放糖果");
	}
}

/**
 * 投硬币的状态
 */
class HasQuarterState extends State {
	GumballMachine gumballMachine;

	public HasQuarterState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	@Override
	public void insertQuarter() {
		System.out.println("请不要重复投币！");
		returnQuarter();
	}

	@Override
	public void ejectQuarter() {
		returnQuarter();
		gumballMachine.setState(gumballMachine.noQuarterState);
	}

	@Override
	public void turnCrank() {
		System.out.println("转动曲轴，准备发糖");
		gumballMachine.setState(gumballMachine.soldState);
	}

	@Override
	public void dispense() {
		System.out.println("this method don't support");
	}
}

/**
 * 出售的状态
 */
class SoldState extends State {
	GumballMachine gumballMachine;

	public SoldState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	@Override
	public void insertQuarter() {
		System.out.println("已投币，请等待糖果");
		returnQuarter();
	}

	@Override
	public void ejectQuarter() {
		System.out.println("无法退币，正在发放糖果，请等待");
	}

	@Override
	public void turnCrank() {
		System.out.println("已按过曲轴，请等待");
	}

	@Override
	public void dispense() {
		int candyCount = gumballMachine.getCandyCount();
		if (candyCount > 0) {
			System.out.println("分发一颗糖果");
			candyCount--;
			gumballMachine.setCandyCount(candyCount);
			if (candyCount > 0) {
				gumballMachine.setState(gumballMachine.noQuarterState);
				return;
			}
		}

		System.out.println("抱歉，糖果已售尽");
		gumballMachine.setState(gumballMachine.soldOutState);
	}
}

/**
 * 售尽的状态
 */
class SoldOutState extends State {
	GumballMachine gumballMachine;

	public SoldOutState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	@Override
	public void insertQuarter() {
		System.out.println("糖果已经售尽");
		returnQuarter();
	}

	@Override
	public void ejectQuarter() {
		System.out.println("没有投币，无法退币");
	}

	@Override
	public void turnCrank() {
		System.out.println("糖果已经售尽");
	}

	@Override
	public void dispense() {
		System.out.println("糖果已经售尽");
	}
}

class GumballMachine extends State {
	public State noQuarterState = new NoQuarterState(this);
	public State hasQuarterState = new HasQuarterState(this);
	public State soldState = new SoldState(this);
	public State soldOutState = new SoldOutState(this);

	private State state = soldOutState;
	private int candyCount = 0;

	public GumballMachine(int count) {
		this.candyCount = count;
		if (count > 0)
			setState(noQuarterState);
	}

	@Override
	public void insertQuarter() {
		state.insertQuarter();
	}

	@Override
	public void ejectQuarter() {
		state.ejectQuarter();
	}

	@Override
	public void turnCrank() {
		state.turnCrank();
	}

	@Override
	public void dispense() {
		state.dispense();
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public void setCandyCount(int candyCount) {
		this.candyCount = candyCount;
	}

	public int getCandyCount() {
		return candyCount;
	}
}