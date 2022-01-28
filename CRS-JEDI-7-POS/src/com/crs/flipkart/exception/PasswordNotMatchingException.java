/**
 * 
 */
package com.crs.flipkart.exception;

/**
 * @author Ashruth
 *
 */
public class PasswordNotMatchingException extends Exception {
	public String getMessage() {
		return "Entered Passwords Do not match";
	}
}
