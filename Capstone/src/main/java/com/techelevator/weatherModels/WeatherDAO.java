package com.techelevator.weatherModels;

import java.util.List;

public interface WeatherDAO {
	
	public List<Weather> getWeatherByParkCode(String parkCode);
	
}
