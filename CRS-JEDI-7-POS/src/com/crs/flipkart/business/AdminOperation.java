/**
 * 
 */
package com.crs.flipkart.business;

import java.util.Scanner;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.dao.AdminDaoOperation;
import com.crs.flipkart.dao.ProfessorDaoOperation;
import com.crs.flipkart.dao.UserDaoOperation;

/**
 * @author aditya.gupta3
 *
 */
public class AdminOperation implements AdminOperationInterface {
	
	UserDaoOperation userDaoOperation = new UserDaoOperation();
	AdminDaoOperation adminDaoOperation = new AdminDaoOperation();
	ProfessorDaoOperation professorDaoOperation = new ProfessorDaoOperation();
	
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
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter User ID to approve: ");
		int userId = sc.nextInt();
		
		userDaoOperation.approveUser(userId);
	}
	
	public void addProfessor() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter User ID: ");
		int userId = sc.nextInt();

		Professor professor = new Professor();
		professor.setUserId(userId);
		
		if (userDaoOperation.getUser(professor.getUserId())!=null) {
			System.out.println("User already exists with this userId");
			return;
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
		System.out.println("Enter Nationality: ");
		String nationality = sc.next();
		professor.setNationality(nationality);
		System.out.println("Enter Department: ");
		String dep = sc.next();
		professor.setDepartment(dep);
		
		professorDaoOperation.addProfessor(professor);
	}
	
	public void assignCourseToProfessor() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter User ID of professor: ");
		int userId = sc.nextInt();
		CourseRegistrationOperation courseRegistrationOperation = new CourseRegistrationOperation();
		courseRegistrationOperation.registerProfessorCourse(userId);
	}
	
	public void viewGradeCard() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter User ID of student: ");
		
		int userId = sc.nextInt();
		GradeCardOperation gradeCardOperation = new GradeCardOperation();
		gradeCardOperation.viewGradeCard(userId);
	}
}