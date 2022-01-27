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


public class DBUtils {
	
		   
		public Connection connectionEstablish() {
			 Connection connection = null;
			    try {
	            	Properties prop = new Properties();
	                InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("./config.properties");
	                prop.load(inputStream);
	                String driver = prop.getProperty("driver");
	                String url = prop.getProperty("url");
	                String user = prop.getProperty("user");
	                String password = prop.getProperty("password");
//	                Class.forName(driver);
	                connection = DriverManager.getConnection(url, user, password);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            return connection;
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
