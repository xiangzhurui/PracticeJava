package personal.xzr.practice.design.patterns.singleton;

/**
 * 懒汉式，延迟加载。
 * 线程不安全，为题在于 if (single == null) 这一行
 */
public class Singleton0 {
	private static Singleton0 single;

	private Singleton0() {
	}

	public static Singleton0 getInstance() {
		if (single == null) {
			single = new Singleton0();
		}
		return single;
	}
}
