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
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.constants.SQLQueryConstant;
import com.crs.flipkart.exception.CourseIDAlreadyExistException;
import com.crs.flipkart.exception.ErrorInAddingCourseException;
import com.crs.flipkart.exception.ErrorInDropingCourseException;
import com.crs.flipkart.utils.DBUtils;

/**
 * @author aditya.gupta3
 *
 */
public class AdminDaoOperation implements AdminDaoOperationInterface {
	
	CourseDaoOperation courseDaoOperation = new CourseDaoOperation();
	
	public Admin getAdmin(int userID) {
		DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
		 Admin admin=new Admin();
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(SQLQueryConstant.GET_USER_DETAIL);
			 stmt.setInt(1, userID);
			 ResultSet rs=stmt.executeQuery(); 
			 while(rs.next()) {
				admin.setUserId(rs.getInt("userID"));
				admin.setPassword(rs.getString("Password"));
				if(rs.getInt("isApproved")==1)
					admin.setIsApproved(true);
				else
					admin.setIsApproved(false);
				
			 }
			 PreparedStatement stmt2=conn2.prepareStatement(SQLQueryConstant.GET_ADMIN_DETAIL);
			 stmt2.setInt(1, userID);
			rs=stmt2.executeQuery(); 
			 while(rs.next()) {
				 admin.setUserName(rs.getString("username"));
				 admin.setEmail(rs.getString("email"));
				admin.setAddress(rs.getString("address"));
				admin.setAge(rs.getInt("age"));
				admin.setGender(rs.getString("gender"));
				admin.setContact(rs.getString("contact"));
			 }
			} catch (SQLException e) {
				System.out.println("Exception raised: "+e.getMessage());
			}
			connectObj.connectionClose(conn2);
			return admin;
	}
	
	public boolean addCourse (Course course) {
		
		DBUtils connectionSetup = new DBUtils();
	    Connection conn = connectionSetup.connectionEstablish();
	    try {
		    PreparedStatement stmt = conn.prepareStatement(SQLQueryConstant.ADD_COURSE_QUERY);
	    	stmt.setInt(1, course.getCourseID());
		    stmt.setString(2, course.getCourseName());
		    stmt.setInt(4, course.getProfessorAllotted());
		    stmt.setInt(3, course.getCredits());
		    int i=stmt.executeUpdate(); 
		    
		    try {
		    	if(i==0) 
		    		throw new ErrorInAddingCourseException(1);
				 else 
					 return true;
					
		    }
		    catch(ErrorInAddingCourseException e){
		    	System.out.println(e.getMessage());
		    }
		 
	    }catch (SQLException e) {
			System.out.println("Exception raised: "+e.getMessage());
		} return false;
		
	}
	
	public boolean dropCourse(int courseId) {
		
		
		DBUtils connectionSetup = new DBUtils();
	    Connection conn = connectionSetup.connectionEstablish();
	    try {
	    	PreparedStatement stmt = conn.prepareStatement(SQLQueryConstant.DELETE_COURSE_QUERY);
		    stmt.setInt(1, courseId);
		    int i = stmt.executeUpdate();
		    
		    try {
		    	if(i==0) 
		    		throw new ErrorInDropingCourseException(1);
				 else 
					 return true;
		    }
		    catch(ErrorInDropingCourseException e){
		    	System.out.println(e.getMessage());
		    }
		    
		    
	    }catch (SQLException e) {
			System.out.println("Exception raised: "+e.getMessage());
		} 
			connectionSetup.connectionClose(conn);
			
		return false;
	}
}
