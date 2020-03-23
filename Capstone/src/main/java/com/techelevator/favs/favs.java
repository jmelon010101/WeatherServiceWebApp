package com.techelevator.favs;

public class favs {
	private String parkName;
	private int numberOfSurveys;
	private String parkCode;
	private String parkDescription;

	
	public String getParkDescription() {
		return parkDescription;
	}
	public void setParkDescription(String parkDescription) {
		this.parkDescription = parkDescription;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	// put img here 
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public int getNumberOfSurveys() {
		return numberOfSurveys;
	}
	public void setNumberOfSurveys(int numberOfSurveys) {
		this.numberOfSurveys = numberOfSurveys;
	}
	
	public String getImageName() {
		return parkCode.toLowerCase() + ".jpg";
	}
	

}
