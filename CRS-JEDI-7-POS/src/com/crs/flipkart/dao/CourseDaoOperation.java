package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.utils.DBUtils;
public class CourseDaoOperation implements CourseDaoOperationInterface {
	
	 public ArrayList <Integer>  getEnrolledStudents(int courseId){
		ArrayList<Integer> studentList=new ArrayList<Integer>();
		DBUtils connectObj=new DBUtils();
		Connection conn = connectObj.connectionEstablish();
		String sql = "select * from registeredcourse";
		try {
			PreparedStatement stmt=conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				if(rs.getInt("courseid")==courseId)
					studentList.add(rs.getInt("userid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connectObj.connectionClose(conn);
		return studentList;
	}
	 
	 public Course getCourse(int courseId) {
		 Course course=new Course();
		 DBUtils connectObj=new DBUtils();
		 Connection conn1 = connectObj.connectionEstablish();
		 String sql = "select * from course";
		 try {
			 PreparedStatement stmt=conn1.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery(sql);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connectObj.connectionClose(conn1);
		 return course;
	 }
	 
	 public ArrayList<Course> viewCourses(){
		 ArrayList<Course> catalog=new ArrayList<Course>();
		 DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
		 String sql = "select * from course";
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery(sql);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connectObj.connectionClose(conn2);
		return catalog;
		 
	 }
	 
	 public ArrayList<RegisteredCourse> getRegisteredCourses(int studentId){
		 ArrayList<RegisteredCourse> listOfRegisteredCourses= new ArrayList<RegisteredCourse>();
		 DBUtils connectObj=new DBUtils();
			Connection conn = connectObj.connectionEstablish();
			String sql = "select * from registeredcourse";
			try {
				PreparedStatement stmt=conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery(sql);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connectObj.connectionClose(conn);
		 return listOfRegisteredCourses;
	 }
	 
	 public boolean verifyCourse(int courseId) {
		 DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
		 String sql = "select * from course";
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					if(rs.getInt("courseid")== courseId)
						return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connectObj.connectionClose(conn2);
		 return false;
	 }
	 
	 public void addCourse(int studentId,int courseId) {
		 DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
		 String sql = "insert into registeredcourse values(?,?,'NA')";
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(sql);
			 stmt.setInt(2, courseId);
			 stmt.setInt(1, studentId);
			 
			 int i=stmt.executeUpdate(); 
			 if(i==0)
				 System.out.println("Error in adding course");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connectObj.connectionClose(conn2);
		 
	 }
	 public void dropCourse(int courseId,int studentId) {
		 
		 DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
		 String sql = "delete from registeredcourse where courseId=? and userId=?";
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(sql);
			 stmt.setInt(1, courseId);
			 stmt.setInt(2, studentId);
			 int i=stmt.executeUpdate(); 
			 if(i==0)
				 System.out.println("Error in dropping course");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connectObj.connectionClose(conn2);
		 		 
	 }
	 
	 public ArrayList<Course> getProfessorCourses(int userId){
		 ArrayList<Course> catalog=new ArrayList<Course>();
		 DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
		 String sql = "select * from course where professoralloted=?";
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(sql);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connectObj.connectionClose(conn2);
		return catalog;
	 }
	 public ArrayList<Course> getUnregisteredCourses(int userId){
		 ArrayList<Course> catalog=new ArrayList<Course>();
		 DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
		 String sql = "select * from course where professoralloted=0";
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(sql);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connectObj.connectionClose(conn2);
		return catalog;
	 }
	 public void setRegisterCourse(int userID,int courseId) {
		 
		 DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
		 String sql = "update course set professoralloted = ? where courseId=?";
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(sql);
			 stmt.setInt(2, courseId);
			 stmt.setInt(1, userID);
			 
			 int i=stmt.executeUpdate(); 
			 if(i==0)
				 System.out.println("Error in Registering course");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connectObj.connectionClose(conn2);
	 }
	 
	 public void setGrade(int studentId,int courseId,String grade) {
		 DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
		 String sql = "update registeredcourse set grade = ? where courseid = ? and userid = ? ";
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(sql);
			 stmt.setString(1, grade);
			 stmt.setInt(2, courseId);
			 stmt.setInt(3, studentId);
			 
			 int i=stmt.executeUpdate(); 
			 if(i==0)
				 System.out.println("Error in Assigning Grade");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connectObj.connectionClose(conn2);
	 }
}
