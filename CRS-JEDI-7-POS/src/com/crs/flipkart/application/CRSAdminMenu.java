/**
 * 
 */
package com.crs.flipkart.application;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Admin;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Grade;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.AdminOperation;
import com.crs.flipkart.business.AdminOperationInterface;
import com.crs.flipkart.business.CourseRegistrationOperation;
import com.crs.flipkart.business.CourseRegistrationOperationInterface;
import com.crs.flipkart.dao.AdminDaoOperation;
import com.crs.flipkart.dao.AdminDaoOperationInterface;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.CourseDaoOperationInterface;
import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.dao.UserDaoOperationInterface;
import com.crs.flipkart.exception.CourseDoesNotExistException;
import com.crs.flipkart.exception.CourseIDAlreadyExistException;
import com.crs.flipkart.exception.NoUnallottedCourseException;
import com.crs.flipkart.exception.UserAlreadyExistsException;
import com.crs.flipkart.exception.UserDoNotExistException;
import com.crs.flipkart.exception.UserDoesNotExistException;

/**
 * @author aditya.gupta3
 *
 *  The Client Side Application for displaying and going forward with
 *  Administrator related operations and functionalities.
 */


 /**
 * Main Function to display, choose and then call the respective Administrator
 * Operations.
 */
public class CRSAdminMenu extends CRSApplication {
	
	AdminDaoOperationInterface adminDaoObj=new AdminDaoOperation();
	Scanner sc = new Scanner(System.in);
	CourseDaoOperationInterface courseDAOobj=new CourseDaoOperation();
	UserDaoOperationInterface userDaoOperation = new UserDaoOperation();
	int userId;
	CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
	private static Logger logger = Logger.getLogger(CourseRegistrationOperation.class);
	AdminOperationInterface adminOperation = new AdminOperation();
	
