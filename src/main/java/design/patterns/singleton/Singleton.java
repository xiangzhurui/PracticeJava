package design.patterns.singleton;

public class Singleton {
	private static Singleton single = null;

	private Singleton() {
	}

	public synchronized static Singleton getInstance() {
		if (single == null) {
			single = new Singleton();
		}
		return single;
	}
}
