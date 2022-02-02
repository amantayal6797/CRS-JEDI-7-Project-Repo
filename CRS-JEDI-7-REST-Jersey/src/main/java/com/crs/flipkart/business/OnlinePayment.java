/**
 * 
 */
package com.crs.flipkart.business;

import java.util.Scanner;

//import org.apache.log4j.Logger;

import com.crs.flipkart.exception.PaymentException;

/**
 * @author Ashruth
 *
 */
public class OnlinePayment extends PaymentOperation implements OnlinePaymentInterface{
	private int cardNumber, date;
	
	public boolean payByCard(int userId, int cardNumber, int date) {
		try {
	    this.cardNumber=cardNumber;
		this.date=date;
		
		if(makePayment(userId, 250000,1)==false)
			throw new PaymentException();
		else {
			return true;
		}	
		}catch (PaymentException e) {
			e.getMessage();		}
		return false;
	}
	
	public boolean authenticate(int cardNumber, int date) {
		return false;
	}
		
}
