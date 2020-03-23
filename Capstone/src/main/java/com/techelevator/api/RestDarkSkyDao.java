package com.techelevator.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.taglibs.standard.tag.common.core.CatchTag;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestDarkSkyDao implements DarkSkyDao{
	
	
	private static class DarkSkyDataPoint {
		public String summery;
		public String icon;
		public String precipType;
		
		//public double tempLow;
	}
	
	
	/*
	 * private static class DarkSkyDataBlock { public List<DarkSkyDataPoint> data; }
	 * 
	 * private static final class DarkSkyForecast { DarkSkyDataBlock daily; }
	 * 
	 * 
	 * public List<WeatherForecast> getDailyWeather(){ String coordinates = "";
	 * DarkSkyForecast forecast = RestTemplate.getForObject(BASE_URL + coordinates,
	 * DarkSkyForecast.class); List<WeatherForecast> results = new ArrayList<>();
	 * for(DarkSkyDataPoint dp: forecast.daily.data) { WeatherForcast w = new
	 * WeatherForecast(); w.setHighTemp(dp.details.get("temperatureHigh"));
	 * w.setLowTemp(dp.tempLow); results.add(w); } return results; }
	 */

	
	private static class SingleDarkSkyApiResponse {
		public DarkSky ds;
	}
	
	
	
	private static final String BASE_URL = "https://api.darksky.net/forecast/99f293764981a195d6dc27ecddaf60bb/";
	
	private RestTemplate restTemp = new RestTemplate();
	
	@Override
	public DarkSky getDarkSkyForPark(double lattitude, double longitude) {
		
		String url = BASE_URL + lattitude + "," + longitude;
		
		SingleDarkSkyApiResponse response;
		try {
			
			response = restTemp.getForObject(url, SingleDarkSkyApiResponse.class);
			
		} catch(HttpClientErrorException e) {
			response = new SingleDarkSkyApiResponse();
		}
		
		return response.ds;
		
	}
	
	

	
	
}
