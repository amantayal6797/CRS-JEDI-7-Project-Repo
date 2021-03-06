package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.constants.SQLQueryConstant;
import com.crs.flipkart.exception.ErrorInAddingCourseException;
import com.crs.flipkart.exception.ErrorInAssigningCourseException;
import com.crs.flipkart.exception.ErrorInDropingCourseException;
import com.crs.flipkart.exception.ErrorInRegisteringCourseException;
import com.crs.flipkart.utils.DBUtils;

public class CourseDaoOperation implements CourseDaoOperationInterface {
	private static Logger logger = Logger.getLogger(CourseDaoOperation.class);
	 public ArrayList <Integer>  getEnrolledStudents(int courseId){
		ArrayList<Integer> studentList=new ArrayList<Integer>();
		DBUtils connectObj=new DBUtils();
		Connection conn = connectObj.connectionEstablish();
		try {
			PreparedStatement stmt=conn.prepareStatement(SQLQueryConstant.GET_ALL_REGISTERED_COURSES);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				if(rs.getInt("courseid")==courseId)
					studentList.add(rs.getInt("userid"));
			}
		} catch (SQLException e) {
			logger.debug("Exception raised: "+e.getMessage());
		}
		connectObj.connectionClose(conn);
		return studentList;
	}
	 
	 public Course getCourse(int courseId) {
		 Course course=new Course();
		 DBUtils connectObj=new DBUtils();
		 Connection conn1 = connectObj.connectionEstablish();
		 try {
			 PreparedStatement stmt=conn1.prepareStatement(SQLQueryConstant.GET_ALL_COURSES);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					if(rs.getInt("courseid")==courseId) {
					course.setCourseID(rs.getInt("courseid"));
					course.setCourseName(rs.getString("coursename"));
					course.setCredits(rs.getInt("credit"));
					course.setPrerequisites(rs.getString("prerequisites"));
					course.setProfessorAllotted(rs.getInt("professoralloted"));
					break;
					}
				}
			} catch (SQLException e) {
				logger.debug("Exception raised: "+e.getMessage());
			}
			connectObj.connectionClose(conn1);
		 return course;
	 }
	 
	 public ArrayList<Course> viewCourses(){
		 ArrayList<Course> catalog=new ArrayList<Course>();
		 DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(SQLQueryConstant.GET_ALL_COURSES);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					Course course=new Course();
					course.setCourseID(rs.getInt("courseid"));
					course.setCourseName(rs.getString("coursename"));
					course.setCredits(rs.getInt("credit"));
					course.setPrerequisites(rs.getString("prerequisites"));
					course.setProfessorAllotted(rs.getInt("professoralloted"));
					if(getEnrolledStudents(course.getCourseID()).size() < 10)
						catalog.add(course);
				}
			} catch (SQLException e) {
				logger.debug("Exception raised: "+e.getMessage());
			}
			connectObj.connectionClose(conn2);
		return catalog;
		 
	 }
	 
	 public ArrayList<RegisteredCourse> getRegisteredCourses(int studentId){
		 ArrayList<RegisteredCourse> listOfRegisteredCourses= new ArrayList<RegisteredCourse>();
		 DBUtils connectObj=new DBUtils();
			Connection conn = connectObj.connectionEstablish();
			try {
				PreparedStatement stmt=conn.prepareStatement(SQLQueryConstant.GET_ALL_REGISTERED_COURSES);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					if(rs.getInt("userid")==studentId) {
						RegisteredCourse regCourse=new RegisteredCourse();
						regCourse.setCourseID(rs.getInt("courseid"));
						regCourse.setGrade(rs.getString("grade"));
						regCourse.setUserId(studentId);
						listOfRegisteredCourses.add(regCourse);
					}
				}
			} catch (SQLException e) {
				logger.debug("Exception raised: "+e.getMessage());
			}
			connectObj.connectionClose(conn);
		 return listOfRegisteredCourses;
	 }
	 
	 public boolean verifyCourse(int courseId) {
		 DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(SQLQueryConstant.GET_ALL_COURSES);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					if(rs.getInt("courseid")== courseId)
						return true;
				}
			} catch (SQLException e) {
				logger.debug("Exception raised: "+e.getMessage());
			}
			connectObj.connectionClose(conn2);
		 return false;
	 }
	 
	 public void addCourse(int studentId,int courseId) {
		 DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(SQLQueryConstant.ADD_REGISTERED_COURSE);
			 stmt.setInt(2, courseId);
			 stmt.setInt(1, studentId);
			 
			 int i=stmt.executeUpdate(); 
			 
			 
			  try {
			    	if(i==0) 
			    		throw new ErrorInAddingCourseException(2);
			    }
			    catch(ErrorInAddingCourseException e){
			    	System.out.println(e.getMessage());
			    }
			} catch (SQLException e) {
				logger.debug("Exception raised: "+e.getMessage());
			}
			connectObj.connectionClose(conn2);
		 
	 }
	 public void dropCourse(int courseId,int studentId) {
		 
		 DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(SQLQueryConstant.DELETE_REGISTERED_COURSE);
			 stmt.setInt(1, courseId);
			 stmt.setInt(2, studentId);
			 int i=stmt.executeUpdate(); 
			 
			  try {
			    	if(i==0) 
			    		throw new ErrorInDropingCourseException(2);
			    }
			    catch(ErrorInDropingCourseException e){
			    	System.out.println(e.getMessage());
			    } 
			 
			} catch (SQLException e) {
				logger.debug("Exception raised: "+e.getMessage());
			}
			connectObj.connectionClose(conn2);
		 		 
	 }
	 
	 public ArrayList<Course> getProfessorCourses(int userId){
		 ArrayList<Course> catalog=new ArrayList<Course>();
		 DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(SQLQueryConstant.GET_PROFESSOR_COURSES);
			 stmt.setInt(1, userId);
			 ResultSet rs = stmt.executeQuery();
			 while(rs.next()) {
					Course course=new Course();
					course.setCourseID(rs.getInt("courseid"));
					course.setCourseName(rs.getString("coursename"));
					course.setCredits(rs.getInt("credit"));
					course.setPrerequisites(rs.getString("prerequisites"));
					course.setProfessorAllotted(rs.getInt("professoralloted"));
					catalog.add(course);
				}
			} catch (SQLException e) {
				logger.debug("Exception raised: "+e.getMessage());
			}
			connectObj.connectionClose(conn2);
		return catalog;
	 }
	 public ArrayList<Course> getUnregisteredCourses(int userId){
		 ArrayList<Course> catalog=new ArrayList<Course>();
		 DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(SQLQueryConstant.GET_UNALLOTED_COURSES);
			 ResultSet rs = stmt.executeQuery();
			 while(rs.next()) {
					Course course=new Course();
					course.setCourseID(rs.getInt("courseid"));
					course.setCourseName(rs.getString("coursename"));
					course.setCredits(rs.getInt("credit"));
					course.setPrerequisites(rs.getString("prerequisites"));
					course.setProfessorAllotted(rs.getInt("professoralloted"));
					catalog.add(course);
				}
			} catch (SQLException e) {
				logger.debug("Exception raised: "+e.getMessage());
			}
			connectObj.connectionClose(conn2);
		return catalog;
	 }
	 public void setRegisterCourse(int userID,int courseId) {
		 
		 DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(SQLQueryConstant.ALLOT_COURSE);
			 stmt.setInt(2, courseId);
			 stmt.setInt(1, userID);
			 
			 int i=stmt.executeUpdate(); 
			 
			 try{
			  	if(i==0) 
			    	throw new ErrorInRegisteringCourseException();
			 }
			 catch(ErrorInRegisteringCourseException e){
			    System.out.println(e.getMessage());
			 } 
			  
			
			} catch (SQLException e) {
				logger.debug("Exception raised: "+e.getMessage());
			}
			connectObj.connectionClose(conn2);
	 }
	 
	 public void setGrade(int studentId,int courseId,String grade) {
		 DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(SQLQueryConstant.ASSIGN_GRADE);
			 stmt.setString(1, grade);
			 stmt.setInt(2, courseId);
			 stmt.setInt(3, studentId);
			 
			 int i=stmt.executeUpdate(); 
			 try{
				if(i==0) 
				   	throw new ErrorInAssigningCourseException();
				 }
			 catch(ErrorInAssigningCourseException e){
			    System.out.println(e.getMessage());
			 }
			} catch (SQLException e) {
				logger.debug("Exception raised: "+e.getMessage());
			}
			connectObj.connectionClose(conn2);
	 }
}
