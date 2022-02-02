/**
 * 
 */
package com.crs.flipkart.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Manages the connection with MySQL server
 * 
 * @author Aditya
 *
 */



public class DBUtils {
	
	 private static Logger logger = Logger.getLogger(DBUtils.class); 
		public Connection connectionEstablish()  {
			/**
			 * Fetches and establishes a connection with MySQL server
			 * with the mentioned database and login credentials
			 * 
			 * @return Connection which is used to fire queries to the connected database
			 */
		
			 Connection connection = null;
			    try {
  
			    	String driver="com.mysql.cj.jdbc.Driver";
			    	String url="jdbc:mysql://localhost:3306/crs_db";
			    	String user="root";
			    	String password="108062@Ma";
			    	Class.forName(driver);
	                connection = DriverManager.getConnection(url, user, password);
	                return connection;
	            } catch (SQLException e) {
	            	logger.error(e.getMessage());
	            }
			    catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
			    	logger.error(e.getMessage());
				}
			    logger.info("Database connection established.");
	            return connection;
	        }
				   
		
				  /**
			 * Terminates connection with the database if instance
			 * of connection is not null
			 */ 
		public  void connectionClose(Connection conn) {
			try {
				
			    conn.close();
			    logger.info("Database connection clossed.");
			   }catch(SQLException se){
				   logger.error(se.getMessage());
			   }catch(Exception e){
				   logger.error(e.getMessage());
			   }finally{
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			    	  logger.error(se.getMessage());
			      }
			   }
			
			}
}
