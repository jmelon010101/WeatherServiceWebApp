package com.techelevator.parkModels;

import java.util.List;

public interface ParkDAO {
	
	public List<Park> getAllParks();
	
	public Park getParkByParkCode(String parkCode);
	
	

}
