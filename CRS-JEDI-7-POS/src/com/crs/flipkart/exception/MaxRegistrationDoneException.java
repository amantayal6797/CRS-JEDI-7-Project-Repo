/**
 * 
 */
package com.crs.flipkart.exception;

/**
 * @author Ashruth
 *
 */
public class MaxRegistrationDoneException extends Exception {
	public String getMessage() {
		return "Already Registered for 4 courses.Drop a course before adding";
	}
}
