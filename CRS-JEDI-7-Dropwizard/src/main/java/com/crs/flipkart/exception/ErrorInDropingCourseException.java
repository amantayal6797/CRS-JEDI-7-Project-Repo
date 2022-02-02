/**
 * 
 */
package com.crs.flipkart.exception;

/**
 * @author Aditya
 *
 */
public class ErrorInDropingCourseException extends Exception {
	
	//flag is used here to differentiate caller (AdminDaoOperation, CourseDaoOperation) of this exception.
	//1: Exception called by AdminDaoOperation
	//2: Exception called by CourseDaoOperation
	
	private int flag;
	
	public ErrorInDropingCourseException(int flag) {
		this.flag=flag;
	}
	
	public String getMessage() {
		return "Error in dropping course";
		}
	
}
