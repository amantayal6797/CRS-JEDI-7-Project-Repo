/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Course;

/**
 * @author Aditya
 *
 */
public interface CourseRegistrationOperationInterface {
	/**
	 * Method to display registered courses of an user
	 * @param studentId: to identify the student whose registered courses are to be displayed
	 */
	public void viewRegisteredCourse(int studentId);

	/**
	 * Method to display all courses in the catalogue
	 */
	public void viewCourses();
	/**
	 * Method to remove registration from CoursesRegisteredDB
	 * @param studentId: to identify the student whose registered courses are to be displayed
	 */
	public void dropCourse(int studentId,int courseId);
	/**
	 * Method to add registration from CoursesRegisteredDB
	 * @param studentId: to identify the student whose registered courses are to be displayed
	 */
	public void addCourse(int studentId,int courseId);
	/**
	 * Method to register for course
	 * @param studentId: to identify the student whose registered courses are to be displayed
	 */
	public void registerCourses(int studentId,ArrayList<Integer> choices);
	/**
	 * Method for professor to register for the course
	 * @param userId: identification number of the professor
	 */
	public void registerProfessorCourse(int userId,ArrayList<Integer> courseIdList,int choice);
	/**
	 * Method to dislay the list of registered courses of a professor
	 * @param userId: identification number of the professor
	 */
	public void viewProfessorCourses(int userId);
	/**
	 * Method to display list professor the list of students enrolled in their registered courses
	 * @param userId: identification number of the professor
	 */
	public void viewEnrolledStudents(int userId);
}