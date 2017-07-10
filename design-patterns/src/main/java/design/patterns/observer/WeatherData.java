package design.patterns.observer;

import java.util.Observable;

/**
 * 主题（发布者）
 * 使用JDK的内置的观察者模式
 *
 * @author XiangZhuRui
 */
public class WeatherData extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    /**
     * 主题状态变更方法
     */
    public void changeStatus() {
        setChanged();
        notifyObservers();
    }

    /**
     * 测试方法
     *
     * @param temperature 温度值
     * @param humidity    湿度值
     * @param pressure    气压值
     */
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        changeStatus();
    }
}
