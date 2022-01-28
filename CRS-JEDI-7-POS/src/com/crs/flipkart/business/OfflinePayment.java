/**
 * 
 */
package com.crs.flipkart.business;

import java.util.Scanner;

/**
 * @author Ashruth
 *
 */
public class OfflinePayment extends PaymentOperation implements OfflinePaymentInterface {
	private int slipNumber;
//	--------------------------------------------------------------------------------	

	public void payByCash(int userId, int slipNumber) {
		
	    this.slipNumber = slipNumber;
		
		Scanner sc=new Scanner(System.in);
	
		logger.info("You have to pay: 2,50,000");
		
		logger.info("Enter 1. to pay fees or 2. for exit");	
		
		int choice = sc.nextInt();
		sc.nextLine();
		
		if(choice!=1)
			return;
		
		
		if(makePayment(userId, 250000)==false)
			logger.info("Sorry :(  Bank Technical Problem");
		else {
			logger.info("Transaction completed :) ");
		}
	}
	
	public boolean authenticate(int slipNumber) {
		return false;
			
		//check slipNumber from Bank DataBase, and return a boolean value correspond to that
			
	}
	
}