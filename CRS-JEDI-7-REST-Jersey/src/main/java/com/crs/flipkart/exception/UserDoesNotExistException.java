/**
 * 
 */
package com.crs.flipkart.exception;

/**
 * @author Ashruth
 *
 */
public class UserDoesNotExistException extends Exception{
	int userId;
	public UserDoesNotExistException(int userId) {
		this.userId=userId;
	}
	public String getMessage() {
		return "User with ID "+Integer.toString(userId)+" Does Not Exist";
	}
}
