<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/jsp/common/header.jsp" />
<div class="title">
	<h2>Favorite Parks</h2>
</div>
<div class="favsContainer">
	<ul>
	<c:forEach items="${favs}" var="fav">
		
			<li>${fav.parkName} ${fav.numberOfSurveys} ${fav.parkDescription}</li>
			<c:url value="/img/parks/${fav.imageName}" var="imageUrl"/>
		
		<img class="image" src="${imageUrl}">
			
	</c:forEach>
	</ul>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp"/>