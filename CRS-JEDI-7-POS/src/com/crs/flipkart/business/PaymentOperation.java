/**
 * 
 */
package com.crs.flipkart.business;

import java.util.Scanner;

import com.crs.flipkart.dao.StudentDaoOperation;

/**
 * @author Ashruth
 *
 */
public class PaymentOperation implements PaymentOperationInterface {

//--------------------------------------------------------------------------------	
	private int transactionID;
	int amount;
//	------------------------------------------------------------------------------
	
	
	public boolean makePayment(int userId, int amount) {
	    StudentDaoOperation studentDaoOperation = new StudentDaoOperation();
    	boolean paymentSuccessful = studentDaoOperation.setPaymentStatus(userId);
    	if (paymentSuccessful) {
    		generateBill(amount);	
    	}
    	
    	return paymentSuccessful;
	}
	
	
//	--------------------------------------------------------------------------------		
	public void generateBill(int amount) {		// this function called from makePayment function
		System.out.println("Fees Paid - "+amount);
//		transactionID=random();			generate transID by random generator
		
	}
}






