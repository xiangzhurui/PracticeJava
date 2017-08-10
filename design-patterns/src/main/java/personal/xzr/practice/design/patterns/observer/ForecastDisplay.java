package personal.xzr.practice.design.patterns.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by XiangZhuRui on 2017/7/10.
 */
public class ForecastDisplay implements Observer {

    private Observable observable;
    private float currentPressure = 22.92f;
    private float lastPressure;

    public ForecastDisplay(Observable observable) {
        this.observable = observable;
        this.observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) o;
            lastPressure = currentPressure;
            currentPressure = weatherData.getPressure();
            display();
        }
    }

    public void display() {
        System.out.println("上次压力：" + lastPressure + ";\n 当前压力：" + currentPressure);
    }
}
