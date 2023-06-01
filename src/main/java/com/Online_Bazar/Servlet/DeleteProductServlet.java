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
import com.Online_Bazar.dao.ProductDao;
import com.Online_Bazar.modal.AdminModal;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/deletepro")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			AdminModal admin = (AdminModal) request.getSession().getAttribute("auth");
			
			if(admin != null) {
				int id = Integer.parseInt(request.getParameter("id_p"));
				
				ProductDao pDao = new ProductDao(DBConnection.getConnection());
				
				boolean result = pDao.deleteProduct(id);
				
				if(result) {
					response.sendRedirect("addProduct.jsp");
				}else {
					out.print("<h1>Product Id not Found <a href='addProduct.jsp'> return to product page </a></h>");
				}
				
				
			}else {
				response.sendRedirect("AdminLogin.jsp");
			}
			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
