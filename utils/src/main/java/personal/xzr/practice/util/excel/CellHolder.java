package personal.xzr.practice.util.excel;

public class CellHolder<T> {
	private T value;

	public void setValue(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public CellHolder(T value) {
		super();
		this.value = value;
	}

	public CellHolder() {
		super();
	}
}
