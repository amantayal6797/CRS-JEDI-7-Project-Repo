/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.dao.AdminDaoOperation;
import com.crs.flipkart.dao.AdminDaoOperationInterface;
import com.crs.flipkart.dao.NotificationDaoOperation;
import com.crs.flipkart.dao.NotificationDaoOperationInterface;
import com.crs.flipkart.dao.ProfessorDaoOperation;
import com.crs.flipkart.dao.ProfessorDaoOperationInterface;
import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.dao.UserDaoOperationInterface;
import com.crs.flipkart.exception.UserAlreadyExistsException;
import com.crs.flipkart.exception.UserDoesNotExistException;

/**
 * @author aditya.gupta3
 *
 */
public class AdminOperation implements AdminOperationInterface {
	
	UserDaoOperationInterface userDaoOperation = new UserDaoOperation();
	AdminDaoOperationInterface adminDaoOperation = new AdminDaoOperation();
	ProfessorDaoOperationInterface professorDaoOperation = new ProfessorDaoOperation();
	private static Logger logger = Logger.getLogger(AdminOperation.class);

	public void addCourse() {
		Scanner sc = new Scanner(System.in);
		logger.info("Enter Course ID to add: ");
		int courseID = sc.nextInt();
		logger.info("Enter Course Name to add: ");
		String courseName = sc.next();
		logger.info("Enter Course Credits: ");
		int credits = sc.nextInt();
		
		Course course = new Course();
		course.setCourseID(courseID);
		course.setCourseName(courseName);
		course.setProfessorAllotted(0);
		course.setCredits(credits);
		
		adminDaoOperation.addCourse(course);
	}
	
	public void dropCourse() {
		Scanner sc = new Scanner(System.in);
		logger.info("Enter Course ID to drop: ");
		int courseID = sc.nextInt();
		
		adminDaoOperation.dropCourse(courseID);
	}
	
	public void approveUser() {
		ArrayList<Integer> unapprovedStudents=userDaoOperation.getUnapprovedStudents();
		logger.info("List of Unapproved Students");
		unapprovedStudents.forEach(System.out::println);
		
		//for(int i:unapprovedStudents) {
			//logger.info(i);
		//}
		
		Scanner sc = new Scanner(System.in);
		logger.info("Enter User ID to approve: ");
		int userId = sc.nextInt();
		userDaoOperation.approveUser(userId);
		
		NotificationDaoOperationInterface notificationObj = new NotificationDaoOperation();
		notificationObj.updateStatus(userId, 1);
	}
	
	public void addProfessor() {
		Scanner sc = new Scanner(System.in);
		
		logger.info("Enter User ID: ");
		int userId = sc.nextInt();

		Professor professor = new Professor();
		professor.setUserId(userId);
		
		if (userDaoOperation.getUser(professor.getUserId())!=null) {
			logger.error("User already exists with this userId");
			return;
		}
		try {
		if (userDaoOperation.getUser(professor.getUserId())!=null) 
			throw new UserAlreadyExistsException(userId);
		}
		catch(UserAlreadyExistsException e) {
			logger.info(e.getMessage());
			}
		
		logger.info("Enter Username: ");
		String userName = sc.next();
		professor.setUserName(userName);
		logger.info("Enter Password: ");
		String password = sc.next();
		professor.setPassword(password);
		professor.setRole("Professor");
		logger.info("Enter Email: ");
		String email = sc.next();
		professor.setEmail(email);
		professor.setIsApproved(true);
		logger.info("Enter Address: ");
		String address = sc.next();
		professor.setAddress(address);
		logger.info("Enter Age: ");
		int age = sc.nextInt();
		professor.setAge(age);
		logger.info("Enter Gender: ");
		String gender = sc.next();
		professor.setGender(gender);
		logger.info("Enter Contact: ");
		String contact = sc.next();
		professor.setContact(contact);
		logger.info("Enter Department: ");
		String dep = sc.next();
		professor.setDepartment(dep);
		
		professorDaoOperation.addProfessor(professor);
	}
	
	public void assignCourseToProfessor() {
		Scanner sc = new Scanner(System.in);
		logger.info("Enter User ID of professor: ");
		int userId = sc.nextInt();
		try {
		if (userDaoOperation.getUser(userId)==null) {
			throw new UserDoesNotExistException(userId);
		}
		CourseRegistrationOperation courseRegistrationOperation = new CourseRegistrationOperation();
		courseRegistrationOperation.registerProfessorCourse(userId);
		}
		catch(UserDoesNotExistException e){
			System.out.println(e.getMessage());
		}
	}
	
	public void viewGradeCard() {
		Scanner sc = new Scanner(System.in);
		logger.info("Enter User ID of student: ");
		
		int userId = sc.nextInt();
		GradeCardOperation gradeCardOperation = new GradeCardOperation();
		gradeCardOperation.viewGradeCard(userId);
	}
}
