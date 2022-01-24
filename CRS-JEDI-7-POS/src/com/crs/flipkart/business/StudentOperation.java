/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.constants.AuthorizationDB;

/**
 * @author aditya
 *
 */
public class StudentOperation extends AuthorizationDB {
	
	public void registerStudent(int userId, String password, String userName, String address, int age, String branch, String contact, String email, String gender, int registeredStudentCount) {
		Student student=new Student();
		student.setUserId(userId);
		student.setPassword(password);
		student.setUserName(userName);
		student.setAddress(address);
		student.setAge(age);
		student.setBranch(branch);
		student.setContact(contact);
		student.setEmail(email);
		student.setGender(gender);
		student.setIsApproved(true);
		student.setPaymentStatus(false);
		student.setRegistered(false);
		student.setRole("Student");
		student.setRollNo(registeredStudentCount+1);
		
		listOfStudents[registeredStudentCount] = student;
	}
}
