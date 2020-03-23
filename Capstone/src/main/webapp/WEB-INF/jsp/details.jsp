<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/jsp/common/header.jsp" />
<div>
</div>
<div>
	<p class="detailsParksName">${park.parkName}</p>
	<p class="detailsParkState">${park.state}</p>
	<p class="detailsParksAcerage">acreage: ${park.acreage}</p>
	<p class="detailsParksElevation">elevation: ${park.elevationInFeet} ft.</p>
	<p class="detailsParksMilesOfTrail">trail miles: ${park.milesOfTrail}</p>
	<p class="detailsParksNumOfCampsites">number of campsites: ${park.numberOfCampsites}</p>
	<p class="detailsParksClimate">climate: ${park.climate}</p>
	<p class="detailsParksYearFounded">founded in ${park.yearFounded}.</p>
	<p class="detailsParksAnnVisitors">annual visitors: ${park.annualVisitorCount}</p>
	<p class="detailsParksInspaQuote">${park.inspirationalQuote}</p>
	<p class="detailsParksSource">${park.inspirationalQuoteSource}</p>
	<p class="detailsParksDescription">${park.parkDescription}</p>
	<p class="detailsParksEntryFee">entery fee: $ ${park.entryFee}</p>
	<p class="detailsParksAnimalSpecies">number of animal species: ${park.numberOfAnimalSpecies}</p>

</div>

<div id="weatherContainer">
<Form action="POST">
<a class="button" href="details?id=${park.parkCode}&unit=switch"><span>Switch Temperature Units</span></a>
</Form>

<p id="today"> Today : </p>

	<c:forEach items="${weathers}" var="weather">
		<div class="weatherItem">
	
		<div>
		<c:url value="/img/weather/${weather.forecast}.png" var="imageUrl"/>
			<img class="detailImage" src="${imageUrl}">
		</div>
			<ul class="forcast">
				<li>high: ${weather.high} ${weather.unit}</li>
				<li>low: ${weather.low} ${weather.unit}</li>
				<li>forecast: ${weather.forecast}</li>
			</ul>
			
			<p>${weather.recommendations}</p>
			
		</div>
		
	</c:forEach>

</div>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />