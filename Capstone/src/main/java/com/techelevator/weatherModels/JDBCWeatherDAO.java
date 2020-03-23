package com.techelevator.weatherModels;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCWeatherDAO implements WeatherDAO{
	private JdbcTemplate JdbcTemplate;
	
	@Autowired
	 public JDBCWeatherDAO(DataSource dataSource) {
				JdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Weather> getWeatherByParkCode(String parkCode) {
		
		List<Weather> weatherList = new ArrayList<Weather>();
		
		String sqlweatherByParkCode = "SELECT parkcode, fivedayforecastvalue, low, high, forecast "
				+ "FROM weather WHERE parkcode = ?;";
		SqlRowSet result = JdbcTemplate.queryForRowSet(sqlweatherByParkCode, parkCode);
		
		while(result.next()) {
			Weather w = mapWeather(result);
			weatherList.add(w);
		}
		
		return weatherList;
	}
	
	public Weather mapWeather(SqlRowSet row) {
		
		Weather w = new Weather();
		
		w.setParkCode(row.getString("parkcode"));
		w.setFiveDayForecastValue(row.getInt("fivedayforecastvalue"));
		w.setLow(row.getInt("low"));
		w.setHigh(row.getInt("high"));
		w.setForecast(row.getString("forecast"));
		
		return w;
	}
	
	
}
