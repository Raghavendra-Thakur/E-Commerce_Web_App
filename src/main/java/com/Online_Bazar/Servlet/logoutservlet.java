package com.Online_Bazar.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class logoutservlet
 */
@WebServlet("/logout")
public class logoutservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getSession().getAttribute("auth")!= null) {
				request.getSession().removeAttribute("auth");
				response.sendRedirect("login.jsp");
			}
			else {
				response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
}
