/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.exception.CourseAlreadyRegisteredException;
import com.crs.flipkart.exception.CourseDoesNotExistException;
import com.crs.flipkart.exception.CourseNotRegisteredToDropException;
import com.crs.flipkart.exception.NoCourseToDropException;
import com.crs.flipkart.exception.RegistrationCompletedException;

/**
 * @author Aditya
 *
 */
public interface CourseRegistrationOperationInterface {
	/**
	 * Method to display registered courses of an user
	 * @param studentId: to identify the student whose registered courses are to be displayed
	 * @return 
	 */
	public ArrayList<RegisteredCourse> viewRegisteredCourse(int studentId);

	/**
	 * Method to display all courses in the catalogue
	 * @return 
	 */
	public ArrayList<Course> viewCourses();
	/**
	 * Method to remove registration from CoursesRegisteredDB
	 * @param studentId: to identify the student whose registered courses are to be displayed
	 * @return 
	 * @throws CourseNotRegisteredToDropException 
	 * @throws CourseDoesNotExistException 
	 * @throws NoCourseToDropException 
	 */
	public boolean dropCourse(int studentId,int courseId) throws NoCourseToDropException, CourseDoesNotExistException, CourseNotRegisteredToDropException;
	/**
	 * Method to add registration from CoursesRegisteredDB
	 * @param studentId: to identify the student whose registered courses are to be displayed
	 * @return 
	 * @throws CourseAlreadyRegisteredException 
	 * @throws CourseDoesNotExistException 
	 * @throws RegistrationCompletedException 
	 */
	public boolean addCourse(int studentId,int courseId) throws RegistrationCompletedException, CourseDoesNotExistException, CourseAlreadyRegisteredException;
	/**
	 * Method to register for course
	 * @param studentId: to identify the student whose registered courses are to be displayed
	 * @return 
	 */
	public boolean registerCourses(int studentId,ArrayList<Integer> choices);
	/**
	 * Method for professor to register for the course
	 * @param userId: identification number of the professor
	 * @return 
	 * @throws CourseDoesNotExistException 
	 */
	public boolean registerProfessorCourse(int userId,ArrayList<Integer> courseIdList,int choice) throws CourseDoesNotExistException;
	/**
	 * Method to dislay the list of registered courses of a professor
	 * @param userId: identification number of the professor
	 * @return 
	 */
	public ArrayList<Course> viewProfessorCourses(int userId);
	/**
	 * Method to display list professor the list of students enrolled in their registered courses
	 * @param userId: identification number of the professor
	 * @return 
	 */
	public ArrayList<Integer> viewEnrolledStudents(int userId);
}