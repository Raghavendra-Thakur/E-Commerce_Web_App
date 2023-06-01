package com.Online_Bazar.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Online_Bazar.modal.Cart;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/addtocart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");

		try (PrintWriter out = response.getWriter()) {
			ArrayList<Cart> cartlist = new ArrayList<>();

			int id = Integer.parseInt(request.getParameter("id"));

			Cart cm = new Cart();

			cm.setId(id);
			cm.setQuantity(1);
			HttpSession session = request.getSession();

			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cartlist");

			if (cart_list == null) {
				cartlist.add(cm);

				session.setAttribute("cartlist", cartlist);

				response.sendRedirect("index.jsp");

			} else {
				cartlist = cart_list;
				boolean exist = false;

				for (Cart c : cartlist) {
					if (c.getId() == id) {
						exist = true;
						out.print(
								"<h3 style='color:crimson; text-align:center'>Item Already Exist In Cart <a href='cart.jsp'>Go To Cart</a></h3>");
					}
				}
				if (!exist) {
					cartlist.add(cm);
					response.sendRedirect("index.jsp");
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
