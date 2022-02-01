/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import org.apache.log4j.Logger;

import com.crs.flipkart.bean.User;
import com.crs.flipkart.constants.SQLQueryConstant;
import com.crs.flipkart.utils.DBUtils;

/**
 * @author Aditya
 *
 */
public class NotificationDaoOperation implements NotificationDaoOperationInterface {
	
	//private static Logger logger = Logger.getLogger(NotificationDaoOperation.class);
	public int geStatus(int userId) {
		
		DBUtils DBUtils = new DBUtils();
	    Connection conn = DBUtils.connectionEstablish();
	    int status = 0;
		try {
		    PreparedStatement stmt=conn.prepareStatement(SQLQueryConstant.GET_STATUS);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery(); 
			
			while (rs.next()) {
				status = rs.getInt("status");
			}
			return status;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		    System.out.println("Exception raised: "+e.getMessage());
		} finally {
			DBUtils.connectionClose(conn);	
		}
		return status;
	}
	
	public void updateStatus(int userId,int status) {
		
		DBUtils DBUtils = new DBUtils();
	    Connection conn = DBUtils.connectionEstablish();
	  
		try {
					
		    PreparedStatement stmt=conn.prepareStatement(SQLQueryConstant.SET_STATUS);
			 stmt.setInt(1,status);
			 stmt.setInt(2,userId);  
			 int i = stmt.executeUpdate();  	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		   System.out.println("Exception raised: "+e.getMessage());
		} finally {
			DBUtils.connectionClose(conn);	
		}
		
	}
	
public void insertStatus(int userId) {
		
		DBUtils DBUtils = new DBUtils();
	    Connection conn = DBUtils.connectionEstablish();
	  
		try {
					
		    PreparedStatement stmt=conn.prepareStatement(SQLQueryConstant.INSERT_STATUS);
			 stmt.setInt(2,userId);
			 stmt.setInt(1,0);  
			 int i = stmt.executeUpdate();  	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		    System.out.println("Exception raised: "+e.getMessage());
		} finally {
			DBUtils.connectionClose(conn);	
		}
		
	}
	
	
	

}
