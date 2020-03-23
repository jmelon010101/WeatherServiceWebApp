package com.techelevator.surveyResult.Models;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.favs.favs;
import com.techelevator.parkModels.Park;

@Component
public class JDBCSurveyResultDAO implements SurveyResultDAO {
	private JdbcTemplate JdbcTemplate;

	@Autowired
	public JDBCSurveyResultDAO(DataSource dataSource) {
		JdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void createSurvey(String parkCode, String emailAddress, String state, String activityLevel) {
		String sqlCreateSurveyString = "INSERT INTO survey_result(parkcode,emailaddress,state,activitylevel)"
				+ "VALUES (?,?,?,?) ;";
		JdbcTemplate.update(sqlCreateSurveyString, parkCode, emailAddress, state, activityLevel);

	}

	@Override
	public List<SurveyResult> getSurveysByParkCode(String parkCode) {
		List<SurveyResult> surveyList = new ArrayList<SurveyResult>();
		String sqlListByCode = "SELECT parkcode,emailaddress, state,activitylevel FROM survey_result WHERE parkcode = ? ;";
		SqlRowSet results = JdbcTemplate.queryForRowSet(sqlListByCode, parkCode);
		while (results.next()) {
			SurveyResult s;
			s = mapSurveyResult(results);
			surveyList.add(s);
		}

		return surveyList;
	}

	public List<favs> favoriteParks() {
		List<favs> favParks = new ArrayList<>();
		String sqlFavParkString = "SELECT park.parkdescription, survey_result.parkcode, park.parkname, COUNT (survey_result.parkcode) FROM survey_result "
				+ "JOIN park ON park.parkcode = survey_result.parkcode "
				+ "GROUP BY survey_result.parkcode, parkname, park.parkdescription ORDER BY count DESC, survey_result.parkcode;";
		SqlRowSet result = JdbcTemplate.queryForRowSet(sqlFavParkString);
		while (result.next()) {
			favs favs = new favs();
			favs.setNumberOfSurveys(result.getInt("count"));
			favs.setParkName(result.getString("parkname"));
			favs.setParkCode(result.getString("parkcode"));
			favs.setParkDescription(result.getString("parkdescription"));
			favParks.add(favs);
		}
		return favParks;
	}

	private SurveyResult mapSurveyResult(SqlRowSet row) {
		SurveyResult survey = new SurveyResult();
		survey.setParkCode(row.getString("parkcode"));
		survey.setState(row.getString("state"));
		survey.setEmailAddress(row.getString("emailaddress"));
		survey.setActivityLevel(row.getString("activitylevel"));
		return survey;
	}

}
