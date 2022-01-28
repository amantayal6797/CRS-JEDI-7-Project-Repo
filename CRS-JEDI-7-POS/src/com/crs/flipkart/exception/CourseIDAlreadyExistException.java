/**
 * 
 */
package com.crs.flipkart.exception;

/**
 * @author Aditya
 *
 */
public class CourseIDAlreadyExistException extends Exception {
	
	public String getMessage() {
		return "Course ID already exist";
	}

}
