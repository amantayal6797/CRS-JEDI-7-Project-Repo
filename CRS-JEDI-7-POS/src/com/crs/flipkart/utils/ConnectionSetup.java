/**
 * 
 */
package com.crs.flipkart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Aditya
 *
 */

/*
 ConnectionSetup connectionSetup = new ConnectionSetup();
		Connection conn = connectionSetup.connectionEstablish();
		String sql = "select * from professor";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

		      while(rs.next()){
		         int id  = rs.getInt("userId");
		         System.out.print("ID: " + id);
		      }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connectionSetup.connectionClose(conn);
 */


public class ConnectionSetup {
	
		   static final String DB_URL = "jdbc:mysql://localhost/crs_db";
		   static final String USER = "root";
		   static final String PASS = "108062@Ma";		   
		   
		
		public Connection connectionEstablish() {
			Connection conn = null;
				   try{
						  // Class.forName("com.mysql.jdbc.Driver");
					      System.out.println("Connecting to database...");
					      conn = DriverManager.getConnection(DB_URL,USER,PASS);						      
				   }catch(SQLException se){
				          se.printStackTrace();
				   }catch(Exception e){
				      e.printStackTrace();
				   }
				   return conn;
				   }
		
				   
					public  void connectionClose(Connection conn) {
						
					try {
					    conn.close();
					   }catch(SQLException se){
					          se.printStackTrace();
					   }catch(Exception e){
					      e.printStackTrace();
					   }finally{
					      try{
					         if(conn!=null)
					            conn.close();
					      }catch(SQLException se){
					         se.printStackTrace();
					      }
					   }
					
					}
		}
