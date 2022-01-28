/**
 * 
 */
package com.crs.flipkart.exception;

/**
 * @author Aditya
 *
 */
public class ErrorInSettingPaymentStatusException extends Exception {
	
	private int studentId;
	public ErrorInSettingPaymentStatusException (int studentId) {
	this.studentId=studentId;
	}
	
	public String getMessage() {
		String msg = "Error in setting payment status for student-"+ Integer.toString(this.studentId);
		return msg;
	}
}
