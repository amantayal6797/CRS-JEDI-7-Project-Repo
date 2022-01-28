/**
 * 
 */
package com.crs.flipkart.exception;

/**
 * @author Ashruth
 *
 */
public class UserAlreadyExistsException extends Exception {
	private int userId;
	
	public UserAlreadyExistsException(int userId) {
		this.userId=userId;
	}
	public String getMessage() {
		return "User with ID "+userId+ " already Exists";
		}
}
