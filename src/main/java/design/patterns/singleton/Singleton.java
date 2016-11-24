package design.patterns.singleton;

public class Singleton {
	private Singleton() {
	}
	private static Singleton single = null;

	public synchronized static Singleton getInstance() {
		if (single == null) {
			single = new Singleton();
		}
		return single;
	}
}
