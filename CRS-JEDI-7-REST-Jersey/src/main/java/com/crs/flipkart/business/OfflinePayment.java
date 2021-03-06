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
public class OfflinePayment extends PaymentOperation implements OfflinePaymentInterface {
	private int slipNumber;

	public boolean payByCash(int userId, int slipNumber) {
		try {
	    this.slipNumber = slipNumber;
		
		Scanner sc=new Scanner(System.in);
	
		
		
		if(makePayment(userId, 250000,2)==false)
			throw new PaymentException();
		else {
			return true;
		}	
		}catch (PaymentException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean authenticate(int slipNumber) {
		return false;
			
	}
	
}