/**
 * 
 */
package com.crs.flipkart.business;

import java.util.Scanner;

/**
 * @author Ashruth
 *
 */
public class OnlinePayment extends PaymentOperation{
	
	private int cardNumber, date;
//	--------------------------------------------------------------------------------	
	
	
	public void cardDetail(int cardNumber, int date) {
		
		this.cardNumber=cardNumber;
		this.date=date;
		
		
		
			Scanner sc=new Scanner(System.in);
			System.out.print("Enter amount: ");
			int amount=sc.nextInt();
			sc.nextLine();
			
			
			
			if(make_payment(amount)==false)
				System.out.println("Sorry :(  Bank Technical Problem");
			else {
				System.out.println("Transcation completed :) ");
			}
			
			
			
		}
		
	}
