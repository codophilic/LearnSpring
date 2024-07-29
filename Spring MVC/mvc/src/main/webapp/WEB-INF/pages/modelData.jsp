<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Model data</title>
</head>
<body>
	<h1>Display Data using Model Only</h1>
	<!-- JSP files is converted into servlet by tomcat -->
	<%@ page import="java.util.List" %>
	
	<% 
	String name=(String)request.getAttribute("name");
	Integer age=(Integer)request.getAttribute("age");
	List<String> alltechs=(List<String>) request.getAttribute("alltechstacks");
	%>
	
	<p>
	Name is <%=name %><br/>
	Age is <%=age %><br/>
	List of techs: </br>
	<%
		for(String i : alltechs){
			
			out.println(i); 	%> </br>
	<%

		}
	
	%>
	
	</p>
	
</body>
</html>