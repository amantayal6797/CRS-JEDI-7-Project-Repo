/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.utils.ConnectionSetup;

/**
 * @author aditya.gupta3
 *
 */
public class AdminDaoOperation {
	
	CourseDaoOperation courseDaoOperation = new CourseDaoOperation();
	
	public void addCourse (Course course) {
		if(courseDaoOperation.verifyCourse(course.getCourseID())) {
			System.out.println("Course ID already exist");
			return;
		}
		
		ConnectionSetup connectionSetup = new ConnectionSetup();
	    Connection conn = connectionSetup.connectionEstablish();
	    try {
	    	String sql = "insert into coursecatalog values (?,?,?,?,'NA')";
		    PreparedStatement stmt = conn.prepareStatement(sql);
	    	stmt.setInt(1, course.getCourseID());
		    stmt.setString(2, course.getCourseName());
		    stmt.setInt(3, course.getProfessorAllotted());
		    stmt.setInt(4, course.getCredits());
		    int i=stmt.executeUpdate(); 
			if(i==0) {
				System.out.println("Error in adding course in course catalog");
			} else {
				System.out.println("Course - "+course.getCourseID()+" added successfully");
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
		
		ConnectionSetup connectionSetup = new ConnectionSetup();
	    Connection conn = connectionSetup.connectionEstablish();
	    try {
	    	String sql = "delete from courseCatalog where courseid = ?";
	    	PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, courseId);
		    int i = stmt.executeUpdate();
		    if(i==0) {
				 System.out.println("Error in dropping course - "+courseId);
			} else {
				System.out.println("Course - "+courseId+" dropped successfully");
			}
	    }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connectionSetup.connectionClose(conn);
		}
	}
}
