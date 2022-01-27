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
		
		public String Authorize(int userId,String password) {
			
			ConnectionSetup connectionSetup = new ConnectionSetup();
			Connection conn = connectionSetup.connectionEstablish();
			 
			try {
				String sql = "select * from user";
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){	
				    if(rs.getInt("userId")==userId) {
				    	if(rs.getString("password").equals(password)) {
				    		sql = "select role from role where userid=?";
				    		stmt = conn.prepareStatement(sql);
				    		stmt.setInt(1, userId);
				    		ResultSet rs2 = stmt.executeQuery();
				    		while (rs2.next()) {
				    			return rs2.getString("role");	
				    		}
				    	}
				    	else {
				    		return "Invalid Password"; 
				    	}
				    }	
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return "Error";
			}
				
			return "Invalid ID";
		}
		
		
		public int updatePasswordCheck(int userId, String Password) {
			
			 ConnectionSetup connectionSetup = new ConnectionSetup();
			 Connection conn = connectionSetup.connectionEstablish();
			 String sql = "select * from user";
			 PreparedStatement stmt;
			 ResultSet rs;
				 
			 //1: Password updated successfully
			 //2: DB Error
			 //3: Student id not found
				try {
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery(sql);
					while(rs.next()){	
					     if( rs.getInt("userId")==userId) {
					    	 String sql1 = "update user set password=? where userId=?";
							 PreparedStatement stmt1= conn.prepareStatement(sql1);
							 stmt1.setString(1,Password);
							 stmt1.setInt(2,userId);  
							 int i = stmt1.executeUpdate();  			  
					    		 return 1;
					     }	
					 }
					return 3;
				} catch (SQLException e) {
					e.printStackTrace();
					return 2;
				}
		}
		
		public void registerUser(int userId, String password, boolean isApproved) {
			ConnectionSetup connectionSetup = new ConnectionSetup();
		    Connection conn = connectionSetup.connectionEstablish();
			String sql = "insert into user values (?,?,?)";
			try {
			    PreparedStatement stmt=conn.prepareStatement(sql);
				stmt.setInt(1, userId);
				stmt.setString(2, password);
				stmt.setInt(3, (isApproved)? 1: 0);
				int i=stmt.executeUpdate(); 
				if(i==0) {
					System.out.println("Error in registering user");
				} else {
					System.out.println("User - "+userId+" registered successfully");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			    e.printStackTrace();
			} finally {
				connectionSetup.connectionClose(conn);	
			}
		}
		
}
