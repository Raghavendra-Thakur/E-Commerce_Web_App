<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.Online_Bazar.dao.ProductDao"%>
<%@page import="com.Online_Bazar.modal.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.Online_Bazar.connection.DBConnection"%>
<%@page import="com.Online_Bazar.modal.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);

}
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cartlist");
List<Cart> cartProduct = null;

if (cart_list != null) {
	ProductDao pd = new ProductDao(DBConnection.getConnection());
	cartProduct = pd.getCartProduct(cart_list);
	double total = pd.getTotalPrice(cart_list);
	request.setAttribute("cart_list", cart_list);
	request.setAttribute("total", total);

}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
<%@include file="includes/header.jsp"%>
<style type="text/css">
.table tbody td {
	vertical-align: middle;
}

.btn-incre, .btn-decre {
	box-shadow: none;
	font-size: 25px;
}
</style>
</head>
<body>
	<%@include file="includes/Navbar.jsp"%>

	<div class="container">
		<div class="d-flex py-3">
			<h3>Total Price : RS ${total > 0?dcf.format(total): 0 }</h3>

			<a class="mx-3 btn btn-primary" href="checkout">Check Out</a>
		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (cart_list != null) {
					for (Cart c : cartProduct) {
				%>
				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td><%=dcf.format(c.getPrice())%></td>
					<td>
						<form action="ordernow" method="post" class="form-inline">
							<input type="hidden" name="id" value="<%=c.getId()%>"
								class="form-input w-50">
							<div class="form-group d-flex justify-content-between w-5-">
								<a class="btn btn-sm btn-decre px-1"
									href="quantityIncreDecre?action=dec&id=<%=c.getId()%>"><i
									class="fas fa-minus-square"></i></a> 
									<input type="text"
									name="quantity" class="form-control w-50"
									value="<%=c.getQuantity()%>" readonly> <a
									class="btn btn-sm btn-incre px-1"
									href="quantityIncreDecre?action=inc&id=<%=c.getId()%>"><i
									class="fas fa-plus-square"></i></a>
							</div>
							<button type="submit" class="btn btn-primary">BUY</button>
						</form>
					</td>
					<td><a class="btn btn-sm btn-danger px-1" href="removefromcart?id=<%= c.getId()%>">Remove</a></td>
				</tr>
				<%
				}
				}
				%>
			</tbody>
		</table>
	</div>

	</div>




	<%@include file="includes/Footer.jsp"%>
</body>
</html>