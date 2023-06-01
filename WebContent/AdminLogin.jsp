<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.Online_Bazar.modal.AdminModal"%>
<%
AdminModal auth = (AdminModal) request.getSession().getAttribute("AdminAuth");
if (auth != null) {
	response.sendRedirect("addProduct.jsp");
	
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
		<div class="card w-50 mx-auto">
			<div class="card-header text-center ">Admin Login</div>
			<div class="card-body">
				<form action="adminlogin" method="post">
					<div class="form-group">
						<label>Admin Username</label> <input type="text"
							class="form-control" name="login-username"
							placeholder="Enter Your Username" requird>
					</div>
					<div class="form-group">
						<label>Admin Password</label> <input type="password"
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