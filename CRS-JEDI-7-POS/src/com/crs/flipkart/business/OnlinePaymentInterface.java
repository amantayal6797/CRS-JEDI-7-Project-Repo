/**
 * 
 */
package com.crs.flipkart.business;

/**
 * @author Aditya
 *
 */
public interface OnlinePaymentInterface {
	
	public void payByCard(int userId, int cardNumber, int date);
	public boolean authenticate(int cardNumber, int date);	
}
