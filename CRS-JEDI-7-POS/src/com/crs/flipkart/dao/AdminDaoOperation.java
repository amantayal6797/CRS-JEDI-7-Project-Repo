/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

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
	private static Logger logger = Logger.getLogger(AdminDaoOperation.class);
	
	public Admin getAdmin(int userID) {
		DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
//		 String sql1 = "select * from user where userid = ?";
//		 String sql2 = "select * from student where userid = ?";
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
				// TODO Auto-generated catch block
				logger.debug("Exception raised: "+e.getMessage());
			}
			connectObj.connectionClose(conn2);
			return admin;
	}
	
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
				logger.error("Error in adding course in course catalog");
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
		    
		    /*
		    if(i==0) {
				 logger.error("Error in dropping course - "+courseId);
			} else {
				logger.info("Course - "+courseId+" dropped successfully");
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
			logger.debug("Exception raised: "+e.getMessage());
		} finally {
			connectionSetup.connectionClose(conn);
		}
	}
}
