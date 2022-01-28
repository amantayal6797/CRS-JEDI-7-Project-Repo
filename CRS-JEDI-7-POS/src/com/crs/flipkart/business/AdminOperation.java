/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.Scanner;

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
	
	public void addCourse() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Course ID to add: ");
		int courseID = sc.nextInt();
		System.out.println("Enter Course Name to add: ");
		String courseName = sc.next();
		System.out.println("Enter Course Credits: ");
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
		System.out.println("Enter Course ID to drop: ");
		int courseID = sc.nextInt();
		
		adminDaoOperation.dropCourse(courseID);
	}
	
	public void approveUser() {
		ArrayList<Integer> unapprovedStudents=userDaoOperation.getUnapprovedStudents();
		System.out.println("List of Unapproved Students");
		for(int i:unapprovedStudents) {
			System.out.println(i);
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter User ID to approve: ");
		int userId = sc.nextInt();
		userDaoOperation.approveUser(userId);
		
		NotificationDaoOperationInterface notificationObj = new NotificationDaoOperation();
		notificationObj.updateStatus(userId, 1);
	}
	
	public void addProfessor() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter User ID: ");
		int userId = sc.nextInt();

		Professor professor = new Professor();
		professor.setUserId(userId);
		try {
		if (userDaoOperation.getUser(professor.getUserId())!=null) 
			throw new UserAlreadyExistsException(userId);
		}
		catch(UserAlreadyExistsException e) {
			System.out.println(e.getMessage());
			}
		
		System.out.println("Enter Username: ");
		String userName = sc.next();
		professor.setUserName(userName);
		System.out.println("Enter Password: ");
		String password = sc.next();
		professor.setPassword(password);
		professor.setRole("Professor");
		System.out.println("Enter Email: ");
		String email = sc.next();
		professor.setEmail(email);
		professor.setIsApproved(true);
		System.out.println("Enter Address: ");
		String address = sc.next();
		professor.setAddress(address);
		System.out.println("Enter Age: ");
		int age = sc.nextInt();
		professor.setAge(age);
		System.out.println("Enter Gender: ");
		String gender = sc.next();
		professor.setGender(gender);
		System.out.println("Enter Contact: ");
		String contact = sc.next();
		professor.setContact(contact);
		System.out.println("Enter Department: ");
		String dep = sc.next();
		professor.setDepartment(dep);
		
		professorDaoOperation.addProfessor(professor);
	}
	
	public void assignCourseToProfessor() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter User ID of professor: ");
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
		System.out.println("Enter User ID of student: ");
		
		int userId = sc.nextInt();
		GradeCardOperation gradeCardOperation = new GradeCardOperation();
		gradeCardOperation.viewGradeCard(userId);
	}
}
