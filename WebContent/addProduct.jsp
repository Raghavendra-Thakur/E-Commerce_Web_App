<%@page import="java.text.DecimalFormat"%>
<%@page import="com.Online_Bazar.modal.product"%>
<%@page import="java.util.List"%>
<%@page import="com.Online_Bazar.connection.DBConnection"%>
<%@page import="com.Online_Bazar.dao.ProductDao"%>
<%@page import="com.Online_Bazar.modal.AdminModal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
AdminModal auth = (AdminModal) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}

ProductDao pdao = new ProductDao(DBConnection.getConnection());

List<product> pro = pdao.getAllproducts();
if (pro != null) {
	request.setAttribute("pro", pro);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OnlineBazar</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
	<%@include file="includes/adminnav.jsp"%>
	<div class="container py-5">
		<div class="row">
			<div
				class="p-5 col-md border border-secondary d-flex flex-column   align-items-center  ">
				<h3 class="p-2">Add New Product</h3>
				<div>
					<form action="insertpro" method="post">
						<div class="form-row">

							<div class="form-group">
								<label for="inputAddress">Product Name</label> <input
									type="text" class="form-control" name="name">
							</div>
							<div class="form-group">
								<label for="inputAddress2">Price</label> <input type="text"
									class="form-control" name="price">
							</div>
							<div class="form-group col-md-4">
								<label for="inputState">Category</label> 
								<select id="inputState"
									name="category" class="form-control">
									<option selected>Choose Category</option>
									<option>Mens</option>
									<option>Womens</option>
									<option>Kids</option>
									<option>Electronic</option>
									<option>HouseHolds</option>
								</select>
							</div>
							<div class="form-group">
								<label for="inputAddress2">Image</label> <input type="text"
									class="form-control" name="image">
							</div>
						</div>
				
				<button type="submit" class="btn btn-primary">Add Product</button>
				</form>
				<form action="deletepro" method="post">
						<div class="form-row ">

							<div class="form-group">
								<label for="inputAddress2" class="pt-5">Product ID To Delete</label> <input type="text"
									class="form-control" name="id_p">
							</div>
							
						</div>
				
				<button type="submit" class="btn btn-primary">Delete Product</button>
				</form>
			</div>
		</div>
		<div
			class=" p-5  border border-secondary d-flex flex-column  align-items-center ">
			<h3 class="p-2">Product List</h3>
			<table class="table table-light table-bordered">
				<thead>
					<tr>
						<th scope="col">ProductId</th>
						<th scope="col">Name</th>
						<th scope="col">Category</th>
						<th scope="col">Price</th>
						<th scope="col">Image</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (product p : pro) {
					%>
					<tr>
						<th scope="col"><%=p.getId()%></th>
						<th scope="col"><%=p.getName()%></th>
						<th scope="col"><%=p.getCategory()%></th>
						<th scope="col"><%=p.getPrice()%></th>
						<th scope="col"><img alt="ProductImg"
							style="height: 60px; widht: 60px;" src=<%=p.getImg()%>></th>
					</tr>
					<%
					}
					%>

				</tbody>

			</table>
		</div>
	</div>
	</div>
	<%@include file="includes/Footer.jsp"%>
</body>
</html>