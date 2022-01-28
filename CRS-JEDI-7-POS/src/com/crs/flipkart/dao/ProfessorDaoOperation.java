/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.constants.SQLQueryConstant;
import com.crs.flipkart.exception.ErrorInAddingProfessorException;
import com.crs.flipkart.utils.DBUtils;

/**
 * @author aditya.gupta3
 *
 */
public class ProfessorDaoOperation implements ProfessorDaoOperationInterface {
	
	UserDaoOperationInterface userDaoOperation = new UserDaoOperation();
	private static Logger logger = Logger.getLogger(ProfessorDaoOperation.class);
	
	public void addProfessor(Professor professor) {
		DBUtils connectionSetup = new DBUtils();
	    Connection conn = connectionSetup.connectionEstablish();
	    try {
	    	userDaoOperation.registerUser(professor.getUserId(), professor.getPassword(), professor.getIsApproved());
//	    	String sql = "insert into professor values (?,?,?,?,?,?,?,?)";
	    	PreparedStatement stmt = conn.prepareStatement(SQLQueryConstant.ADD_PROFESSOR_DETAIL);
		    stmt.setInt(1, professor.getUserId());
		    stmt.setString(2, professor.getUserName());
		    stmt.setString(3, professor.getEmail());
		    stmt.setString(4, professor.getAddress());
		    stmt.setInt(5, professor.getAge());
		    stmt.setString(6, professor.getGender());
		    stmt.setString(7, professor.getContact());
		    stmt.setString(8, professor.getDepartment());
		    int i = stmt.executeUpdate();
			
		    try {
		    	if(i==0)
		    		throw new ErrorInAddingProfessorException();
		    	else
		    		System.out.println("Professor - "+professor.getUserId()+" added successfully");
		    }catch(ErrorInAddingProfessorException e) {
		    	System.out.println(e.getMessage());
		    }
		  
	    }catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.debug("Exception raised: "+e.getMessage());
		} finally {
			connectionSetup.connectionClose(conn);	
		}
	}
}
