/**
 * 
 */
package com.crs.flipkart.business;

import java.util.Scanner;

//import org.apache.log4j.Logger;

import com.crs.flipkart.dao.PaymentDaoOperation;
import com.crs.flipkart.dao.PaymentDaoOperationInterface;
import com.crs.flipkart.dao.StudentDaoOperation;

/**
 * @author Ashruth
 *
 */
public class PaymentOperation implements PaymentOperationInterface {
	//private static Logger logger = Logger.getLogger(CourseRegistrationOperation.class);
//--------------------------------------------------------------------------------	
	private int transactionID;
	int amount;
//	------------------------------------------------------------------------------
	
	
	public boolean makePayment(int userId, int amount) {
	    StudentDaoOperation studentDaoOperation = new StudentDaoOperation();
    	boolean paymentSuccessful = studentDaoOperation.setPaymentStatus(userId);
    	if (paymentSuccessful) {
    		generateBill(userId,amount);	
    	}
    	
    	return paymentSuccessful;
	}
	
	
//	--------------------------------------------------------------------------------		
	public void generateBill(int userId, int amount) {		// this function called from makePayment function
		System.out.println("Fees Paid - "+amount);
		
		String transactionId = Integer.toString(userId) + Integer.toString((int) Math.random());
		PaymentDaoOperationInterface paymentObj = new PaymentDaoOperation();
		paymentObj.savePayment(userId, amount, transactionId);
	}
	
}






