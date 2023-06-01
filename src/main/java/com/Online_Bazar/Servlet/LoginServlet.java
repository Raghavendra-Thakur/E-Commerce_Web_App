package com.Online_Bazar.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Online_Bazar.connection.DBConnection;
import com.Online_Bazar.dao.UserDao;
import com.Online_Bazar.modal.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String email = request.getParameter("login-email"); 
			String password = request.getParameter("login-password");
			
			UserDao uDao = new UserDao(DBConnection.getConnection());
			User user = uDao.userLogin(email, password);
			
			if(user != null) {
				
				request.getSession().setAttribute("auth", user);
				response.sendRedirect("index.jsp");
			} 
			if(user == null) {
				out.println("<script type=\"text/javascript\">"); 
				out.println("alert('Password or username Wrong');");
				out.println("location='login.jsp';"); 
				out.println("</script>"); 
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