	public void AdminMenu(int userId) {
		this.userId=userId;
		
		Admin admin=adminDaoObj.getAdmin(userId);
		System.out.println("\nWelcome "+admin.getUserName());
		
		LocalTime localTime = LocalTime.now();
		System.out.println("Login Time:- " + localTime);
		
		LocalDate localDate = LocalDate.now();
		System.out.println("Login Date:- "+ localDate.getDayOfMonth()+" "+localDate.getMonth()+", "+localDate.getYear());
		
		
		int choice = 0;
		while(choice!=9) {
			System.out.println("+++++++++++++++++++++++++++++++++++");
			System.out.println("Admin Menu");
			System.out.println("1. View all courses");
			System.out.println("2. Add Course to catalog");
			System.out.println("3. Drop Course from catalog");
			System.out.println("5. View Unapproved Students");
			System.out.println("5. Approve Student");
			System.out.println("6. Add Professor");
			System.out.println("7. Assign Course To Professor");
			System.out.println("8. View Grade Card");
			System.out.println("9. Logout");
			System.out.println("++++++++++++++++++++++++++++++++++++");
			
			choice = sc.nextInt();
			
			switch(choice) {
				// View Course case
			case 1:
				viewAllCourses();
				break;
				// Add Course case
			case 2:
				addCourse();
				break;
				// Drop Course case
			case 3:
				dropCourse();
				break;
				// Approve Course case
			case 4:
				viewUnapprovedUsers();
				break;
			case 5:
				ApproveUser();
				break;
			// Add Professor case
			case 6:
				addProfessor();
				break;
			// Assign Course to Professor Case
			case 7:
				assignProfessorCourse();
				break;
			// View Grade Card Case
			case 8:
				viewGradeCard();
				break;
			
			case 9:
				System.out.println("Successfully logged out!!!");
				return;

			default:
				System.out.println("Invalid Choice!!!");
			}
		}
	}
	private void viewGradeCard() {
		try{System.out.println("Enter User ID of student: ");
		int studId = sc.nextInt();
		sc.nextLine();
		GradeCard gradeCard=adminOperation.viewGradeCard(studId);
		logger.info("Grade Card");
		logger.info("User ID:-"+userId);
		for(Grade grade:gradeCard.getListOfGrades()) {
			logger.info("Course ID:-"+grade.getCourseID()+" Grade:-"+grade.getGrade());
			}
	
		}catch(UserDoesNotExistException e) {
			System.out.println(e.getMessage());
		}
	}
	private void assignProfessorCourse() {
		try {
			ArrayList<Course> courseList=new ArrayList<Course>();
			System.out.println("Enter User ID of professor: ");
			int profId = sc.nextInt();
			 sc.nextLine();
			courseList=courseDAOobj.getUnregisteredCourses(userId);
			 
			 if (courseList.size()==0) {
				 throw new NoUnallottedCourseException();
			 }
			 System.out.println("Available Courses");
			 ArrayList<Integer> courseIdList=new ArrayList<Integer>();
			 for(Course course:courseList) {
					System.out.println("Course Id:- "+course.getCourseID());
					System.out.println("Course Name:- "+course.getCourseName());
					System.out.println("Course Credits:- "+course.getCredits());
					System.out.println("Course Prerequisites:- "+course.getPrerequisites());
					System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					courseIdList.add(course.getCourseID());
				}
			 System.out.println("Enter Course ID to register");
			 int ch=sc.nextInt();
			 sc.nextLine();
			  
			adminOperation.assignCourseToProfessor(profId,courseIdList,ch);
			}catch(UserDoesNotExistException |CourseDoesNotExistException | NoUnallottedCourseException e){
				System.out.println(e.getMessage());
			}
		
	}
	private void addProfessor() {
		try {
		System.out.println("Enter User ID: ");
		int professorUserId = sc.nextInt();
		Professor professor = new Professor();
		professor.setUserId(professorUserId);
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
		adminOperation.addProfessor(professor);
		}catch(UserAlreadyExistsException e) {
			System.out.println(e.getMessage());
			}
		
	}
	private void viewUnapprovedUsers() {
		ArrayList<Integer> unapprovedStudents=userDaoOperation.getUnapprovedStudents();
		if(unapprovedStudents.size()==0) {
			System.out.println("All users Approved");
			return;
		}
		System.out.println("List of Unapproved Students");
		unapprovedStudents.forEach(System.out::println);
		
	}
	private void ApproveUser() {
		try {
		ArrayList<Integer> unapprovedStudents=userDaoOperation.getUnapprovedStudents();
		if(unapprovedStudents.size()==0) {
			System.out.println("All users Approved");
			return;
		}
		System.out.println("Enter User ID to approve: ");
		int userIdToApprove = sc.nextInt();
		boolean flag=adminOperation.approveUser(userIdToApprove);
		if (flag)
			System.out.println("User - "+userId+" approved successfully");	
		}catch(UserDoNotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
	}
	private void dropCourse() {
		try {
		System.out.println("Enter Course ID to drop: ");
		int courseID = sc.nextInt();
		sc.nextLine();
		boolean flag=adminOperation.dropCourse(courseID);
		if (flag)
			System.out.println("Course - "+courseID+" dropped successfully");
		}catch(CourseDoesNotExistException e) {
			System.out.println(e.getMessage());
		}
	}
	private void addCourse() {
		try {
		System.out.println("Enter Course ID to add: ");
		int courseID = sc.nextInt();
		System.out.println("Enter Course Name to add: ");
		String courseName = sc.next();
		System.out.println("Enter Course Credits: ");
		int credits = sc.nextInt();
		boolean flag=adminOperation.addCourse(courseID,courseName,credits);
		if(flag)
			System.out.println("Course - "+courseID+" added successfully");
		}
		catch(CourseIDAlreadyExistException e){
			System.out.println(e.getMessage());
			return;	
		}
		
	}
	private void viewAllCourses() {
		ArrayList<Course> catalog = courseRegistrationObj.viewCourses();
		for(Course course:catalog) {
			logger.info("Course Id:- "+course.getCourseID());
			logger.info("Course Name:- "+course.getCourseName());
			logger.info("Course Credits:- "+course.getCredits());
			logger.info("Course Prerequisites:- "+course.getPrerequisites());
			logger.info("Course Professor Id:- "+course.getProfessorAllotted());
			logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
		
	}
}
