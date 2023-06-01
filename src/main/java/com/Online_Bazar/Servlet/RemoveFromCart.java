package com.Online_Bazar.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Online_Bazar.modal.Cart;

/**
 * Servlet implementation class RemoveFromCart
 */
@WebServlet("/removefromcart")
public class RemoveFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try (PrintWriter out = response.getWriter()){
		String id =  request.getParameter("id");
		
		if(id != null) {
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cartlist");
			
			if(cart_list != null) {
				for(Cart cart : cart_list) {
					if(cart.getId() == Integer.parseInt(id)) {
						cart_list.remove(cart_list.indexOf(cart));
						break;
					}
					
				}
				response.sendRedirect("cart.jsp");
			}
			
		}else {
			response.sendRedirect("cart.jsp");
		}
		
		
	}
	
	}

	
}
