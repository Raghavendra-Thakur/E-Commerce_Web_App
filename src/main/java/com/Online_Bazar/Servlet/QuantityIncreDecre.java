package com.Online_Bazar.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Online_Bazar.connection.DBConnection;
import com.Online_Bazar.dao.UserDao;
import com.Online_Bazar.modal.Cart;
import com.Online_Bazar.modal.User;


@WebServlet("/quantityIncreDecre")
public class QuantityIncreDecre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String action = request.getParameter("action"); 
			int id = Integer.parseInt(request.getParameter("id"));
			
		ArrayList<Cart> cart_list	= (ArrayList<Cart>) request.getSession().getAttribute("cartlist");
		if(action!= null && id >= 1) {
			if(action.equals("inc")) {
				for(Cart cart : cart_list) {
					if(cart.getId() == id) {
						int quantity = cart.getQuantity();
						quantity++;
						cart.setQuantity(quantity);
						response.sendRedirect("cart.jsp");
					}
					
				}
			}
			if(action.equals("dec")) {
				for(Cart cart : cart_list) {
					if(cart.getId() == id && cart.getQuantity() > 1) {
						int quantity = cart.getQuantity();
						quantity--;
						cart.setQuantity(quantity);
						response.sendRedirect("cart.jsp");
					}
					
				}
			}
		}else {
			response.sendRedirect("cart.jsp");
		}
			
		}
	
	}

	

}
