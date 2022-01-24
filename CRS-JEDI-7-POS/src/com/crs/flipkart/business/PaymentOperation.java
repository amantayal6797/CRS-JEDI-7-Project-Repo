/**
 * 
 */
package com.crs.flipkart.business;

import java.util.Scanner;

/**
 * @author Ashruth
 *
 */
public class PaymentOperation implements PaymentOperationInterface {

//--------------------------------------------------------------------------------	
	private int transactionID;
	int amount;
//	------------------------------------------------------------------------------
	
	
	public boolean make_payment(int amount) {
		
			generateBill();
			
			return true;		//paymnet done
		
			
	}
	
	
//	--------------------------------------------------------------------------------		
	public void generateBill() {		// this function called from make_payment function
		System.out.println("Fees Paid.");
//		transactionID=random();			generate transID by random generator
		
	}
	
	
//	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
//				*****	FUNCTION OVERLOADING	*****
	
	public boolean authenticate(int cardNumber, int date) {
		return false;
		
		//check card num & expiryDate from Bank DataBase, and return a boolean value correspond to that
		
	}
	
	public boolean authenticate(int slipNumber) {
		return false;
			
			//check slipNumber from Bank DataBase, and return a boolean value correspond to that
			
	}
	
//	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
}






