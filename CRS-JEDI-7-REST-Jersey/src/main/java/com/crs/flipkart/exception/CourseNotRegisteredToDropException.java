/**
 * 
 */
package com.crs.flipkart.exception;

/**
 * @author Ashruth
 *
 */
public class CourseNotRegisteredToDropException extends Exception{
	public String getMessage() {
		return "Course is not registered by You to Drop";
	}
}
