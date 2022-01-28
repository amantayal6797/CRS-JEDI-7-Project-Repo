/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.crs.flipkart.application.CRSStudentMenu;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.CourseDaoOperationInterface;
import com.crs.flipkart.dao.StudentDaoOperation;
import com.crs.flipkart.dao.StudentDaoOperationInterface;
import com.crs.flipkart.exception.CourseAlreadyRegisteredException;
import com.crs.flipkart.exception.CourseDoesNotExistException;
import com.crs.flipkart.exception.CourseNotRegisteredToDropException;
import com.crs.flipkart.exception.MaxRegistrationDoneException;
import com.crs.flipkart.exception.NoCourseToDropException;
import com.crs.flipkart.exception.NoUnallottedCourseException;

/**
 * @author Ashruth
 *
 */
public class CourseRegistrationOperation implements CourseRegistrationOperationInterface {
	CourseDaoOperationInterface courseDAOobj=new CourseDaoOperation();
	StudentDaoOperationInterface studDAOobj = new StudentDaoOperation();
	Scanner sc=new Scanner(System.in);
	StudentOperationInterface studOpObj = new StudentOperation();
	private static Logger logger = Logger.getLogger(CourseRegistrationOperation.class);

	public void viewRegisteredCourse(int studentId) {
		logger.info("Register Courses for User "+studentId);
		ArrayList<RegisteredCourse> listOfRegisteredCourses=new ArrayList<RegisteredCourse>();
		listOfRegisteredCourses=courseDAOobj.getRegisteredCourses(studentId);
		for(RegisteredCourse regCourse:listOfRegisteredCourses) {
				Course course=courseDAOobj.getCourse(regCourse.getCourseID());
				logger.info("Course Id:-"+course.getCourseID()+"\tCourse Name:-"+course.getCourseName());
			
		}
		logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}

	public void viewCourses() {
		// Display all courses in catalog
		logger.info("Displaying All Courses");
		ArrayList<Course> catalog=new ArrayList<Course>();
		catalog=courseDAOobj.viewCourses();
		for(Course course:catalog) {
			logger.info("Course Id:- "+course.getCourseID());
			logger.info("Course Name:- "+course.getCourseName());
			logger.info("Course Credits:- "+course.getCredits());
			logger.info("Course Prerequisites:- "+course.getPrerequisites());
			logger.info("Course Professor Id:- "+course.getProfessorAllotted());
			logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
		}
	}

