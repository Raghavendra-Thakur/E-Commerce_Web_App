<%@page import="java.util.List"%>
<%@page import="com.Online_Bazar.dao.ProductDao"%>
<%@page import="com.Online_Bazar.modal.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.Online_Bazar.modal.product"%>
<%@page import="com.Online_Bazar.connection.DBConnection"%>
<%@page import="com.Online_Bazar.modal.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);

}

ProductDao pd = new ProductDao(DBConnection.getConnection());
List<product> products = pd.getAllproducts();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cartlist");
if(cart_list != null){
	request.setAttribute("cart_list", cart_list);
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome To OnlineBazar</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
	<%@include file="includes/Navbar.jsp"%>

	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
			<%
			if(!products.isEmpty()){
				for(product p:products){%>
					<div class="col-md-3 my-3" height=800>
					<div class="card w-100" style="width: 18rem; ">
						<img class="card-img-top" src=<%=p.getImg() %> width=400 height=200 alt="Card image cap">
						<div class="card-body">
							<h5 class="card-title"><%=p.getName()%></h5>
							<h6 class="price">Price : RS <%=p.getPrice()%></h6>
							<h6 class="category">category : <%=p.getCategory()%></h6>
							<div class="mt-3 d-flex justify-content-between">
								<a href="addtocart?id=<%=p.getId() %>" class="btn btn-primary">Add To Cart</a>
								<a href="ordernow?quantity=1&id=<%=p.getId() %>" class="btn btn-danger">Buy Now</a>
							</div>
						</div>
					</div>
				</div>
				<%}
			}
			 %>
			
		</div>
	</div>

	<%@include file="includes/Footer.jsp"%>
</body>
</html>