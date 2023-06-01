package com.Online_Bazar.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Online_Bazar.connection.DBConnection;
import com.Online_Bazar.dao.ProductDao;
import com.Online_Bazar.modal.AdminModal;
import com.Online_Bazar.modal.product;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/insertpro")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try (PrintWriter out = response.getWriter()){
		
		
		
		
		AdminModal admin = (AdminModal) request.getSession().getAttribute("auth");
		
		if(admin != null) {
			
			String name = request.getParameter("name");
			String category = request.getParameter("category");
			String image = request.getParameter("image");
			double price = Double.parseDouble(request.getParameter("price"));
		
			
			ProductDao pDao = new ProductDao(DBConnection.getConnection());
			product pro = new product();
		
			pro.setName(name);
			pro.setPrice(price);
			pro.setCategory(category);
			pro.setImg(image);
		
		

		
		boolean result = pDao.insertProduct(pro);
		System.out.println(result);
		if(result) {
			response.sendRedirect("addProduct.jsp");
		}else {
			response.sendRedirect("addProduct.jsp");
		}
		
		}else {
			response.sendRedirect("AdminLogin.jsp");
		}
		
		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
