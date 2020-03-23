package com.techelevator.parkModels;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;



@Component
public class JdbcParkDao implements ParkDAO {
	JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcParkDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> allParks = new ArrayList<>();
		String sqlForAllPark = "SELECT parkcode, parkname, state, "
				+ "elevationinfeet, milesoftrail, numberofcampsites," + " climate, yearfounded, annualvisitorcount,"
				+ " inspirationalquote, inspirationalquotesource, "
				+ "parkdescription, entryfee, numberofanimalspecies, acreage, longitude, lattitude " + "FROM park;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlForAllPark);

		while (result.next()) {
			allParks.add(mapRowToPark(result));
		}

		return allParks;
	}

	@Override
	public Park getParkByParkCode(String parkCode) {
		Park park = null;
		String sqlForParkById = "SELECT parkcode, parkname, state, "
				+ "elevationinfeet, milesoftrail, numberofcampsites," + " climate, yearfounded, annualvisitorcount,"
				+ " inspirationalquote, inspirationalquotesource, "
				+ "parkdescription, entryfee, numberofanimalspecies, acreage, longitude, lattitude " + "FROM park " + "WHERE parkcode = ? ;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlForParkById, parkCode);

		if (result.next()) {
			park = mapRowToPark(result);
		}
		return park;
	}

	

	public Park mapRowToPark(SqlRowSet row) {
		Park park = new Park();
		park.setParkCode(row.getString("parkcode"));
		park.setParkName(row.getString("parkname"));
		park.setState(row.getString("state"));
		park.setElevationInFeet(row.getInt("elevationinfeet"));
		park.setMilesOfTrail(row.getDouble("milesoftrail"));
		park.setNumberOfCampsites(row.getInt("numberofcampsites"));
		park.setClimate(row.getString("climate"));
		park.setYearFounded(row.getInt("yearfounded"));
		park.setAnnualVisitorCount(row.getInt("annualvisitorcount"));
		park.setInspirationalQuote(row.getString("inspirationalquote"));
		park.setInspirationalQuoteSource(row.getString("inspirationalquotesource"));
		park.setParkDescription(row.getString("parkdescription"));
		park.setEntryFee(row.getInt("entryfee"));
		park.setNumberOfAnimalSpecies(row.getInt("numberofanimalspecies"));
		park.setAcreage(row.getInt("acreage"));
		park.setLongitude(row.getDouble("longitude"));
		park.setLattitude(row.getDouble("lattitude"));
		return park;
	}

}
