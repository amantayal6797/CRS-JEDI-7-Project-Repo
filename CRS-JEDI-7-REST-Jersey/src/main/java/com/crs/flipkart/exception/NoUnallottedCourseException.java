/**
 * 
 */
package com.crs.flipkart.exception;

/**
 * @author Ashruth
 *
 */
public class NoUnallottedCourseException extends Exception{
	public String getMessage() {
		return "All courses have already been allotted to professors";
	}
}
