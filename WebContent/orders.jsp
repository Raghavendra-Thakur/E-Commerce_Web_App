<%@page import="com.Online_Bazar.modal.Order"%>
<%@page import="java.util.List"%>
<%@page import="com.Online_Bazar.dao.OrderDao"%>
<%@page import="com.Online_Bazar.connection.DBConnection" %>
<%@page import="com.Online_Bazar.modal.User" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.Online_Bazar.modal.Cart"%>
<%@page import="java.text.DecimalFormat"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
	User auth = (User) request.getSession().getAttribute("auth"); 
	List<Order> orders = null;
	if(auth != null){
		request.setAttribute("auth", auth);
		OrderDao OrderDao = new OrderDao(DBConnection.getConnection());
				orders =	OrderDao.userOrder(auth.getId());
		
	}else{
		response.sendRedirect("login.jsp");
	}
	ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cartlist");
	if(cart_list != null){
		request.setAttribute("cart_list", cart_list);
	}
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Orders</title>
<%@include file="includes/header.jsp" %>
</head>
<body>
<%@include file="includes/Navbar.jsp"%>
	
	<div class="container">
		<div class="card-header my-3">All Orders</div>
		
			<table class="table table-light" >
				<thead>
					<tr>
						<th scope="col"> Date </th>
						<th scope="col"> Name </th>
						<th scope="col"> Category </th>
						<th scope="col"> Quantity </th>
						<th scope="col"> Price </th>
						<th scope="col"> Cancel </th>
					</tr>
				</thead>	
				<tbody>
				<%
				if(orders != null){
					for(Order o:orders){%>
					<tr>
						<th><%=o.getDate() %></th>
						<th><%=o.getName() %></th>
						<th><%=o.getCategory() %></th>
						<th><%=o.getQuantity() %></th>
						<th><%=o.getPrice() %></th>
						<th><a class="btn btn-sm btn-danger" href="cancelorder?id=<%=o.getOrderId()%>">Cancel</a></th>
					</tr>
					<%}} %>
				
				</tbody>
			</table>
		
		
	</div>
	
	
	
	
<%@include file="includes/Footer.jsp" %>	
</body>
</html>