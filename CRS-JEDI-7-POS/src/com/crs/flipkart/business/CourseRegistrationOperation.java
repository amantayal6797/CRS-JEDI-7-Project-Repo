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
import com.crs.flipkart.exception.RegistrationCompletedException;
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
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.printf("|%-20s | %-20s |\n","Course Id","Course Name");
		System.out.println("---------------------------------------------------------------------------------------------------");

		for(RegisteredCourse regCourse:listOfRegisteredCourses) {
				Course course=courseDAOobj.getCourse(regCourse.getCourseID());
				logger.info(String.format("|%20d|%20s|\n", course.getCourseID(), course.getCourseName()));

			
		}
		logger.info("-------------------------------------------------------------------------------------------");
	}

	public void viewCourses() {
		// Display all courses in catalog
		logger.info("Displaying All Courses");
		ArrayList<Course> catalog=new ArrayList<Course>();
		catalog=courseDAOobj.viewCourses();
		logger.info("---------------------------------------------------------------------------------------------------\n");
		logger.info(String.format("|%-20s | %-20s | %-20s | %-20s|%20s|\n","Course Id","Course Name","Course Credits","Course Prerequisites","Course Professor Id"));
		logger.info("---------------------------------------------------------------------------------------------------\n");

		for(Course course:catalog) {
			logger.info(String.format("|%-20d | %-20s | %-20d | %-20s|%20d|\n",course.getCourseID(),course.getCourseName(),course.getCredits(),course.getPrerequisites(),course.getProfessorAllotted()));
			logger.info("---------------------------------------------------------------------------------------------------");

		}
	}

	public void dropCourse(int studentId,int courseId) {
		try {
		ArrayList<RegisteredCourse> enrolledCourses=courseDAOobj.getRegisteredCourses(studentId);
		if (enrolledCourses.size()==0) {
			throw new NoCourseToDropException();
		}
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

	public void addCourse(int studentId,int courseId) {
		try {
		ArrayList<RegisteredCourse> enrolledCourses=courseDAOobj.getRegisteredCourses(studentId);
		if(enrolledCourses.size()==4) {
			throw new RegistrationCompletedException();
		}
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
		}catch(RegistrationCompletedException | CourseAlreadyRegisteredException | CourseDoesNotExistException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void registerCourses(int studentId,ArrayList<Integer> choices) {
		
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
	
	 public void registerProfessorCourse(int userId,ArrayList<Integer> courseIdList,int choice) {
		 try{
			 if(!courseIdList.contains(choice)) {
			 throw new CourseDoesNotExistException(choice);
		 }
		 courseDAOobj.setRegisterCourse(userId,choice);
		 System.out.println("Course - "+choice+" succesfully allotted to Professor - "+userId);
	 
		 }catch( CourseDoesNotExistException e) {
			 System.out.println(e.getMessage());
		 }
	 }
	 
	 public void viewProfessorCourses(int userId) {
		 logger.info("Registered Courses");
		 ArrayList<Course> courseList=new ArrayList<Course>();
		 courseList=courseDAOobj.getProfessorCourses(userId);
		 logger.info("---------------------------------------------------------------------------------------------------\n");
		 logger.info(String.format("|%-20s | %-20s | %-20s | %-20s|\n","Course Id","Course Name","Course Credits","Course Prerequisites"));
		 logger.info("---------------------------------------------------------------------------------------------------\n");

		 for(Course course:courseList) {
			 logger.info(String.format("|%-20d | %-20s | %-20d | %-20s|\n",course.getCourseID(),course.getCourseName(),course.getCredits(),course.getPrerequisites()));
			 logger.info("---------------------------------------------------------------------------------------------------\n");

		 }
	 }
	 
	 public void viewEnrolledStudents(int userId) {
		 logger.info("Enrolled Students");
		 ArrayList<Course> courseList=new ArrayList<Course>();
		 courseList=courseDAOobj.getProfessorCourses(userId);
		 /*
		 for(Course course:courseList) {
			 logger.info("Course ID:- "+ course.getCourseID()+"\tCourse Name:- "+course.getCourseName());
			 ArrayList <Integer> enrolledStudents = courseDAOobj.getEnrolledStudents(course.getCourseID());
			 logger.info("Students:-");
			 for(int i:enrolledStudents) {
				 logger.info("Student ID :- "+i);
			 }
		 }
		 */
		 logger.info("---------------------------------------------------------------------------------------------------\n");
		 logger.info(String.format("|%-20s | %-20s | %-20s | \n","Course Id","Course Name","Student ID"));
		 logger.info("---------------------------------------------------------------------------------------------------\n");
		 for(Course course:courseList) {
			 ArrayList <Integer> enrolledStudents = courseDAOobj.getEnrolledStudents(course.getCourseID());
			 //logger.info(String.format("%-20d"));
			 String temp = "";
			 for(int i:enrolledStudents) {
				 temp = temp + Integer.toString(i) + ","; 
				 //logger.info("Student ID :- "+i);
			 }
			 
			 temp = temp.length()!=0?temp.substring(0,temp.length()-1):temp;
			 logger.info(String.format("|%-20d | %-20s | %-20s |",course.getCourseID(),course.getCourseName(),temp));
			 logger.info("\n---------------------------------------------------------------------------------------------------\n");
		 }
		 
	 }
}
