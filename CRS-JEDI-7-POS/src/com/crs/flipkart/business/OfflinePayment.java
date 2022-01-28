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
	
		System.out.println("You have to pay: 2,50,000");
		
		System.out.println("Enter 1. to pay fees or 2. for exit");	
		
		int choice = sc.nextInt();
		sc.nextLine();
		
		if(choice!=1)
			return;
		
		
		if(makePayment(userId, 250000)==false)
			System.out.println("Sorry :(  Bank Technical Problem");
		else {
			System.out.println("Transaction completed :) ");
		}
	}
	
	public boolean authenticate(int slipNumber) {
		return false;
			
		//check slipNumber from Bank DataBase, and return a boolean value correspond to that
			
	}
	
}