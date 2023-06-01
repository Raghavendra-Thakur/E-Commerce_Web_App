package com.Online_Bazar.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Online_Bazar.connection.DBConnection;
import com.Online_Bazar.dao.AdminDao;
import com.Online_Bazar.modal.AdminModal;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/adminlogin")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("AdminLogin.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html;charset=UTF-8");

		try (PrintWriter out = response.getWriter()){
			
			String username = request.getParameter("login-username");
			String password = request.getParameter("login-password");
			
			AdminDao aDao = new AdminDao(DBConnection.getConnection());
			
			AdminModal admin = aDao.authAdmin(username, password); 
			
			
			
			if(admin != null) {
				out.print(admin);
				request.getSession().setAttribute("auth", admin);
				response.sendRedirect("addProduct.jsp");
			}
			
			if(admin == null) {
				out.println("<script type=\"text/javascript\">"); 
				out.println("alert('Password or username Wrong');");
				out.println("location='AdminLogin.jsp';"); 
				out.println("</script>"); 
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
