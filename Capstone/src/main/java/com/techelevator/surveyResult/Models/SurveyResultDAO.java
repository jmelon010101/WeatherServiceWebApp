package com.techelevator.surveyResult.Models;

import java.util.List;

public interface SurveyResultDAO {
	
	public void createSurvey( String parkCode, String emailAddress, String state, String activityLevel);
	
	public List<SurveyResult> getSurveysByParkCode(String parkCode);
}
