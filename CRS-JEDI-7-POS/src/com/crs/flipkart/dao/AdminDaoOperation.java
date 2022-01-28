/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.crs.flipkart.bean.Course;
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
	
	public void addCourse (Course course) {
			try {
				if(courseDaoOperation.verifyCourse(course.getCourseID()))
					throw new CourseIDAlreadyExistException();
			}
			catch(CourseIDAlreadyExistException e){
				System.out.println(e.getMessage());
				return;	
			}
		
		DBUtils connectionSetup = new DBUtils();
	    Connection conn = connectionSetup.connectionEstablish();
	    try {
//	    	String sql = "insert into coursecatalog values (?,?,?,?,'NA')";
		    PreparedStatement stmt = conn.prepareStatement(SQLQueryConstant.ADD_COURSE_QUERY);
	    	stmt.setInt(1, course.getCourseID());
		    stmt.setString(2, course.getCourseName());
		    stmt.setInt(4, course.getProfessorAllotted());
		    stmt.setInt(3, course.getCredits());
		    int i=stmt.executeUpdate(); 
		    
		    /*
			if(i==0) {
				System.out.println("Error in adding course in course catalog");
			} else {
				System.out.println("Course - "+course.getCourseID()+" added successfully");
			}	
		    */
		    
		    try {
		    	if(i==0) 
		    		throw new ErrorInAddingCourseException(1);
				 else 
					System.out.println("Course - "+course.getCourseID()+" added successfully");
		    }
		    catch(ErrorInAddingCourseException e){
		    	System.out.println(e.getMessage());
		    }
		 
	    }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connectionSetup.connectionClose(conn);	
		}
	}
	
	public void dropCourse(int courseId) {
		if(!courseDaoOperation.verifyCourse(courseId)) {
			System.out.println("Course ID does not exist");
			return;
		}
		
		DBUtils connectionSetup = new DBUtils();
	    Connection conn = connectionSetup.connectionEstablish();
	    try {
//	    	String sql = "delete from course where courseid = ?";
	    	PreparedStatement stmt = conn.prepareStatement(SQLQueryConstant.DELETE_COURSE_QUERY);
		    stmt.setInt(1, courseId);
		    int i = stmt.executeUpdate();
		    
		    /*
		    if(i==0) {
				 System.out.println("Error in dropping course - "+courseId);
			} else {
				System.out.println("Course - "+courseId+" dropped successfully");
			}
		    */
		    
		    try {
		    	if(i==0) 
		    		throw new ErrorInDropingCourseException(1);
				 else 
					 System.out.println("Course - "+courseId+" dropped successfully");
		    }
		    catch(ErrorInDropingCourseException e){
		    	System.out.println(e.getMessage());
		    }
		    
		    
	    }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connectionSetup.connectionClose(conn);
		}
	}
}
