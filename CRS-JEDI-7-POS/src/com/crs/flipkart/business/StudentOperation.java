/**
 * 
 */
package com.crs.flipkart.business;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.dao.StudentDaoOperation;
import com.crs.flipkart.dao.StudentDaoOperationInterface;
import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.dao.UserDaoOperationInterface;

/**
 * @author aditya.gupta3
 *
 */
public class StudentOperation implements StudentOperationInterface {

	UserDaoOperationInterface userDaoOperation = new UserDaoOperation();
	StudentDaoOperationInterface studentDaoOperation = new StudentDaoOperation();
	private static Logger logger = Logger.getLogger(StudentOperation.class);
	@Override
	public void registerStudent() {
		Scanner sc = new Scanner(System.in);
		
		logger.info("Enter User ID: ");
		int userId = sc.nextInt();

		Student student = new Student();
		student.setUserId(userId);
		
		if (userDaoOperation.getUser(student.getUserId())!=null) {
			logger.error("User already exists with this userId");
			return;
		}
		
		logger.info("Enter Username: ");
		String userName = sc.next();
		student.setUserName(userName);
		logger.info("Enter Password: ");
		String password = sc.next();
		student.setPassword(password);
		student.setRole("Student");
		logger.info("Enter Email: ");
		String email = sc.next();
		student.setEmail(email);
		student.setIsApproved(false);
		logger.info("Enter Address: ");
		String address = sc.next();
		student.setAddress(address);
		logger.info("Enter Age: ");
		int age = sc.nextInt();
		student.setAge(age);
		logger.info("Enter Gender: ");
		String gender = sc.next();
		student.setGender(gender);
		logger.info("Enter Contact: ");
		String contact = sc.next();
		student.setContact(contact);
		student.setRegistered(false);
		logger.info("Enter Branch: ");
		String branch = sc.next();
		student.setBranch(branch);
		student.setPaymentStatus(false);
		
		studentDaoOperation.registerStudent(student);
	}

}
