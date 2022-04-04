package com.branch.study.demos.patterns.structural;

/**
 * Adapter实现用户需要的接口，并关联Adaptee（或者继承Adaptee）
 * 对Adaptee中方法的输出进行处理，来匹配到用户需要的接口
 * 
 * @author Administrator
 */
public class AdapterPattern {

	public static void main(String[] args) {
		ChineseTranslator chineseTranslator = new ChineseTranslator(new Japanese());
		chineseTranslator.speakChinese();
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