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
public class StudentOperation implements StudentOperationInterface {
	
	public void registerStudent(int userId, String password, String userName, String address, int age, String branch, String contact, String email, String gender) {
		
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
		student.setRollNo(AuthorizationDB.listOfStudents.size()+1);
		
		AuthorizationDB.listOfStudents.add(student);
	}
	
	public void showstudent() {
		for(int i=0;i<AuthorizationDB.listOfStudents.size();i++) {
			System.out.println(AuthorizationDB.listOfStudents.get(i).getUserId());
		}
	}
	
	public void setRegistration(int studentId) {
		for(int i=0;i<AuthorizationDB.listOfStudents.size();i++) {
			if(AuthorizationDB.listOfStudents.get(i).getUserId()==studentId){
				Student student=AuthorizationDB.listOfStudents.get(i);
				AuthorizationDB.listOfStudents.remove(i);
				student.setRegistered(true);
				AuthorizationDB.listOfStudents.add(student);
				break;
			}
		}
	}
	
	
	
}
