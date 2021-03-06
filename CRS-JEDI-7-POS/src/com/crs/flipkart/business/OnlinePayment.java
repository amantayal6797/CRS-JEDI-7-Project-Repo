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
public class OnlinePayment extends PaymentOperation implements OnlinePaymentInterface{
	private static Logger logger = Logger.getLogger(CourseRegistrationOperation.class);
	private int cardNumber, date;
	
	public void payByCard(int userId, int cardNumber, int date) {
		try {
	    this.cardNumber=cardNumber;
		this.date=date;

		Scanner sc=new Scanner(System.in);
		logger.info("You have to pay: 2,50,000");
		
		logger.info("Enter 1. to pay fees or 2. for exit");		
		int choice = sc.nextInt();
		sc.nextLine();
		
		if(choice!=1)
			return;
				
		if(makePayment(userId, 250000,1)==false)
			throw new PaymentException();
		else {
			logger.info("Payment received in online mode.\nFees Paid.");
		}	
		}catch (PaymentException e) {
			e.getMessage();		}
	}
	
	public boolean authenticate(int cardNumber, int date) {
		return false;
	}
		
}
