package personal.xzr.practice.design.patterns.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 订阅者
 *
 * @author XiangZhuRui
 *
 */
public class DisplayObserver implements Observer {
    private Observable observable;
    private float temperature;
    private float huimidity;

    public DisplayObserver(Observable observable) {
        this.observable = observable;
        this.observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) o;
            this.temperature = weatherData.getTemperature();
            this.huimidity = weatherData.getHumidity();
            display();
        }
    }

    public void display(){
        System.out.println("温度:"+temperature+", \n 湿度:"+huimidity);
    }

}
