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

	public boolean payByCash(int userId, int slipNumber) {
		try {
	    this.slipNumber = slipNumber;
		
		Scanner sc=new Scanner(System.in);
	
		
		
		if(makePayment(userId, 250000)==false)
			throw new PaymentException();
		else {
			return true;
		}	
		}catch (PaymentException e) {
			logger.error(e.getMessage());
		}
		return false;
	}
	
	public boolean authenticate(int slipNumber) {
		return false;
			
		//check slipNumber from Bank DataBase, and return a boolean value correspond to that
			
	}
	
}