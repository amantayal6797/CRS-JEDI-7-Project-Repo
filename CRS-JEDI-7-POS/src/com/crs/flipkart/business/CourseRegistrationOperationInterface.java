/**
 * 
 */
package com.crs.flipkart.business;

/**
 * @author Aditya
 *
 */
public interface CourseRegistrationOperationInterface {

	public void viewRegisteredCourse(int studentId);
	public void viewCourses();
	public void dropCourse(int studentId);
	public void addCourse(int studentId);
	public void registerCourses(int studentId);
}