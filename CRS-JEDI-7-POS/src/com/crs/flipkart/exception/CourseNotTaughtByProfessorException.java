/**
 * 
 */
package com.crs.flipkart.exception;

/**
 * @author Ashruth
 *
 */
public class CourseNotTaughtByProfessorException extends Exception {
	
	int courseId;
	public CourseNotTaughtByProfessorException(int courseId) {
		this.courseId=courseId;
	}
	
	public String getMessage() {
		return "Course "+Integer.toString(courseId)+" is not taught by you";
	}
}
