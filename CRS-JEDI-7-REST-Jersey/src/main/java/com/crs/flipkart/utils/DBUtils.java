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

/**
 * Manages the connection with MySQL server
 * 
 * @author Aditya
 *
 */



public class DBUtils {
	
		   
		public Connection connectionEstablish()  {
			/**
			 * Fetches and establishes a connection with MySQL server
			 * with the mentioned database and login credentials
			 * 
			 * @return Connection which is used to fire queries to the connected database
			 */
			 Connection connection = null;
			    try {
	            	Properties prop = new Properties();
	                InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("./com/crs/flipkart/utils/config.properties");
	                prop.load(inputStream);
	                String driver = prop.getProperty("driver");
	                String url = prop.getProperty("url");
	                String user = prop.getProperty("user");
	                String password = prop.getProperty("password");
	  
	                Class.forName(driver);
	                connection = DriverManager.getConnection(url, user, password);
	                return connection;
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            return connection;
	        }
				   
		
				  /**
			 * Terminates connection with the database if instance
			 * of connection is not null
			 */ 
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
