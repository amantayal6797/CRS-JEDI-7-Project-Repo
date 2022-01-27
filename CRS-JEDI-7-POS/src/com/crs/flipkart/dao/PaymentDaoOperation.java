/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.crs.flipkart.constants.SQLQueryConstant;
import com.crs.flipkart.utils.DBUtils;

/**
 * @author Aditya
 *
 */
public class PaymentDaoOperation implements PaymentDaoOperationInterface{
	
	
	public void savePayment(int userId, int amount, String trasactionId) {
		
		DBUtils DBUtils = new DBUtils();
	    Connection conn = DBUtils.connectionEstablish();
	  
		try {
					
		    PreparedStatement stmt=conn.prepareStatement(SQLQueryConstant.SAVE_PAYMENT);
		    stmt.setInt(1,userId);
			stmt.setInt(2,amount);
			stmt.setString(3,trasactionId);
			 int i = stmt.executeUpdate();  	
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
			DBUtils.connectionClose(conn);	
		}
		
		
		
	}

}
