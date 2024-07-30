<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ar" dir="rtl">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.rtl.min.css" integrity="sha384-dpuaG1suU0eT09tx5plTaGMLBsfDLzUCCUXOY2j/LSvXYuG6Bqs43ALlhIqAJVRb" crossorigin="anonymous">

  </head>
  <body>
	
	<h2 class="text-center">${randomMsg }</h2>
	<div class="container mt-5 align-items-center">

	<h3 class="text-center">Registration Form using Model Attribute</h3>
	<!-- 
		Due to action parameter view will pass on the request to controller and using the action value processing-details
		controller will call the respected handler method.
		Making the transmission secure using on of the HTTPs method
	 -->	
	<form action="processing-details-via-modelattribute" method="post">
		<div class="form-group" align="left">
		  <label for="emailAddressInput" >Email address</label>
		  <input name="fieldEmail" type="email" id="emailAddressInput" class="form-control" aria-describedby="emailHelp" placeholder="name@example.com">
		 
		</div>
		<div class="form-group" align="left">
		  <label for="userNameInput" >User name</label>
		  <input name="fieldUserName" type="text" id="userNameInput" class="form-control" aria-describedby="emailHelp">
		</div>
		<div class="form-group" align="left">
		  <label for="userPassIput" >User Password</label>
		  <input name="fieldPassword" type="password" id="userPassIput" class="form-control" aria-describedby="emailHelp">
		</div>
		<!-- Optional field age -->
		<div class="form-group" align="left">
		  <label>Security Code</label>
		  <input name="fieldSecurityCode" type="text" id="code" class="form-control" aria-describedby="emailHelp">
		</div>
		<div class="form-group" align="center">
			
			<button type="submit" class="btn btn-primary mt-5">Login</button>
		</div> 
	  </form>
	</div>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    
  </body>
</html>