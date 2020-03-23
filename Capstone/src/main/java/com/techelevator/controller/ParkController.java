package com.techelevator.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.api.DarkSky;
import com.techelevator.api.RestDarkSkyDao;
import com.techelevator.favs.favs;
import com.techelevator.parkModels.JdbcParkDao;
import com.techelevator.parkModels.Park;
import com.techelevator.surveyResult.Models.ActivityLevels;
import com.techelevator.surveyResult.Models.JDBCSurveyResultDAO;
import com.techelevator.surveyResult.Models.State;
import com.techelevator.surveyResult.Models.SurveyResult;
import com.techelevator.weatherModels.JDBCWeatherDAO;
import com.techelevator.weatherModels.Weather;
import com.techelevator.weatherModels.WeatherDAO;

@Controller
public class ParkController {

	@Autowired
	JdbcParkDao parkDao;
	@Autowired
	JDBCWeatherDAO weatherDao;
	@Autowired
	JDBCSurveyResultDAO surveyDAO;
	@Autowired
	RestDarkSkyDao restDarkSkyDao;

	@GetMapping("/")
	public String displayHomePage(ModelMap mm) {
		mm.put("allParks", parkDao.getAllParks());
		List<Park> parks = parkDao.getAllParks();
		Park park = parks.get(0);
		DarkSky ds = restDarkSkyDao.getDarkSkyForPark(park.getLattitude(), park.getLongitude());
		mm.put("DarkSky", ds);

		return "home";
	}

	@GetMapping("/details")
	public String displayParkDetails(HttpServletRequest request, HttpSession session) {
		String parkCode = request.getParameter("id");
		Park p = parkDao.getParkByParkCode(parkCode);
		request.setAttribute("park", p);
		if (session.getAttribute("weathers") == null) {
			List<Weather> weatherList = new ArrayList<>();
			weatherList = weatherDao.getWeatherByParkCode(parkCode);
			session.setAttribute("weathers", weatherList);	
		} 

		if (request.getParameter("unit").equals("switch")) {
			for (Weather w : (List<Weather>)session.getAttribute("weathers")) {
				w.convertUnits();
			}
		}
		
		return "details";
	}


	@GetMapping("/survey")
	public String displaySurveyPage(ModelMap mm) {
		mm.addAttribute("states", State.values());
		mm.addAttribute("activityLevels", ActivityLevels.values());
		List<Park> parks = new ArrayList<>();
		mm.put("parks", parkDao.getAllParks());
		if (mm.containsAttribute("surveyData") == false) {
			SurveyResult empty = new SurveyResult();
			mm.put("surveyData", empty);
		}
		return "survey";
	}

	@PostMapping("/survey")
	public String processSurvey(@Valid @ModelAttribute SurveyResult survey, BindingResult result, RedirectAttributes ra,
			HttpSession session) {
		if (result.hasErrors()) {
			ra.addFlashAttribute("surveyData", survey);
			ra.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "surveyData", result);
			return "redirect:/survey";
		} else {
			surveyDAO.createSurvey(survey.getParkCode(), survey.getEmailAddress(), survey.getState(),
					survey.getActivityLevel());
			session.setAttribute("surveySessionResults", survey);
			return "redirect:/favorites";
		}
	}

	@GetMapping("/favorites")
	public String processSurvey(ModelMap mm) {
		List<favs> favList = new ArrayList<>();
        favList=(surveyDAO.favoriteParks());		
		mm.put("favs",favList);
		
		
		return "favorites";
	}

}
