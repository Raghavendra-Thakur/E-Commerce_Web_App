package com.Online_Bazar.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;
import java.text.SimpleDateFormat;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Online_Bazar.connection.DBConnection;
import com.Online_Bazar.dao.OrderDao;
import com.Online_Bazar.modal.Cart;
import com.Online_Bazar.modal.Order;
import com.Online_Bazar.modal.User;
import com.Online_Bazar.modal.product;

/**
 * Servlet implementation class OrderNow
 */
@WebServlet("/ordernow")
public class OrderNow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try(PrintWriter out = response.getWriter()){
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date = new Date();
			
			User user = (User) request.getSession().getAttribute("auth"); 
			
			if(user != null) {
				String p_id = request.getParameter("id");
				int p_quantity =  Integer.parseInt(request.getParameter("quantity"));
				
				if(p_quantity <=0) {
					p_quantity = 1;
				}
				
				Order  ordermodal = new Order();
				
				ordermodal.setId(Integer.parseInt(p_id));
				ordermodal.setUid( user.getId() );
				ordermodal.setQuantity(p_quantity);
				ordermodal.setDate(format.format(date));
				
				OrderDao orderDao = new OrderDao(DBConnection.getConnection());
				boolean result = orderDao.isertOrder(ordermodal);
				
				if(result) {
					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cartlist");
					
					if(cart_list != null) {
						for(Cart cart : cart_list) {
							if(cart.getId() == Integer.parseInt(p_id)) {
								cart_list.remove(cart_list.indexOf(cart));
								break;
							}
							
						}
						
					}
					response.sendRedirect("orders.jsp");
				}else {
					out.print("failed to order");
				}
				
			}else {
				response.sendRedirect("login.jsp");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
