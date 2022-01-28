/**
 * 
 */
package com.crs.flipkart.business;

/**
 * @author Aditya
 *
 */
public interface OnlinePaymentInterface {
	/**
	 * Method to pay using online mode
	 * @param userId: identification number
	 * @param cardNumber: card number
	 * @param date: expiry date card
	 */
	public void payByCard(int userId, int cardNumber, int date);
	/**
	 * Method toc heck card num & expiryDate from Bank DataBase
	 * @param cardNumber: card number
	 * @param date: validity date card
	 * @return : boolean value whether card is valid or not
	 */
	public boolean authenticate(int cardNumber, int date);	
}