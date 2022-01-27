/**
 * 
 */
package com.crs.flipkart.business;

import java.util.Scanner;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.dao.StudentDaoOperation;
import com.crs.flipkart.dao.StudentDaoOperationInterface;
import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.dao.UserDaoOperationInterface;

/**
 * @author aditya
 *
 */
public class StudentOperation implements StudentOperationInterface {
	
	UserDaoOperationInterface userDaoOperation = new UserDaoOperation();
	StudentDaoOperationInterface studentDaoOperation = new StudentDaoOperation();
	
	public void registerStudent() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter User ID: ");
		int userId = sc.nextInt();

		Student student = new Student();
		student.setUserId(userId);
		
		if (userDaoOperation.getUser(student.getUserId())!=null) {
			System.out.println("User already exists with this userId");
			return;
		}
		
		System.out.println("Enter Username: ");
		String userName = sc.next();
		student.setUserName(userName);
		System.out.println("Enter Password: ");
		String password = sc.next();
		student.setPassword(password);
		student.setRole("Student");
		System.out.println("Enter Email: ");
		String email = sc.next();
		student.setEmail(email);
		student.setIsApproved(false);
		System.out.println("Enter Address: ");
		String address = sc.next();
		student.setAddress(address);
		System.out.println("Enter Age: ");
		int age = sc.nextInt();
		student.setAge(age);
		System.out.println("Enter Gender: ");
		String gender = sc.next();
		student.setGender(gender);
		System.out.println("Enter Contact: ");
		String contact = sc.next();
		student.setContact(contact);
		student.setRegistered(false);
		System.out.println("Enter Branch: ");
		String branch = sc.next();
		student.setBranch(branch);
		student.setPaymentStatus(false);
		
		studentDaoOperation.registerStudent(student);
	}
	
//	public void showstudent() {
//		for(int i=0;i<AuthorizationDB.listOfStudents.size();i++) {
//			System.out.println(AuthorizationDB.listOfStudents.get(i).getUserId());
//		}
//	}
//	
//	public void setRegistration(int studentId) {
//		for(int i=0;i<AuthorizationDB.listOfStudents.size();i++) {
//			if(AuthorizationDB.listOfStudents.get(i).getUserId()==studentId){
//				Student student=AuthorizationDB.listOfStudents.get(i);
//				AuthorizationDB.listOfStudents.remove(i);
//				student.setRegistered(true);
//				AuthorizationDB.listOfStudents.add(student);
//				break;
//			}
//		}
//	}
	
	
	
}
