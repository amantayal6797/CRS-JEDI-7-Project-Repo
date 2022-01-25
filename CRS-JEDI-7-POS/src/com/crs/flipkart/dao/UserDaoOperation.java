package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.crs.flipkart.utils.ConnectionSetup;

public class UserDaoOperation {
	
	// Register student
	// Verify Credentials
	// Update Password
	
	public void approveUser (int userId) {
		ConnectionSetup connectionSetup = new ConnectionSetup();
	    Connection conn = connectionSetup.connectionEstablish();
		String sql = "update user set isApproved=1 where userId = ?";
		try {
		    PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			int i=stmt.executeUpdate(); 
			if(i==0) {
				System.out.println("Error in approving user-"+userId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		    e.printStackTrace();
		} finally {
			connectionSetup.connectionClose(conn);	
		}
	}
	
	
	
	
	
	
	

}