	public void dropCourse(int studentId) {
		// take course as input
		// remove registration from CoursesRegisteredDB
		// update enrolledCourses for student
		try {
		ArrayList<RegisteredCourse> enrolledCourses=courseDAOobj.getRegisteredCourses(studentId);
		if (enrolledCourses.size()==0) {
			throw new NoCourseToDropException();
		}
		logger.info("Enter id of course to remove");
		int courseId=sc.nextInt();
		sc.nextLine();
		if(!courseDAOobj.verifyCourse(courseId)) {
			throw new CourseDoesNotExistException(courseId);
		}
		for(RegisteredCourse regCourse:enrolledCourses) {
			if(regCourse.getCourseID()==courseId) {
				courseDAOobj.dropCourse(courseId,studentId);
				logger.info("Course Successfully Dropped");
				return;}
		}
		throw new CourseNotRegisteredToDropException();
		}
		catch(NoCourseToDropException | CourseDoesNotExistException |  CourseNotRegisteredToDropException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void addCourse(int studentId) {
		// take course as input
		// add registration in CoursesReGisteredDB
		// update enrolledCourses for student
		try {
		ArrayList<RegisteredCourse> enrolledCourses=courseDAOobj.getRegisteredCourses(studentId);
		if(enrolledCourses.size()==4) {
			throw new MaxRegistrationDoneException();
		}
		logger.info("Enter id of course to add");
		int courseId=sc.nextInt();
		sc.nextLine();
		//logger.info(1);
		for(RegisteredCourse regCourse:enrolledCourses) {
			if(regCourse.getCourseID()==courseId) {
				throw new CourseAlreadyRegisteredException(courseId);
				}
		}

		//logger.info(2);
		if(!courseDAOobj.verifyCourse(courseId)) {
			throw new CourseDoesNotExistException(courseId);
		}

		//logger.info(3);
		courseDAOobj.addCourse(studentId,courseId);
		System.out.println("Course Succesfully Added\n");
		}catch(MaxRegistrationDoneException | CourseAlreadyRegisteredException | CourseDoesNotExistException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void registerCourses(int studentId) {
		viewCourses();
		ArrayList<Integer> choices=new ArrayList<Integer>();
		
		logger.info("Enter 6 distinct choices");
		logger.info("Enter Course ID 1:-");
		int courseId=sc.nextInt();
		sc.nextLine();
		choices.add(courseId);
		
		logger.info("Enter Course ID 2:-");
		courseId=sc.nextInt();
		sc.nextLine();
		choices.add(courseId);
		
		logger.info("Enter Course ID 3:-");
		courseId=sc.nextInt();
		sc.nextLine();
		choices.add(courseId);
		
		logger.info("Enter Course ID 4:-");
		courseId=sc.nextInt();
		sc.nextLine();
		choices.add(courseId);
		
		logger.info("Enter Course ID 5:-");
		courseId=sc.nextInt();
		sc.nextLine();
		choices.add(courseId);
		
		logger.info("Enter Course ID 6:-");
		courseId=sc.nextInt();
		sc.nextLine();
		choices.add(courseId);
		
		int count=0;
		ArrayList<Integer> enrolled=new ArrayList<Integer>();
		for(int cId:choices) {
			if(count==4)
				break;
			if(enrolled.contains(cId)) {
				logger.info("Course "+cId+" already registered");
				continue;
			}
			if(!courseDAOobj.verifyCourse(cId)) {
				logger.error("Invalid Course Id "+cId);
				continue;}
			
			if(courseDAOobj.getEnrolledStudents(cId).size()<10) {
				courseDAOobj.addCourse(studentId, cId);
				enrolled.add(cId);
				logger.info("Registered Course ID "+cId);
				count++;
			}
			else
				logger.error("Course "+cId+" exceeded student limit");
		}
		logger.info("Course Registration Done");
		logger.info("Pay Fees Now.");
		studDAOobj.setRegistration(studentId);
	}
	
	 public void registerProfessorCourse(int userId) {
		 try {
		 ArrayList<Course> courseList=new ArrayList<Course>();
		 courseList=courseDAOobj.getUnregisteredCourses(userId);
		 if (courseList.size()==0) {
			 throw new NoUnallottedCourseException();
		 }
		 logger.info("Available Courses");
		 ArrayList<Integer> courseIdList=new ArrayList<Integer>();
		 for(Course course:courseList) {
				logger.info("Course Id:- "+course.getCourseID());
				logger.info("Course Name:- "+course.getCourseName());
				logger.info("Course Credits:- "+course.getCredits());
				logger.info("Course Prerequisites:- "+course.getPrerequisites());
				logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				courseIdList.add(course.getCourseID());
			}
		 logger.info("Enter Course ID to register");
		 int choice=sc.nextInt();
		 sc.nextLine();
		 if(!courseIdList.contains(choice)) {
			 throw new CourseDoesNotExistException(choice);
		 }
		 courseDAOobj.setRegisterCourse(userId,choice);
		 System.out.println("Course - "+choice+" succesfully allotted to Professor - "+userId);
	 
		 }catch(NoUnallottedCourseException | CourseDoesNotExistException e) {
			 System.out.println(e.getMessage());
		 }
	 }
	 
	 public void viewProfessorCourses(int userId) {
		 logger.info("Registered Courses");
		 ArrayList<Course> courseList=new ArrayList<Course>();
		 courseList=courseDAOobj.getProfessorCourses(userId);
		 for(Course course:courseList) {
				logger.info("Course Id:- "+course.getCourseID());
				logger.info("Course Name:- "+course.getCourseName());
				logger.info("Course Credits:- "+course.getCredits());
				logger.info("Course Prerequisites:- "+course.getPrerequisites());
				logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			}
	 }
	 
	 public void viewEnrolledStudents(int userId) {
		 logger.info("Enrolled Students");
		 ArrayList<Course> courseList=new ArrayList<Course>();
		 courseList=courseDAOobj.getProfessorCourses(userId);
		 for(Course course:courseList) {
			 logger.info("Course ID:- "+ course.getCourseID()+"\tCourse Name:- "+course.getCourseName());
			 ArrayList <Integer> enrolledStudents = courseDAOobj.getEnrolledStudents(course.getCourseID());
			 logger.info("Students:-");
			 for(int i:enrolledStudents) {
				 logger.info("Student ID :- "+i);
			 }
		 }
		 
	 }
	

}
