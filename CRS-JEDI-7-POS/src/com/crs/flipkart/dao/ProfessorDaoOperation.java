/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.utils.ConnectionSetup;

/**
 * @author aditya.gupta3
 *
 */
public class ProfessorDaoOperation {
	
	UserDaoOperation userDaoOperation = new UserDaoOperation();
	
	public void addProfessor(Professor professor) {
		ConnectionSetup connectionSetup = new ConnectionSetup();
	    Connection conn = connectionSetup.connectionEstablish();
	    try {
	    	userDaoOperation.registerUser(professor.getUserId(), professor.getPassword(), professor.getIsApproved());
	    	String sql = "insert into professor values (?,?,?,?,?,?,?,?)";
	    	PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, professor.getUserId());
		    stmt.setString(2, professor.getUserName());
		    stmt.setString(3, professor.getEmail());
		    stmt.setString(4, professor.getAddress());
		    stmt.setInt(5, professor.getAge());
		    stmt.setString(6, professor.getGender());
		    stmt.setString(7, professor.getContact());
		    stmt.setString(8, professor.getDepartment());
		    int i = stmt.executeUpdate();
		    if(i==0) {
				System.out.println("Error in adding professor");
			} else {
				System.out.println("Professor - "+professor.getUserId()+" added successfully");
			}
	    }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connectionSetup.connectionClose(conn);	
		}
	}
}
