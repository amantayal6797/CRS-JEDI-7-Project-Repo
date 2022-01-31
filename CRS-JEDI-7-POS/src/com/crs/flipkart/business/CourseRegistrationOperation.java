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

	public ArrayList<RegisteredCourse> viewRegisteredCourse(int studentId) {
		logger.info("Register Courses for User "+studentId);
		ArrayList<RegisteredCourse> listOfRegisteredCourses=new ArrayList<RegisteredCourse>();
		listOfRegisteredCourses=courseDAOobj.getRegisteredCourses(studentId);
		return listOfRegisteredCourses;
		
	}

	public ArrayList<Course> viewCourses() {
		// Display all courses in catalog
		logger.info("Displaying All Courses");
		ArrayList<Course> catalog=new ArrayList<Course>();
		catalog=courseDAOobj.viewCourses();
		return catalog;
	}

	public boolean dropCourse(int studentId,int courseId) throws NoCourseToDropException,CourseDoesNotExistException,CourseNotRegisteredToDropException {
		
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
				return true;}
		}
		throw new CourseNotRegisteredToDropException();
		
		
		
	}

	public boolean addCourse(int studentId,int courseId) throws RegistrationCompletedException,CourseDoesNotExistException,CourseAlreadyRegisteredException {
		
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
		return true;
		
		
	}

	public boolean registerCourses(int studentId,ArrayList<Integer> choices) {
		
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
		studDAOobj.setRegistration(studentId);
		return true;
	}
	
	 public boolean registerProfessorCourse(int userId,ArrayList<Integer> courseIdList,int choice) throws CourseDoesNotExistException {
		 
			 if(!courseIdList.contains(choice)) {
			 throw new CourseDoesNotExistException(choice);
		 }
		 courseDAOobj.setRegisterCourse(userId,choice);
		 return true;
		 
	 }
	 
	 public ArrayList<Course> viewProfessorCourses(int userId) {
		 ArrayList<Course> courseList=new ArrayList<Course>();
		 courseList=courseDAOobj.getProfessorCourses(userId);
		 return courseList;
		 
	 }
	 
	 public ArrayList<Integer> viewEnrolledStudents(int courseId) {
		 ArrayList <Integer> enrolledStudents = courseDAOobj.getEnrolledStudents(courseId);
		 return enrolledStudents;
		 }
		 
	 }
	


