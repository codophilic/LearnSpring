<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"> -->

<link href="${contextPath}/resource/bootstrap.min.css" rel="stylesheet">
</head>
<body>
 <div class="container">
  <h1>Customer Register Form for Business:</h1>
  <div class="card">
   <div class="card-body">
    <form action="register" method="post">

     <div class="form-group row">
      <label class="col-sm-2 col-form-label">First
       Name</label>
      <div class="col-sm-7">
       <input type="text" class="form-control" name="custfirtName"
        placeholder="Enter first name">
      </div>
     </div>

     <div class="form-group row">
      <label class="col-sm-2 col-form-label">Last
       Name</label>
      <div class="col-sm-7">
       <input type="text" class="form-control" name="custlastName"
        placeholder="Enter last name">
      </div>
     </div>

     <div class=" form-group row">
      <label class="col-sm-2 col-form-label">User
       Name</label>
      <div class="col-sm-7">
       <input type="text" class="form-control" name="custuserName"
        placeholder="Enter user name">
      </div>
     </div>

     <div class="form-group row">
      <label class="col-sm-2 col-form-label">Password</label>
      <div class="col-sm-7">
       <input type="password" class="form-control" name="custpassword"
        placeholder="Enter Password">
      </div>
     </div>

     <div class="form-group row">
      <label class="col-sm-2 col-form-label">Address</label>
      <div class="col-sm-7">
       <input type="text" class="form-control" name="custaddress"
        placeholder="Enter Address">
      </div>
     </div>

     <div class="form-group row">
      <label class="col-sm-2 col-form-label">Contact
       No</label>
      <div class="col-sm-7">
       <input type="text" class="form-control" name="custcontact"
        placeholder="Enter Contact Address">
      </div>
     </div>

     <button type="submit" class="btn btn-primary">Submit</button>
    </form>
   </div>
  </div>
 </div>
</body>
</html>