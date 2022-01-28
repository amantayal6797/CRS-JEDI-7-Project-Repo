package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.constants.SQLQueryConstant;
import com.crs.flipkart.exception.ErrorInRegisteringStudentException;
import com.crs.flipkart.exception.ErrorInSettingPaymentStatusException;
import com.crs.flipkart.exception.ErrorInSettingRegistrationStatusException;
import com.crs.flipkart.utils.DBUtils;

public class StudentDaoOperation implements StudentDaoOperationInterface {
	UserDaoOperationInterface userDaoOperation = new UserDaoOperation();
	
	public void registerStudent (Student student) {
		DBUtils connectionSetup = new DBUtils();
	    Connection conn = connectionSetup.connectionEstablish();
	    try {
	    	userDaoOperation.registerUser(student.getUserId(), student.getPassword(), student.getIsApproved());
//	    	String sql = "insert into student values (?,?,?,?,?,?,?,?,?,?)";
	    	PreparedStatement stmt = conn.prepareStatement(SQLQueryConstant.ADD_COURSE_QUERY);
		    stmt.setInt(1, student.getUserId());
		    stmt.setString(2, student.getUserName());
		    stmt.setString(3, student.getEmail());
		    stmt.setString(4, student.getAddress());
		    stmt.setInt(5, student.getAge());
		    stmt.setString(6, student.getGender());
		    stmt.setString(7, student.getContact());
		    stmt.setInt(8, (student.isRegistered())? 1: 0);
		    stmt.setString(9, student.getBranch());
		    stmt.setInt(10, (student.getPaymentStatus())? 1: 0);
		    int i = stmt.executeUpdate();
		    /*
		    if(i==0) {
				System.out.println("Error in registering student");
			}
			*/
		    try{
		    	if(i==0) 
		    		throw new ErrorInRegisteringStudentException();
		    }catch(ErrorInRegisteringStudentException e){
		    	System.out.println(e.getMessage());
		    }
		    
//		    sql="insert into role values(?,'Student')";
		    stmt=conn.prepareStatement(SQLQueryConstant.ADD_STUDENT_ROLE);
		    stmt.setInt(1, student.getUserId());
		    i = stmt.executeUpdate();
		    /*
		    if(i==0) {
				System.out.println("Error in registering student");
			} else {
				System.out.println("student - "+student.getUserId()+" registered successfully & your approval is pending by admin");
				NotificationDaoOperationInterface notificationOper = new NotificationDaoOperation();
				notificationOper.insertStatus(student.getUserId());
			}
			*/
		    
		    try{
		    	if(i==0) 
		    		throw new ErrorInRegisteringStudentException();
				else {
					System.out.println("student - "+student.getUserId()+" registered successfully & your approval is pending by admin");
					NotificationDaoOperationInterface notificationOper = new NotificationDaoOperation();
					notificationOper.insertStatus(student.getUserId());
				}	
		    }catch(ErrorInRegisteringStudentException e){
		    	System.out.println(e.getMessage());
		    }
		    
	    }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connectionSetup.connectionClose(conn);	
		}
	}
	
	
	
	public boolean getPaymentStatus (int studentId) {
		DBUtils connectionSetup = new DBUtils();
	    Connection conn = connectionSetup.connectionEstablish();
//		String sql = "select paymentstatus from student where userid = ?";
		try {
		    PreparedStatement stmt=conn.prepareStatement(SQLQueryConstant.GET_PAYMENT_STATUS);
			stmt.setInt(1, studentId);
			ResultSet rs=stmt.executeQuery(); 
			while(rs.next()) {
				if(rs.getInt("paymentstatus")==1) {
					return true;
				}
			}
			
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		    e.printStackTrace();
		    return false;
		} finally {
			connectionSetup.connectionClose(conn);	
		}
	}
	
	public boolean setPaymentStatus (int studentId) {
		DBUtils connectionSetup = new DBUtils();
	    Connection conn = connectionSetup.connectionEstablish();
//		String sql = "update student set paymentstatus=1 where userid = ?";
		try {
		    PreparedStatement stmt=conn.prepareStatement(SQLQueryConstant.SET_PAYMENT_STATUS);
			stmt.setInt(1, studentId);
			int i=stmt.executeUpdate(); 
			/*
			if(i==0) {
				System.out.println("Error in setting payment status for student-"+studentId);
				return false;
			}
			*/
			try {
				if(i==0) 
					throw new ErrorInSettingPaymentStatusException(studentId);
			}
			catch(ErrorInSettingPaymentStatusException e){
				System.out.println(e.getMessage());
				return false;
			}
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		    e.printStackTrace();
		    return false;
		} finally {
			connectionSetup.connectionClose(conn);	
		}
	}
	
	public Student showStudent(int studentId) {
		DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
//		 String sql1 = "select * from user where userid = ?";
//		 String sql2 = "select * from student where userid = ?";
		 Student student=new Student();
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(SQLQueryConstant.GET_USER_DETAIL);
			 stmt.setInt(1, studentId);
			 ResultSet rs=stmt.executeQuery(); 
			 while(rs.next()) {
				student.setUserId(rs.getInt("userID"));
				student.setPassword(rs.getString("Password"));
				if(rs.getInt("isApproved")==1)
					student.setIsApproved(true);
				else
					student.setIsApproved(false);
				
			 }
			 PreparedStatement stmt2=conn2.prepareStatement(SQLQueryConstant.GET_STUDENT_DETAIL);
			 stmt.setInt(1, studentId);
			 ResultSet rs2=stmt2.executeQuery(); 
			 while(rs2.next()) {
				 student.setUserName(rs.getString("username"));
				 student.setEmail(rs.getString("email"));
				student.setAddress(rs.getString("address"));
				student.setAge(rs.getInt("age"));
				student.setGender(rs.getString("gender"));
				student.setContact(rs.getString("contact"));
				 if(rs.getInt("isregistered")==1)
					 student.setRegistered(true);
					else
						student.setRegistered(false);
				student.setBranch(rs.getString("branch"));
				 if(rs.getInt("paymentStatus")==1)
					 student.setPaymentStatus(true);
					else
						student.setPaymentStatus(false);
			 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connectObj.connectionClose(conn2);
			return student;
	}
	
	public void setRegistration(int studentId) {
		DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
//		 String sql = "update student set isregistered=1 where userid = ?";
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(SQLQueryConstant.SET_REGISTERED);
			 stmt.setInt(1, studentId);
			 int i=stmt.executeUpdate(); 
			 /*
			 if(i==0)
				 System.out.println("Error in setting registration status");
			*/
			 
			try {
				if(i==0)
					throw new ErrorInSettingRegistrationStatusException();
			}catch(ErrorInSettingRegistrationStatusException e) {
				System.out.println(e.getMessage());
			}
			 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connectObj.connectionClose(conn2);
		
	}
	public boolean isRegistered(int studentId) {
		DBUtils connectObj=new DBUtils();
		 Connection conn2 = connectObj.connectionEstablish();
//		 String sql = "select isregistered from student where userid = ?";
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(SQLQueryConstant.IS_REGISTERED);
			 stmt.setInt(1, studentId);
			 ResultSet rs=stmt.executeQuery(); 
			 while(rs.next()) {
				if(rs.getInt("isregistered")==1)
					return true;
				else
					return false;
			 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connectObj.connectionClose(conn2);
			return false;
	}
}
