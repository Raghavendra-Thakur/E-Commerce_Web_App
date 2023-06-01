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
				
						<%if(auth != null ){ %>
						<li class="nav-item"><a class="nav-link " href="logoutadmin">Logout</a>
						<%}else{ %>
						<li class="nav-item"><a class="nav-link " href="AdminLogin.jsp">Admin Login</a>
						</li>
						<%} %>
					
					
					
				</ul>
			</div>
		</div>
	</nav>