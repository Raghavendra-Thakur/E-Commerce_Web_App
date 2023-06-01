<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.Online_Bazar.modal.User"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	response.sendRedirect("index.jsp");
	
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
<%@include file="includes/Navbar.jsp"%>
	<div>
	<img src="Images/online_Bazar_logo.png"
			class="rounded mx-auto  d-block" style="height:360px; width:350px;" alt="logo">
	</div>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center ">User Login</div>
			<div class="card-body">
				<form action="user-login" method="post">
					<div class="form-group">
						<label>Email Address</label> <input type="email"
							class="form-control" name="login-email"
							placeholder="Enter Your Email" requird>
					</div>
					<div class="form-group">
						<label>Password</label> <input type="password"
							class="form-control" name="login-password"
							placeholder="Enter Your Password" requird>
					</div>
					<div class="text-center">
						<button type="submit" class="btn-primary">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>





	<%@include file="includes/Footer.jsp"%>
</body>
</html>