package com.apress.springrecipes.weather;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherServiceImpl implements WeatherService {

    public List<TemperatureInfo> getTemperatures(String city, List<Date> dates) {
        List<TemperatureInfo> temperatures = new ArrayList<>();
        for (Date date : dates) {
            temperatures.add(new TemperatureInfo(city, date, 5.0, 10.0, 8.0));
        }
        return temperatures;
    }
}
