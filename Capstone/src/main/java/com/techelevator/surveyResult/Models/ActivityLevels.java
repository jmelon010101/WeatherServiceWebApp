package com.techelevator.surveyResult.Models;

public enum ActivityLevels {

	Inactive("inactive"), Sedentary("sedentary"), Active("active"), ExtremelyActive("extremelyActive");

	private String activityLevel;

	private ActivityLevels(String activityLevel) {
		this.activityLevel = activityLevel;
	}

	public String getActivityLevel() {
		return this.activityLevel;
	}

}
