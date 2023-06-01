package com.Online_Bazar.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

/**
 * Servlet implementation class ChecOutServlet
 */
@WebServlet("/checkout")
public class ChecOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try(PrintWriter out = response.getWriter()){
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date = new Date();
			
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cartlist");

			User user = (User) request.getSession().getAttribute("auth"); 
			
			if(cart_list != null && user != null) {
				for(Cart c: cart_list) {
					Order order = new Order();
					
					order.setId(c.getId());
					order.setUid(user.getId());
					order.setQuantity(c.getQuantity());
					order.setDate(format.format(date));
					
					OrderDao orderDao = new OrderDao(DBConnection.getConnection());
					
					Boolean result = orderDao.isertOrder(order);
					
					if(!result) break;
					
				}
				
				cart_list.clear();
				response.sendRedirect("orders.jsp");;
				
			}else {
				if(user == null) {
					response.sendRedirect("login.jsp");
				}
				
				response.sendRedirect("cart.jsp");
			}

			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
