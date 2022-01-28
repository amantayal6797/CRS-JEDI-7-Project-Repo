/**
 * 
 */
package com.crs.flipkart.exception;

/**
 * @author Ashruth
 *
 */
public class BankTechinicalException extends Exception {
	public String getMessage() {
		return "Error: Technical Issue with Bank. Payment Failed";
	}
}
