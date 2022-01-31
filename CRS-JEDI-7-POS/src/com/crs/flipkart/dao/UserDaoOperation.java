/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

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
		private static Logger logger = Logger.getLogger(UserDaoOperation.class);
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
			    logger.debug("Exception raised: "+e.getMessage());
			    return null;
			} finally {
				DBUtils.connectionClose(conn);	
			}
		}
		
		//done
		public boolean approveUser (int userId) throws UserDoNotExistException {
				if (getUser(userId)==null) {
					throw new UserDoNotExistException();
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
					logger.error("Error in approving user-"+userId);
				} else {
					logger.info("User - "+userId+" approved successfully");
				}
				*/
				
				try {
					if(i==0)
						throw new ErrorInApprovingUserException();
					else
						return true;
				}catch (ErrorInApprovingUserException e){
					System.out.println(e.getMessage(userId));
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			    logger.debug("Exception raised: "+e.getMessage());
			} DBUtils.connectionClose(conn);	
			return false;
			
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
				    		if(rs.getInt("isapproved")==0)
				    			return "You are not approved by Admin";
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
				logger.debug("Exception raised: "+e.getMessage());
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
					logger.debug("Exception raised: "+e.getMessage());
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
					logger.error("Error in registering user");
				} else {
					logger.info("User - "+userId+" registered successfully");
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
			    logger.debug("Exception raised: "+e.getMessage());
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
			    logger.debug("Exception raised: "+e.getMessage());
			    return null;
			} 
				DBUtils.connectionClose(conn);	
				return unapprovedStudents;
			
			
		
		}

}

