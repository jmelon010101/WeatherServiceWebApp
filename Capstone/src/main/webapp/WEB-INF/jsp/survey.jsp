<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>`
<c:import url="/WEB-INF/jsp/common/header.jsp" />



<c:url var="newSurveyUrl" value="/survey"/>
	<form:form id="survey" action="${newSurveyUrl}" method="POST" modelAttribute="surveyData">
	
		<div>
		<label for="parkCode">Favorite Park</label>
			<select id="parkCode" name="parkCode" path="parkCode" required="required">
				<c:forEach items="${parks}" var="park">
					<option value="${park.parkCode}">${park.parkName}</option>
				</c:forEach>
			</select>
			<form:errors path="parkCode" class="error"/>			
		</div>
		<div>
			<label for="emailAddress">Email</label>
			<form:input id="email" name="emailAddress"  path="emailAddress" required="required"/>	
			<form:errors path="emailAddress" class="error"/>			
		</div>
		<div>
		<label for="state">State of Residence</label>
			<select id="state" name="state" path="state" required="required">
				<c:forEach items="${states}" var="state">
					<option value="${state}">${state.statusCode}</option>
				</c:forEach>
			</select>
			<form:errors path="state" class="error"/>			
		</div>
		<div>
			<label for="activityLevel">Activity Level</label>
				<select id="activityLevel" name="activityLevel" type="text" path="activityLevel" required="required">
					<c:forEach items="${activityLevels}" var="activityLevel">
						<option value="${activityLevel}">${activityLevel.activityLevel}</option>
					</c:forEach>
				</select>
			<form:errors path="activityLevel" class="error"/>			
		</div>
	<input type="submit" value="submit"/>
</form:form>


<c:import url="/WEB-INF/jsp/common/footer.jsp"/>