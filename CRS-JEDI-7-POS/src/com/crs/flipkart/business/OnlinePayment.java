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
		System.out.print("Enter amount: ");
		int amount=sc.nextInt();
		sc.nextLine();
				
		if(makePayment(userId, amount)==false)
			System.out.println("Sorry :(  Bank Technical Problem");
		else {
			System.out.println("Transcation completed :) ");
		}	
	}
	
	public boolean authenticate(int cardNumber, int date) {
		return false;
		
		//check card num & expiryDate from Bank DataBase, and return a boolean value correspond to that
		
	}
		
}
