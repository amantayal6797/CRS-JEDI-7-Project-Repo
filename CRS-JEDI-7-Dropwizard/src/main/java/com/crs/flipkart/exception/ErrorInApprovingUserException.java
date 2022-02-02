/**
 * 
 */
package com.crs.flipkart.exception;

/**
 * @author Aditya
 *
 */
public class ErrorInApprovingUserException extends Exception{

	public String getMessage(int userId) {
		String msg ="Error in approving user-" + Integer.toString(userId);
		return msg;
		}
	
}
