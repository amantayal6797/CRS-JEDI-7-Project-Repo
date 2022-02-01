/**
 * 
 */
package com.crs.flipkart.exception;

/**
 * @author Ashruth
 *
 */
public class CourseAlreadyRegisteredException extends Exception{
	int courseId;
	public CourseAlreadyRegisteredException(int cid) {
		courseId=cid;
	}
	public String getMessage() {
		return "Course " + Integer.toString(courseId) + " Already Registered";
	}
}
