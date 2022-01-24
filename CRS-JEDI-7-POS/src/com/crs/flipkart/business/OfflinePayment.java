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
	
	
	
	
	public void slipDetail(int slipNumber) {
		
		this.slipNumber=slipNumber;
		
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

