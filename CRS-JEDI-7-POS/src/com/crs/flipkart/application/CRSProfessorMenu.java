/**
 * 
 */
package com.crs.flipkart.application;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.CourseRegistrationOperation;
import com.crs.flipkart.business.CourseRegistrationOperationInterface;
import com.crs.flipkart.business.GradeCardOperation;
import com.crs.flipkart.business.GradeCardOperationInterface;
import com.crs.flipkart.business.OfflinePayment;
import com.crs.flipkart.business.OnlinePayment;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.CourseDaoOperationInterface;
import com.crs.flipkart.dao.ProfessorDaoOperation;
import com.crs.flipkart.dao.ProfessorDaoOperationInterface;
import com.crs.flipkart.exception.CourseDoesNotExistException;
import com.crs.flipkart.exception.CourseNotTaughtByProfessorException;
import com.crs.flipkart.exception.NoUnallottedCourseException;
import com.crs.flipkart.exception.StudentNotRegisteredForCourseException;

/**
 * 
 * User Interactive menu if user logs in is a professor
 * 
 * @author Ashruth
 *
 */


/*
Manages the major roles of professor based on user inputs
*/
public class CRSProfessorMenu extends CRSApplication {
	
	ProfessorDaoOperationInterface profDaoObj=new ProfessorDaoOperation(); 
	CourseDaoOperationInterface courseDAOobj = new CourseDaoOperation();
	CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
	GradeCardOperationInterface gradeCardObj= new GradeCardOperation();
	int userId;
	Scanner sc = new Scanner(System.in);
	private static Logger logger = Logger.getLogger(CourseRegistrationOperation.class);

	public void ProfessorMenu(int userId) {
		this.userId=userId;
		
		Professor professor=profDaoObj.getProfessor(userId);
		System.out.println("\nWelcome "+professor.getUserName());
		
		LocalTime localTime = LocalTime.now();
		System.out.println("Login Time:- " + localTime);
		
		LocalDate localDate = LocalDate.now();
		System.out.println("Login Date:- "+ localDate.getDayOfMonth()+" "+localDate.getMonth()+", "+localDate.getYear());
		
		int choice=0;
		while (choice!=5) 
		{
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("Professor Menu");
			System.out.println("1. Register for Course");
			System.out.println("2. View Registered Courses");
			System.out.println("3. View Enrolled Students");
			System.out.println("4. Grade Student");
			System.out.println("5. Logout");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
				// Professor Registers Course
				case 1:
					registerCourse();
					break;
					
				// View Course	of Professor
				case 2: 
					viewProfessorCourse();
					break;
				// View Enrolled Students in a course
				case 3:
					viewEnrolledStudents();
					break;
				// Assign Grade
				case 4:
					assignGrade();
					break;						
				case 5: 
						break;
						
				default:
						System.out.println("Choice not available");
			}
		}
	}
	
	public void viewEnrolledStudents() {
		ArrayList<Course> courseList=courseRegistrationObj.viewProfessorCourses(userId);
		for(Course course:courseList) {
			ArrayList<Integer> enrolledStudents=courseRegistrationObj.viewEnrolledStudents(course.getCourseID());
			logger.info("Course ID:- "+ course.getCourseID()+"\tCourse Name:- "+course.getCourseName());
			logger.info("Student ID");
			enrolledStudents.forEach(System.out::println);
		}
		 
	}

	public ArrayList<Course> viewUnregisteredCourses() throws NoUnallottedCourseException {
		ArrayList<Course> courseList=new ArrayList<Course>();
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
		 return courseList;
			 
	}
	
	public void registerCourse() {
		try {
			viewUnregisteredCourses();
			 ArrayList<Course> courseList=new ArrayList<Course>();
			 courseList=courseDAOobj.getUnregisteredCourses(userId);
			 if (courseList.size()==0) {
				 throw new NoUnallottedCourseException();
			 }
			 ArrayList<Integer> courseIdList=new ArrayList<Integer>();
			 for(Course course:courseList) {
					courseIdList.add(course.getCourseID());
				}
			 System.out.println("Enter Course ID to register");
			 int choice=sc.nextInt();
			 sc.nextLine();
			 boolean flag=courseRegistrationObj.registerProfessorCourse(userId,courseIdList,choice);
			 if(flag)
				 System.out.println("Course - "+choice+" succesfully allotted to Professor - "+userId);
		}
		catch(NoUnallottedCourseException | CourseDoesNotExistException e) {
			System.out.println(e.getMessage());
		}
	}
	public void viewProfessorCourse() {
		ArrayList<Course> courseList =courseRegistrationObj.viewProfessorCourses(userId);
		for(Course course:courseList) {
			logger.info("Course Id:- "+course.getCourseID());
			logger.info("Course Name:- "+course.getCourseName());
			logger.info("Course Credits:- "+course.getCredits());
			logger.info("Course Prerequisites:- "+course.getPrerequisites());
			logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
	}
	public void assignGrade() {
		try {
		System.out.println("Enter CourseID");
		int courseId=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Student ID");
		int studentId=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Grade");
		String grade=sc.nextLine();
		boolean flag=gradeCardObj.assignGrade(userId,courseId,studentId,grade);
		if(flag) {
			System.out.println("Grade Assigned Succesfully");	
		}
		}catch(StudentNotRegisteredForCourseException | CourseNotTaughtByProfessorException e) {
			System.out.println(e.getMessage());
		}
	}

}
