	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container">

			<img src="Images/online_Bazar_logo.jpg" width="40" height="40"
				class="rounded mx-auto pr-2 d-block" alt="logo"> <a
				class="navbar-brand" href="#">Online Bazar</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item "><a class="nav-link" href="index.jsp">Home
							<span class="sr-only">(current)</span>
					</a></li>
				
					<% 
					if(auth != null){%>
						<li class="nav-item"><a class="nav-link" href="cart.jsp">Cart<span class="badge badge-danger">${cart_list.size()}</span></a></li>
						<li class="nav-item"><a class="nav-link " href="orders.jsp">Orders</a>
						</li>
						<li class="nav-item"><a class="nav-link " href="logout">Logout</a>
						</li>	
					<%}else{%>
						<li class="nav-item"><a class="nav-link " href="login.jsp">Login</a>
						</li>
					<%}
					%>	
					<li class="nav-item"><a class="nav-link " href="AdminLogin.jsp">Admin Login</a>
						</li>
					
				</ul>
			</div>
		</div>
	</nav>
