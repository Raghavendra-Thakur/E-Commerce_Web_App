package com.Online_Bazar.dao;

import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Online_Bazar.modal.Cart;
import com.Online_Bazar.modal.User;
import com.Online_Bazar.modal.product;


public class ProductDao {
	
	
		private Connection con;
		private String query;
		private PreparedStatement pst;
		private ResultSet rs;
		
		public ProductDao(Connection con) {
			super();
			this.con = con;
		}
		
		public List<product> getAllproducts(){
			List<product> products = new ArrayList<product>();
			
			try {
				query="select * from products";
				pst = this.con.prepareStatement(query);
				rs = pst.executeQuery();
				
				while(rs.next()) {
					product rowProduct = new product();
					rowProduct.setId(rs.getInt("id"));
					rowProduct.setCategory(rs.getString("category"));
					rowProduct.setName(rs.getString("name"));
					rowProduct.setImg(rs.getString("image"));
					rowProduct.setPrice(rs.getDouble("price"));
					
					products.add(rowProduct);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return products;
		}
		
		public List<Cart> getCartProduct(ArrayList<Cart> cartList){
			List<Cart> products = new ArrayList<Cart>();
			
			try {
				
				if(cartList.size() > 0) {
					for(Cart item:cartList) {
						query="select * from products  where id=?";
						pst = this.con.prepareStatement(query);
						pst.setInt(1, item.getId());
						rs = pst.executeQuery();
						
						while(rs.next()) {
							Cart row = new Cart();
							
							row.setId(rs.getInt("id"));
							row.setCategory(rs.getString("category"));
							row.setPrice(rs.getDouble("price")*item.getQuantity());
							row.setName(rs.getString("name"));
							row.setQuantity(item.getQuantity());
							products.add(row);
							
						
						}
						
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return products;
			
		}
		
		public double getTotalPrice(ArrayList<Cart> cartList) {
			double sum = 0;
			
			try {
				if(cartList.size() > 0) {
					for(Cart item:cartList) {
					query = "select * from products where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					
					while(rs.next()) {
						sum += rs.getDouble("price")*item.getQuantity();
					}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			return sum;
			
		}
		
		public product getSingleProduct(int id) {
			product product = new product();
			try {
					query = "select * from products where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1,id);
					rs = pst.executeQuery();
						
					while(rs.next()) {
						product.setId(rs.getInt("id"));
						product.setCategory(rs.getString("category"));
						product.setPrice(rs.getDouble("price"));
						product.setName(rs.getString("name"));
						product.setImg(rs.getString("image"));
						
					}
					
				}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			return product;	
		}
		
		public boolean insertProduct(product p) {
			boolean result =false;
			try {
				query = "INSERT INTO products (name, category, price, image) VALUES (?, ?, ?, ?)";
				pst = this.con.prepareStatement(query);
				pst.setString(1,p.getName());
				pst.setString(2,p.getCategory());
				pst.setDouble(3,p.getPrice());
				pst.setString(4,p.getImg());
			 result = 	pst.execute();
			
			}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			return  result;
		}

		public boolean deleteProduct(int id) {
			// TODO Auto-generated method stub
			boolean result = false ;
			try {
				query = "delete from products where id=? ";
				pst = this.con.prepareStatement(query);
				pst.setInt(1,id);
				
			 if(pst.executeUpdate() > 0) {
				 result = true;
			 };
			System.out.println(result +"from daoooooo");
			
			}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			
			return result;
		}
		
	
}
