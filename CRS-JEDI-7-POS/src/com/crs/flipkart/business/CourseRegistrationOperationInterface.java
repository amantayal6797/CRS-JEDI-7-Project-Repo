/**
 * 
 */
package com.crs.flipkart.business;

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
	public void dropCourse(int studentId);
	/**
	 * Method to add registration from CoursesRegisteredDB
	 * @param studentId: to identify the student whose registered courses are to be displayed
	 */
	public void addCourse(int studentId);
	/**
	 * Method to register for course
	 * @param studentId: to identify the student whose registered courses are to be displayed
	 */
	public void registerCourses(int studentId);
	/**
	 * Method for professor to register for the course
	 * @param userId: identification number of the professor
	 */
	public void registerProfessorCourse(int userId);
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