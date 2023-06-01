package com.Online_Bazar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Online_Bazar.connection.DBConnection;
import com.Online_Bazar.modal.AdminModal;

public class AdminDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	
	public AdminDao(Connection con) {
		super();
		this.con = con;
	}



	public AdminModal authAdmin(String username, String password) {
		AdminModal admin = null;
		
		try {
			query = "select * from admin where uname=? and password=? ";
			pst = this.con.prepareStatement(query);
			
			pst.setString(1, username);
			pst.setString(2, password);
		
			rs = pst.executeQuery();
			
			if(rs.next()) {
				admin = new AdminModal();
				admin.setId(rs.getInt("id"));
				admin.setUsername(rs.getString("uname"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return admin;
	}
}
