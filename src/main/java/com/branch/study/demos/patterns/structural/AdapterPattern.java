package com.branch.study.demos.patterns.structural;

/**
 * Adapter：适配器模式
 * Adapter实现用户需要依赖的接口，并聚合Adaptee（或者继承Adaptee）
 * 对Adaptee中方法的输出进行处理，来匹配到用户需要的接口
 * 本例中展示的是对象适配器即通过聚合来实现，类适配器通过继承类来实现，java中继承为单继承所以有局限
 * 用途举例：Java io流中InputStreamReader将InputStream转换成reader
 * 
 * @author Administrator
 */
public class AdapterPattern {

	public static void main(String[] args) {
		// 这里client依赖的Chinese接口，而非ChineseTranslator实现类
		Chinese chinese = new ChineseTranslator(new Japanese());
		chinese.speakChinese();
	}

}

// Target
interface Chinese {
	void speakChinese();
}

// Adapter
class ChineseTranslator implements Chinese {
	private Japanese japanese;

	public ChineseTranslator(Japanese japanese) {
		this.japanese = japanese;
	}

	@Override
	public void speakChinese() {
		japanese.speakJapanese();
		translateToChinese();
	}

	private void translateToChinese() {
		System.out.println("中国翻译说中文");
	}
}

// Adaptee
class Japanese {
	void speakJapanese() {
		System.out.println("日本人说日语");
	}
}