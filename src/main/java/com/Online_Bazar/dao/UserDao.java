package com.Online_Bazar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Online_Bazar.modal.User;

public class UserDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public UserDao(Connection con) {
		super();
		this.con = con;
	}
	
	public User userLogin(String email ,String Password) {
		User user = null;
		
		try {
			query = "select * from users where email=? and password=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, Password);
			rs = pst.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return user;
	}
	
	
	
	
}
