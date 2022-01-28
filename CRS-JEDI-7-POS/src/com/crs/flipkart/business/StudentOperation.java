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
import com.crs.flipkart.exception.UserAlreadyExistsException;

/**
 * @author aditya.gupta3
 *
 */
public class StudentOperation implements StudentOperationInterface {

	UserDaoOperationInterface userDaoOperation = new UserDaoOperation();
	StudentDaoOperationInterface studentDaoOperation = new StudentDaoOperation();
	
	@Override
	public void registerStudent() {
		try {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter User ID: ");
		int userId = sc.nextInt();

		Student student = new Student();
		student.setUserId(userId);
		
		if (userDaoOperation.getUser(student.getUserId())!=null) {
			throw new UserAlreadyExistsException(userId);
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
		}catch(UserAlreadyExistsException e) {
			System.out.println(e.getMessage());
		}
	}

}
