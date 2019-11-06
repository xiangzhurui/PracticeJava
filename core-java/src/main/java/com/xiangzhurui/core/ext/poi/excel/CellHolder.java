package com.xiangzhurui.core.ext.poi.excel;

public class CellHolder<T> {
    private T value;

    public CellHolder(T value) {
        super();
        this.value = value;
    }

    public CellHolder() {
        super();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
