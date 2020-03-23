<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/jsp/common/header.jsp" />


<div>
${DarkSky.daily}
</div>

<c:forEach items="${allParks}" var="park">
	<div class="home">
	<c:url value="/img/parks/${park.imageName}" var="imageUrl"/>
		<a href="details?id=${park.parkCode}&unit=f"><img class="image" src="${imageUrl}"></a>
		<p class="parkNames">${park.parkName}</p>
		<p class="parkStates">${park.state}</p>
		<p class="parkDescriptions">${park.parkDescription}</p>
	</div>
	
</c:forEach>



<c:import url="/WEB-INF/jsp/common/footer.jsp"/>
