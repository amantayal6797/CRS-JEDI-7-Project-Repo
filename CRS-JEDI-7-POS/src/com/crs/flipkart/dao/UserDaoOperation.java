/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.crs.flipkart.bean.User;
import com.crs.flipkart.constants.SQLQueryConstant;
import com.crs.flipkart.exception.ErrorInApprovingUserException;
import com.crs.flipkart.exception.ErrorInRegisteringUserException;
import com.crs.flipkart.exception.UserDoNotExistException;
import com.crs.flipkart.utils.DBUtils;

/**
 * @author aditya.gupta3
 *
 */
public class UserDaoOperation implements UserDaoOperationInterface {
		
		public User getUser (int userId) {
			DBUtils DBUtils = new DBUtils();
		    Connection conn = DBUtils.connectionEstablish();
//			String sql = "select * from user where userId = ?";
			try {
			    PreparedStatement stmt=conn.prepareStatement(SQLQueryConstant.GET_USER_DETAIL);
				stmt.setInt(1, userId);
				ResultSet rs = stmt.executeQuery(); 
				
				while (rs.next()) {
					if (rs.getInt("userId")==userId) {
						User user = new User();
						user.setUserId(userId);
						user.setPassword(rs.getString("Password"));
						user.setIsApproved((rs.getInt("isApproved")==1)? true: false);
						return user;
					}
				}
				
				return null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			    e.printStackTrace();
			    return null;
			} finally {
				DBUtils.connectionClose(conn);	
			}
		}
		
		//done
		public void approveUser (int userId) {
			/*
			if (getUser(userId)==null) {
				System.out.println("User does not exists with this userId");
				return;
			}
			*/
			try {
				if (getUser(userId)==null) {
					throw new UserDoNotExistException();
				}
			}catch(UserDoNotExistException e) {
				System.out.println(e.getMessage());
				return;
			}
			
			DBUtils DBUtils = new DBUtils();
		    Connection conn = DBUtils.connectionEstablish();
//			String sql = "update user set isApproved=1 where userId = ?";
			try {
			    PreparedStatement stmt=conn.prepareStatement(SQLQueryConstant.APPROVE_USER);
				stmt.setInt(1, userId);
				int i=stmt.executeUpdate();
				
				/*
				if(i==0) {
					System.out.println("Error in approving user-"+userId);
				} else {
					System.out.println("User - "+userId+" approved successfully");
				}
				*/
				
				try {
					if(i==0)
						throw new ErrorInApprovingUserException();
					else
						System.out.println("User - "+userId+" approved successfully");	
				}catch (ErrorInApprovingUserException e){
					System.out.println(e.getMessage(userId));
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			    e.printStackTrace();
			} finally {
				DBUtils.connectionClose(conn);	
			}
		}
		
public String Authorize(int userId,String password) {
			
			DBUtils DBUtils = new DBUtils();
			Connection conn = DBUtils.connectionEstablish();
			 
			try {
//				String sql = "select * from user";
				PreparedStatement stmt = conn.prepareStatement(SQLQueryConstant.GET_ALL_USERS);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){	
				    if(rs.getInt("userId")==userId) {
				    	if(rs.getString("password").equals(password)) {
//				    		sql = "select role from role where userid=?";
				    		stmt = conn.prepareStatement(SQLQueryConstant.GET_USER_ROLE);
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
		
		//done
		public int updatePasswordCheck(int userId, String Password) {
			
			 DBUtils DBUtils = new DBUtils();
			 Connection conn = DBUtils.connectionEstablish();
//			 String sql = "select * from user";
			 PreparedStatement stmt;
			 ResultSet rs;
				 
			 //1: Password updated successfully
			 //2: DB Error
			 //3: Student id not found
				try {
					stmt = conn.prepareStatement(SQLQueryConstant.GET_ALL_USERS);
					rs = stmt.executeQuery();
					while(rs.next()){	
					     if( rs.getInt("userid")==userId) {
//					    	 String sql1 = "update user set password=? where userid=?";
							 PreparedStatement stmt1= conn.prepareStatement(SQLQueryConstant.UPDATE_PASSWORD);
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
			DBUtils DBUtils = new DBUtils();
		    Connection conn = DBUtils.connectionEstablish();
//			String sql = "insert into user values (?,?,?)";
			try {
			    PreparedStatement stmt=conn.prepareStatement(SQLQueryConstant.ADD_USER_QUERY);
				stmt.setInt(1, userId);
				stmt.setString(2, password);
				stmt.setInt(3, (isApproved)? 1: 0);
				int i=stmt.executeUpdate(); 
				/*
				if(i==0) {
					System.out.println("Error in registering user");
				} else {
					System.out.println("User - "+userId+" registered successfully");
				}
				*/
				try {
					if(i==0)
						throw new ErrorInRegisteringUserException();
					else
						System.out.println("User - "+userId+" registered successfully");	
				}catch(ErrorInRegisteringUserException e) {
					System.out.println(e.getMessage());
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			    e.printStackTrace();
			} finally {
				DBUtils.connectionClose(conn);	
			}
		}
		public ArrayList<Integer> getUnapprovedStudents(){
			ArrayList<Integer> unapprovedStudents=new ArrayList<Integer>();
			DBUtils DBUtils = new DBUtils();
		    Connection conn = DBUtils.connectionEstablish();
//			String sql = "select * from user where isapproved=0";
			try {
			    PreparedStatement stmt=conn.prepareStatement(SQLQueryConstant.UNAPPROVED_USERS);
				ResultSet rs = stmt.executeQuery(); 
				
				while (rs.next()) {
						unapprovedStudents.add(rs.getInt("userid"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			    e.printStackTrace();
			    return null;
			} 
				DBUtils.connectionClose(conn);	
				return unapprovedStudents;
			
			
		
		}

}

