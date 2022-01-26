/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.crs.flipkart.bean.User;
import com.crs.flipkart.utils.ConnectionSetup;

/**
 * @author aditya.gupta3
 *
 */
public class UserDaoOperation {
		// Register User
		// Verify Credentials
		// Update Password
		
		public User getUser (int userId) {
			ConnectionSetup connectionSetup = new ConnectionSetup();
		    Connection conn = connectionSetup.connectionEstablish();
			String sql = "select * from user where userId = ?";
			try {
			    PreparedStatement stmt=conn.prepareStatement(sql);
				stmt.setInt(1, userId);
				ResultSet rs = stmt.executeQuery(); 
				
				while (rs.next()) {
					if (rs.getInt("userId")==userId) {
						User user = new User();
						user.setUserId(userId);
						user.setUserName(rs.getString("userName"));
						user.setPassword(rs.getString("Password"));
						user.setRole(rs.getString("Role"));
						user.setEmail(rs.getString("Email"));
						user.setIsApproved((rs.getInt("isApproved")==1)? true: false);
						user.setAddress(rs.getString("Address"));
						user.setAge(rs.getInt("Age"));
						user.setGender(rs.getString("Gender"));
						user.setContact(rs.getString("Contact"));
						user.setNationality(rs.getString("Nationality"));
						return user;
					}
				}
				
				return null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			    e.printStackTrace();
			    return null;
			} finally {
				connectionSetup.connectionClose(conn);	
			}
		}
		
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
				} else {
					System.out.println("User - "+userId+" approved successfully");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			    e.printStackTrace();
			} finally {
				connectionSetup.connectionClose(conn);	
			}
		}
		
		public void registerUser (User user) {
			
		}

}
