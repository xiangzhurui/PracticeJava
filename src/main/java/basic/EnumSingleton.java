package basic;

public enum EnumSingleton {
	INSTANCE;
	public EnumSingleton getInstance() {
		return INSTANCE;
	}
}
