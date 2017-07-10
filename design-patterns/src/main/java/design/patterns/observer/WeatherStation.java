package design.patterns.observer;

/**
 * Created by XiangZhuRui on 2017/7/10.
 */
public class WeatherStation {

    public static void main(String[] args){
        WeatherData weatherData = new WeatherData();
        DisplayObserver displayObserver = new DisplayObserver(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        weatherData.setMeasurements(80,65,34.5F);

    }
}
