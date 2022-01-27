/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.User;
import com.crs.flipkart.utils.DBUtils;

/**
 * @author aditya.gupta3
 *
 */
public class UserDaoOperation implements UserDaoOperationInterface {
		
		public User getUser (int userId) {
			DBUtils connectionSetup = new DBUtils();
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
						user.setPassword(rs.getString("Password"));
						user.setIsApproved((rs.getInt("isApproved")==1)? true: false);
//						user.setAddress(rs.getString("Address"));
//						user.setAge(rs.getInt("Age"));
//						user.setGender(rs.getString("Gender"));
//						user.setContact(rs.getString("Contact"));
//						user.setNationality(rs.getString("Nationality"));
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
		
		//done
		public void approveUser (int userId) {
			if (getUser(userId)==null) {
				System.out.println("User does not exists with this userId");
				return;
			}
			DBUtils connectionSetup = new DBUtils();
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
		//done
		public String Authorize(int id,String password) {
			
			 DBUtils connectionSetup = new DBUtils();
			 Connection conn = connectionSetup.connectionEstablish();
			 String sql = "select * from user";
			 PreparedStatement stmt;
			 ResultSet rs = null;
			 String role="Invalid";
				try {
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery(sql);
					while(rs.next()){	
					     if(rs.getInt("userid")==id) {
					    	 if( rs.getString("password").equals(password)){
					    		 if(rs.getInt("isapproved")==0) {
					    			 System.out.println("User is not approved by admin");
					    			 break;
					    		 }
					    		 PreparedStatement stmt1=conn.prepareStatement("select * from role where userid =?");
					    		 stmt1.setInt(1, id);
					    		 ResultSet rs1=stmt1.executeQuery();
					    		 while(rs1.next()) {
					    			 role=rs1.getString("role");
					    			 break;
					    		 }
					    		 break;
					    		 
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
				
			return role;
		}
		
		//done
		public int updatePasswordCheck(int userId, String Password) {
			
			 DBUtils connectionSetup = new DBUtils();
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
					     if( rs.getInt("userid")==userId) {
					    	 String sql1 = "update user set password=? where userid=?";
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
		
		//done
		public void registerStudent(int userId, String password, String userName, String address, int age, String branch, String contact, String email, String gender) {
			
			 DBUtils connectionSetup = new DBUtils();
			 Connection conn = connectionSetup.connectionEstablish();
			 PreparedStatement stmt;
			 ResultSet rs;
			 String sql;
			 
			 try {
				 sql = "insert into user values(?,?,?)";
				 stmt = conn.prepareStatement(sql);
				 stmt.setInt(1,userId);
				 stmt.setString(2,password);  
				 stmt.setInt(3,0);  	 
				 int i=stmt.executeUpdate(); 
				 sql = "insert into student values(?,?,?,?,?,?,?,?,?,?)";;
				 stmt = conn.prepareStatement(sql);
				 stmt.setInt(1,userId);
				 stmt.setString(2,userName);  
				 stmt.setString(3,email);  
				 stmt.setString(4,address);  
				 stmt.setInt(5,age);
				 stmt.setString(6,gender);  
				 stmt.setString(7,contact); 
				 stmt.setInt(8,0);
				 stmt.setString(9, branch);
				 stmt.setInt(10, 0);
				 
				 
				i=stmt.executeUpdate();   
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//done
		public void registerUser(int userId, String password, boolean isApproved) {
			DBUtils connectionSetup = new DBUtils();
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
		
		public ArrayList<Integer> getUnapprovedStudents(){
			ArrayList<Integer> unapprovedStudents=new ArrayList<Integer>();
			DBUtils connectionSetup = new DBUtils();
		    Connection conn = connectionSetup.connectionEstablish();
			String sql = "select * from user where isapproved=0";
			try {
			    PreparedStatement stmt=conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery(); 
				
				while (rs.next()) {
						unapprovedStudents.add(rs.getInt("userid"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			    e.printStackTrace();
			    return null;
			} 
				connectionSetup.connectionClose(conn);	
				return unapprovedStudents;
			
			
		
		}

}

