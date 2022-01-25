package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.utils.ConnectionSetup;

public class StudentDaoOperation {
	
	public boolean getPaymentStatus (int studentId) {
		ConnectionSetup connectionSetup = new ConnectionSetup();
	    Connection conn = connectionSetup.connectionEstablish();
		String sql = "select paymentStatus from student where userId = ?";
		try {
		    PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			ResultSet rs=stmt.executeQuery(); 
			while(rs.next()) {
				if(rs.getInt("paymentStatus")==1) {
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
		ConnectionSetup connectionSetup = new ConnectionSetup();
	    Connection conn = connectionSetup.connectionEstablish();
		String sql = "update student set paymentStatus=1 where userId = ?";
		try {
		    PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			int i=stmt.executeUpdate(); 
			if(i==0) {
				System.out.println("Error in setting payment status for student-"+studentId);
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
		ConnectionSetup connectObj=new ConnectionSetup();
		 Connection conn2 = connectObj.connectionEstablish();
		 String sql1 = "select * from user where userId = ?";
		 String sql2 = "select * from student where userId = ?";
		 Student student=new Student();
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(sql1);
			 stmt.setInt(1, studentId);
			 ResultSet rs=stmt.executeQuery(); 
			 while(rs.next()) {
				student.setUserId(rs.getInt("userID"));
				student.setUserName(rs.getString("userName"));
				student.setPassword(rs.getString("Password"));
				student.setRole(rs.getString("Role"));
				student.setEmail(rs.getString("Email"));
				//student.setIsApproved(boolean(rs.getInt("isApproved")));
				student.setAddress(rs.getString("Address"));
				student.setAge(rs.getInt("Age"));
				student.setGender(rs.getString("Gender"));
				student.setContact(rs.getString("Contact"));
				student.setNationality(rs.getString("Nationality"));
				if(rs.getInt("isApproved")==1)
					student.setIsApproved(true);
				else
					student.setIsApproved(false);
				
			 }
			 PreparedStatement stmt2=conn2.prepareStatement(sql2);
			 stmt.setInt(1, studentId);
			 ResultSet rs2=stmt2.executeQuery(); 
			 while(rs2.next()) {
				 if(rs.getInt("isRegistered")==1)
					 student.setRegistered(true);
					else
						student.setRegistered(false);
				
				student.setBranch(sql2);
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
		ConnectionSetup connectObj=new ConnectionSetup();
		 Connection conn2 = connectObj.connectionEstablish();
		 String sql = "update student set isRegistered=1 where userId = ?";
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(sql);
			 stmt.setInt(1, studentId);
			 int i=stmt.executeUpdate(); 
			 if(i==0)
				 System.out.println("Error in setting registration status");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connectObj.connectionClose(conn2);
		
	}
	public boolean isRegistered(int studentId) {
		ConnectionSetup connectObj=new ConnectionSetup();
		 Connection conn2 = connectObj.connectionEstablish();
		 String sql = "select isRegistered from student where userId = ?";
		 try {
			 PreparedStatement stmt=conn2.prepareStatement(sql);
			 stmt.setInt(1, studentId);
			 ResultSet rs=stmt.executeQuery(); 
			 while(rs.next()) {
				if(rs.getInt("isRegistered")==1)
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
