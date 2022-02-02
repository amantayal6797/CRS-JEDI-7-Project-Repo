/**
 * 
 */
package com.crs.flipkart.business;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.crs.flipkart.exception.PaymentException;

/**
 * @author Ashruth
 *
 */
public class OfflinePayment extends PaymentOperation implements OfflinePaymentInterface {
	private static Logger logger = Logger.getLogger(CourseRegistrationOperation.class);
	private int slipNumber;
//	--------------------------------------------------------------------------------	

	public void payByCash(int userId, int slipNumber) {
		try {
	    this.slipNumber = slipNumber;
		
		Scanner sc=new Scanner(System.in);
	
		logger.info("You have to pay: 2,50,000");
		
		logger.info("Enter 1. to pay fees or 2. for exit");	
		
		int choice = sc.nextInt();
		sc.nextLine();
		
		if(choice!=1)
			return;
		
		
		if(makePayment(userId, 250000,2)==false)
			throw new PaymentException();
		else {
			logger.info("Payment received in offline mode.\nFees Paid.");
		}	
		}catch (PaymentException e) {
			logger.error(e.getMessage());
		}
	}
	
	public boolean authenticate(int slipNumber) {
		return false;
			
		//check slipNumber from Bank DataBase, and return a boolean value correspond to that
			
	}
	
}