<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login Page</h2>
    <form action="<c:url value='/login' />" method="post">
  <input type="hidden" name="_csrf" value="${_csrf.token}" />  
        <label>Username : </label><input type='text' name='username' />
        <label>Password : </label><input type='password' name='password' />
        <input name="submit" type="submit" value="Sign In"/>
        
    </form>
    <div>
        <c:if test="${not empty error}">
            <p style="color:red;">${error}</p>
        </c:if>
        <c:if test="${not empty message}">
            <p style="color:green;">${message}</p>
        </c:if>
    </div>
</body>
</html>
