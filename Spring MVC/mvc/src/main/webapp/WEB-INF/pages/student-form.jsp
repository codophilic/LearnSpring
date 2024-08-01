<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
        crossorigin="anonymous">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

    <title>IT Application Form</title>
    <style>
        body {
	        background-image: url('<c:url value="/images/background.jpg" />');
	        background-repeat: no-repeat;
	        background-size: cover;
	        background-position: center;
	        background-attachment: fixed;
        }
        .card {
            border-radius: 15px;
            border: none;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
            background: #cccccc;
        }
        .card-body {
            padding: 2rem;
        }
        .form-control {
            border-radius: 10px;
            border: 1px solid #ced4da;
        }
        .btn-primary {
            border-radius: 10px;
            background-color: #007bff;
            border: none;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .form-group label {
            font-weight: bold;
            color: #333;
        }
        .form-check-label {
            margin-left: 0.5rem;
            color: #333;
        }
        .form-group input[type="text"], .form-group input[type="date"], .form-group select {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 40px;
            margin-bottom: 40px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-body">
                    <h3 class="text-center mb-4">IT Application Form for Students,path variable, adding images, error handling</h3>
                    <form action="register" method="post">
                        <div class="form-group">
                            <label for="stdname">Your Name</label>
                            <input name="stdname" type="text" class="form-control" id="stdname" placeholder="Enter Name">
                        </div>

                        <div class="form-group">
                            <label for="stdrollnum">Your College Roll Number</label>
                            <input name="stdrollnum" type="text" class="form-control" id="stdrollnum" placeholder="Enter Roll Number">
                        </div>

                        <div class="form-group">
                            <label for="dob">Your DOB</label>
                            <input name="stddatedob" type="text" class="form-control" id="dob" placeholder="dd-MM-yyyy">
                        </div>

                        <div class="form-group">
                            <label for="stdsubjects">Select Courses</label>
                            <select name="stdsubjects" class="form-control" id="stdsubjects" multiple>
                                <option>Java</option>
                                <option>Python</option>
                                <option>C++</option>
                                <option>Spring</option>
                                <option>Hibernate</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <span class="mr-3">Select Gender</span>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" id="inlineRadio1" value="male">
                                <label class="form-check-label" for="inlineRadio1">Male</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" id="inlineRadio2" value="female">
                                <label class="form-check-label" for="inlineRadio2">Female</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="stdtype">Select Type</label>
                            <select class="form-control" name="stdtype" id="stdtype">
                                <option value="oldstudent">Old Student</option>
                                <option value="normalstudent">Normal Student</option>
                            </select>
                        </div>

                        <div class="card mt-4">
                            <div class="card-body">
                                <p>Your Address</p>
                                <div class="form-group">
                                    <input name="studentAddress.addressline1" type="text" class="form-control" id="addressline1" placeholder="Address Line 1">
                                </div>
                                <div class="form-group">
                                    <input name="studentAddress.addressline2" type="text" class="form-control" id="addressline2" placeholder="Address Line 2">
                                </div>
                            </div>
                        </div>

                        <div class="text-center mt-4">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery and Bootstrap Bundle (includes Popper) -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script
    src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
    crossorigin="anonymous"></script>

<script>
    $(function() {
        $("#dob").datepicker({
            dateFormat: "dd-MM-yy",
            changeMonth: true,
            changeYear: true,
            yearRange: "-100:+0"
        });
    });
</script>
</body>
</html>
