/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Admin;
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
	//private static Logger logger = Logger.getLogger(ProfessorDaoOperation.class);
	
	public boolean addProfessor(Professor professor) {
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
		    
		    stmt = conn.prepareStatement(SQLQueryConstant.ADD_PROFESSOR_ROLE);
		    stmt.setInt(1, professor.getUserId());
		    i = stmt.executeUpdate();
		    
		    try {
		    	if(i==0)
		    		throw new ErrorInAddingProfessorException();
		    	else
		    		return true;
		    }catch(ErrorInAddingProfessorException e) {
		    	System.out.println(e.getMessage());
		    }
	    }catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception raised: "+e.getMessage());
		} 
			connectionSetup.connectionClose(conn);
			return false;
	}

	@Override
	public Professor getProfessor(int userId) {
		DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
//		 String sql1 = "select * from user where userid = ?";
//		 String sql2 = "select * from student where userid = ?";
		 Professor professor=new Professor();
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(SQLQueryConstant.GET_USER_DETAIL);
			 stmt.setInt(1, userId);
			 ResultSet rs=stmt.executeQuery(); 
			 while(rs.next()) {
				professor.setUserId(rs.getInt("userID"));
				professor.setPassword(rs.getString("Password"));
				if(rs.getInt("isApproved")==1)
					professor.setIsApproved(true);
				else
					professor.setIsApproved(false);
				
			 }
			 PreparedStatement stmt2=conn2.prepareStatement(SQLQueryConstant.GET_PROFESSOR_DETAIL);
			 stmt2.setInt(1, userId);
			rs=stmt2.executeQuery(); 
			 while(rs.next()) {
				 professor.setUserName(rs.getString("username"));
				 professor.setEmail(rs.getString("email"));
				professor.setAddress(rs.getString("address"));
				professor.setAge(rs.getInt("age"));
				professor.setGender(rs.getString("gender"));
				professor.setContact(rs.getString("contact"));
			 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Exception raised: "+e.getMessage());
			}
			connectObj.connectionClose(conn2);
	return professor;
	}
}
