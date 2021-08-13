<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Retail@Product</title>
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
        html {
            height: 100%;
            margin: 0;
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-image: url("https://images.pexels.com/photos/953864/pexels-photo-953864.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
            background-size: 100% 100%;
        }
        .container {
            position: absolute;
            top: 50%;
            left: 85%;
            
            transform: translate(-50%, -50%);
            background: white;
            opacity: 0.95;
            border-radius: 5px;
            box-shadow: 10px 10px 15px rgba(0,0,0,0.2);
            width: fit-content;
        }
        button[type="submit"]
        {
            width: 100%;
            border: 0px;
            padding: 10px 10px;
            border-radius: 25px;
            background: #2691d9;
            font-size: 18px;
            color:#FFFFFF;
            font-weight: 700;
            cursor: pointer;
            outline: none;
        }
        .signup_link
        {
            text-align: center;
            font-size: 16px;
        }
        .signup_link a
        {
            color: #0400fc;
            text-decoration: none;
        }
        .login
        {
            text-align: center;
            padding: 20px 0;
            border-bottom: 1px solid black;
        }
        .lab
        {
            font-weight: bold;
        }
       .login-button{
        margin-bottom: 10px;
       }
       .loginData{
        background-color: white;
        min-width: 30rem;
        max-width: 40rem;
       }
       .whiteColor{
         color:black;
       }
        </style>
    </head>
    <body>
        <div class="container  loginData">
            <div class="row">
                <div class="col-md-12" >
                    <h1 class="login whiteColor">Login</h1>
                    <form:form method="post" action="/authenticate" modelAttribute="login">
                    <div class="form-row">
                        <div class="form-group">
                            <form:label class="labe whiteColor" path="username" placeholder="username">Username</form:label>
                            <form:input type="text" class="form-control" path="username" />
                        </div>
                        <div class="form-group">
                            <form:label class="labe whiteColor" path="password" placeholder="password">Password</form:label>
                            <form:input type="password" class="form-control" id="password" path="password" />
                        </div>
                        <div class="login-button">
                            <button type="submit" >Login</button>
                        </div>
			            <div class="form-group text-danger">${error} </div>
                    </div>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>