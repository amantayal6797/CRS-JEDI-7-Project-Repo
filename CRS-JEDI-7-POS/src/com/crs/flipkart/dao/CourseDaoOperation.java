package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.utils.ConnectionSetup;
public class CourseDaoOperation {
	
	 public ArrayList <Integer>  getEnrolledStudents(int courseId){
		ArrayList<Integer> studentList=new ArrayList<Integer>();
		ConnectionSetup connectObj=new ConnectionSetup();
		Connection conn = connectObj.connectionEstablish();
		String sql = "select * from registeredcourses";
		try {
			PreparedStatement stmt=conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				if(rs.getInt("courseId")==courseId)
					studentList.add(rs.getInt("userId"));
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
		 ConnectionSetup connectObj=new ConnectionSetup();
		 Connection conn1 = connectObj.connectionEstablish();
		 String sql = "select * from coursecatalog";
		 try {
			 PreparedStatement stmt=conn1.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					if(rs.getInt("courseId")==courseId) {
					course.setCourseID(rs.getInt("courseId"));
					course.setCourseName(rs.getString("courseName"));
					course.setCredits(rs.getInt("Credits"));
					course.setPrerequisites(rs.getString("Prerequisites"));
					course.setProfessorAllotted(rs.getInt("professorAlloted"));
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
		 ConnectionSetup connectObj=new ConnectionSetup();
		 Connection conn2 = connectObj.connectionEstablish();
		 String sql = "select * from coursecatalog";
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					Course course=new Course();
					course.setCourseID(rs.getInt("courseId"));
					course.setCourseName(rs.getString("courseName"));
					course.setCredits(rs.getInt("Credits"));
					course.setPrerequisites(rs.getString("Prerequisites"));
					course.setProfessorAllotted(rs.getInt("professorAlloted"));
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
		 ConnectionSetup connectObj=new ConnectionSetup();
			Connection conn = connectObj.connectionEstablish();
			String sql = "select * from registeredcourses";
			try {
				PreparedStatement stmt=conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					if(rs.getInt("userId")==studentId) {
						RegisteredCourse regCourse=new RegisteredCourse();
						regCourse.setCourseID(rs.getInt("courseId"));
						regCourse.setGrade(rs.getString("Grade"));
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
		 ConnectionSetup connectObj=new ConnectionSetup();
		 Connection conn2 = connectObj.connectionEstablish();
		 String sql = "select * from coursecatalog";
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					if(rs.getInt("courseId")== courseId)
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
		 ConnectionSetup connectObj=new ConnectionSetup();
		 Connection conn2 = connectObj.connectionEstablish();
		 String sql = "insert into registeredcourses values(?,?,'NA')";
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(sql);
			 stmt.setInt(1, courseId);
			 stmt.setInt(2, studentId);
			 
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
		 
		 ConnectionSetup connectObj=new ConnectionSetup();
		 Connection conn2 = connectObj.connectionEstablish();
		 String sql = "delete from registeredcourses where courseId=? and userId=?";
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
		 ConnectionSetup connectObj=new ConnectionSetup();
		 Connection conn2 = connectObj.connectionEstablish();
		 String sql = "select * from coursecatalog where professorAlloted=?";
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(sql);
			 stmt.setInt(1, userId);
			 ResultSet rs = stmt.executeQuery();
			 while(rs.next()) {
					Course course=new Course();
					course.setCourseID(rs.getInt("courseId"));
					course.setCourseName(rs.getString("courseName"));
					course.setCredits(rs.getInt("Credits"));
					course.setPrerequisites(rs.getString("Prerequisites"));
					course.setProfessorAllotted(rs.getInt("professorAlloted"));
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
		 ConnectionSetup connectObj=new ConnectionSetup();
		 Connection conn2 = connectObj.connectionEstablish();
		 String sql = "select * from coursecatalog where professorAlloted=0";
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery();
			 while(rs.next()) {
					Course course=new Course();
					course.setCourseID(rs.getInt("courseId"));
					course.setCourseName(rs.getString("courseName"));
					course.setCredits(rs.getInt("Credits"));
					course.setPrerequisites(rs.getString("Prerequisites"));
					course.setProfessorAllotted(rs.getInt("professorAlloted"));
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
		 
		 ConnectionSetup connectObj=new ConnectionSetup();
		 Connection conn2 = connectObj.connectionEstablish();
		 String sql = "update coursecatalog set professorAlloted = ? where courseId=?";
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
		 ConnectionSetup connectObj=new ConnectionSetup();
		 Connection conn2 = connectObj.connectionEstablish();
		 String sql = "update registeredcourses set Grade = ? where courseId = ? and userId = ? ";
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
