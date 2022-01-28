/**
 * 
 */
package com.crs.flipkart.exception;

/**
 * @author Aditya
 *
 */
public class UserDoNotExistException extends Exception{
	
	public String getMessage() {
		return "User does not exists with this userId";
		}
}
