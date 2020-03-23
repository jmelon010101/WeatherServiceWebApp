<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html>
<head>

<title>National Park</title>
<c:url value="css/stylez.css" var="csshref"/>
<link rel="stylesheet" href="${csshref}"/>
</head>
<body>
	<div class="topBar">
		<p id="logoName">National Park</p>
<!-- 		<img id="logo" src="img/logo.png"/> -->
		<ul>
			<li><a id="homeButton" href="<c:url value="/"/>">Home</a></li>
			<li><a id="surveyButton" href="<c:url value="/survey"/>">survey</a></li>
			
		</ul>
	</div>