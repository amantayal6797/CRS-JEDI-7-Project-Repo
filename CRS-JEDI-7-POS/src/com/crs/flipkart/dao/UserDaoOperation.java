/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.crs.flipkart.bean.Professor;
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
		
		public String Authorize(int id,String password) {
			
			 ConnectionSetup connectionSetup = new ConnectionSetup();
			 Connection conn = connectionSetup.connectionEstablish();
			 String sql = "select * from user";
			 PreparedStatement stmt;
			 ResultSet rs = null;
			 
				try {
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery(sql);
					while(rs.next()){	
					     if(rs.getInt("userId")==id) {
					    	 if( rs.getString("Password").equals(password)){
					    		 return rs.getString("Role");
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
		
		public void registerStudent(int userId, String password, String userName, String address, int age, String branch, String contact, String email, String gender) {
			
			 ConnectionSetup connectionSetup = new ConnectionSetup();
			 Connection conn = connectionSetup.connectionEstablish();
			 PreparedStatement stmt;
			 ResultSet rs;
			 String sql;
			 
			 try {
				 sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";;
				 stmt = conn.prepareStatement(sql);
				 stmt.setInt(1,userId);
				 stmt.setString(2,userName);  
				 stmt.setString(3,password);  
				 stmt.setString(4,"Student");  
				 stmt.setString(5,email); 
				 stmt.setInt(6,0);
				 stmt.setString(7,address);  
				 stmt.setInt(8,age);
				 stmt.setString(9,gender);  
				 stmt.setString(10,contact); 
				 
				 int i=stmt.executeUpdate();   
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			 try {
				 sql = "insert into student values(?,?,?,?)";
				 stmt = conn.prepareStatement(sql);
				 stmt.setInt(1,userId);
				 stmt.setInt(2,0);  
				 stmt.setString(3,branch);  
				 stmt.setInt(4,0);  	 
				 int i=stmt.executeUpdate();   
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}

		public void registerUser(Professor professor) {
			// TODO Auto-generated method stub
			
		}
		
}
