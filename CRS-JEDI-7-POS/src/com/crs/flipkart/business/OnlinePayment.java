/**
 * 
 */
package com.crs.flipkart.business;

import java.util.Scanner;

/**
 * @author Ashruth
 *
 */
public class OnlinePayment extends PaymentOperation implements OnlinePaymentInterface{
	
	private int cardNumber, date;
//	--------------------------------------------------------------------------------	
	
	public void payByCard(int userId, int cardNumber, int date) {
		
	    this.cardNumber=cardNumber;
		this.date=date;

		Scanner sc=new Scanner(System.in);
		System.out.println("You have to pay: 2,50,000");
		
		System.out.println("Enter 1. to pay fees or 2. for exit");		
		int choice = sc.nextInt();
		sc.nextLine();
		
		if(choice!=1)
			return;
		
		/*System.out.print("Enter amount: ");
		int amount=sc.nextInt();
		sc.nextLine();*/
				
		if(makePayment(userId, 250000)==false)
			System.out.println("Sorry :(  Bank Technical Problem");
		else {
			System.out.println("Transaction completed :) ");
		}	
	}
	
	public boolean authenticate(int cardNumber, int date) {
		return false;
		
		//check card num & expiryDate from Bank DataBase, and return a boolean value correspond to that
		
	}
		
}
