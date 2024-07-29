<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Model and View</title>
</head>
<body>
<h1>Display Data using Model And View Only</h1>
	<!-- JSP files is converted into servlet by tomcat -->

	<p>
	Name is ${name}<br/>
	Age is ${age}<br/>
	List of techs:
	
	<!-- One way to print it -->
	<h3>One Way to print using expression</h3>
	<c:forEach var="i" items="${alltechstacks}">
		<h4>${i}</h4>
	</c:forEach>

	<h3>Second Way to print using c:out</h3>
	<!-- Second way to print it -->
	<c:forEach var="i" items="${alltechstacks}">
		<h4><c:out value="${i }"/></h4>
	</c:forEach>	
	</p>
</body>
</html>