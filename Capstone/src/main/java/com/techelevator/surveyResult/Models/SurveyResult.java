package com.techelevator.surveyResult.Models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SurveyResult {
	
	private int surveyid;
	private String parkCode;
	
	@NotBlank(message="you must provide your email.")
	@Email(message="you must provide a valid email.")
	private String emailAddress;
	
	@NotBlank(message="you must provide a state of residence")
	private String state;
	
	@NotBlank(message="you must provide your activity level")
	private String activityLevel;
	
	
	
	public int getsurveyid() {
		return surveyid;
	}
	public void setsurveyid(int id) {
		this.surveyid = id;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}

}
