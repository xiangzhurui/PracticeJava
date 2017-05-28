package design.patterns.singleton;

public enum EnumSingleton {
    INSTANCE;
    public EnumSingleton getInstance() {
        return INSTANCE;
    }
}
