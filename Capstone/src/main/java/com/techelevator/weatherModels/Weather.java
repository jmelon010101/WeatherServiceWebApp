package com.techelevator.weatherModels;

import java.util.ArrayList;
import java.util.List;

import org.apache.taglibs.standard.tag.common.xml.IfTag;

public class Weather {
	
	
	private String parkCode;
	private int fiveDayForecastValue;
	private int low;
	private int high;
	private String forecast;
	private boolean isCelcius;
	private String unit="F";
	
	public boolean isCelcius() {
		return isCelcius;
	}

	public void setCelcius(boolean isCelcius) {
		this.isCelcius = isCelcius;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRecommendations() {
		int lowTemp = low;
		int highTemp = high;
		if (isCelcius) {
			//convert to fahrenheit
			lowTemp = (lowTemp * 9 / 5) + 32;
			highTemp = (highTemp * 9 / 5) + 32;
		}
	
		String result = "";
		if (forecast.equals("snow")) {
			result += "*Pack snowshoes \n";
		} else if (forecast.equals("rain")) {
			result += "*Pack raingear and wear waterproof shoes \n";
		} else if (forecast.equals("thunderstorms")) {
			result += "*Seek shelter and avoid hiking on exposed ridges \n";
		} else if (forecast.equals("sun")) {
			result += "*Pack sunblock \n";
		}
		if (lowTemp > 75) {
			result += "*Bring an extra gallon of water \n";
		} 
		if (highTemp - lowTemp > 20) {
			result += "*Wear breathable layers \n";
		}
		if (lowTemp < 20) {
			result += "*WARNING: Fridged Temperatures! \n";
		}
		return result;
	}
	
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public int getFiveDayForecastValue() {
		return fiveDayForecastValue;
	}
	public void setFiveDayForecastValue(int fiveDayForecastValue) {
		this.fiveDayForecastValue = fiveDayForecastValue;
	}
	public int getLow() {
		return low;
	}
	public void setLow(int low) {
		this.low = low;
	}
	public int getHigh() {
		return high;
	}
	public void setHigh(int high) {
		this.high = high;
	}
	public String getForecast() {
		if (forecast.contains(" ")) {
			int space=forecast.indexOf(" ");
			char[] forecastChar = forecast.toCharArray();
			forecastChar[space+1] = Character.toUpperCase(forecastChar[space+1]); 
			String newForecast = "";
			for (char ch : forecastChar) {
				newForecast += ch;
			}
			newForecast = newForecast.replace(" ", "");
			
			return newForecast;			
		}
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	
	public void convertToCelcius() {
		
		this.high = ((high - 32) * 5 / 9);
		this.low = ((low - 32) * 5 / 9);
	}
	
	public void convertToFarenheit() {
		
		this.low = (low * 9 / 5) + 32;
		this.high = (high * 9 / 5) + 32;
	}
	public void convertUnits() {
		if(isCelcius) {
			System.out.println("converting to f");
			isCelcius=false;
			convertToFarenheit();
			unit="F";
		}else if (!isCelcius){
			System.out.println("converting to c");
			isCelcius=true;
			convertToCelcius();
			unit="C";
		}
	}
}
