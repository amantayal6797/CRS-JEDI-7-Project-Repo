/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.constants.SQLQueryConstant;
import com.crs.flipkart.utils.DBUtils;

/**
 * @author aditya.gupta3
 *
 */
public class AdminDaoOperation implements AdminDaoOperationInterface {
	
	CourseDaoOperation courseDaoOperation = new CourseDaoOperation();
	private static Logger logger = Logger.getLogger(AdminDaoOperation.class);
	public void addCourse (Course course) {
		if(courseDaoOperation.verifyCourse(course.getCourseID())) {
			logger.error("Course ID already exist");
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
			if(i==0) {
				logger.error("Error in adding course in course catalog");
			} else {
				logger.info("Course - "+course.getCourseID()+" added successfully");
			}
	    }catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.debug("Exception raised: "+e.getMessage());
		} finally {
			connectionSetup.connectionClose(conn);	
		}
	}
	
	public void dropCourse(int courseId) {
		if(!courseDaoOperation.verifyCourse(courseId)) {
			logger.error("Course ID does not exist");
			return;
		}
		
		DBUtils connectionSetup = new DBUtils();
	    Connection conn = connectionSetup.connectionEstablish();
	    try {
//	    	String sql = "delete from course where courseid = ?";
	    	PreparedStatement stmt = conn.prepareStatement(SQLQueryConstant.DELETE_COURSE_QUERY);
		    stmt.setInt(1, courseId);
		    int i = stmt.executeUpdate();
		    if(i==0) {
				 logger.error("Error in dropping course - "+courseId);
			} else {
				logger.info("Course - "+courseId+" dropped successfully");
			}
	    }catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.debug("Exception raised: "+e.getMessage());
		} finally {
			connectionSetup.connectionClose(conn);
		}
	}
}
