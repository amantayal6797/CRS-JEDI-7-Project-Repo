/**
 * 
 */
package com.crs.flipkart.business;

/**
 * @author Aditya
 *
 */
public interface OfflinePaymentInterface {
	/**
	 * Method to pay by cash
	 * @param userId: identification number of user making the payment
	 * @param slipNumber:
	 * @return 
	 */
	public boolean payByCash(int userId, int slipNumber);
	/**
	 * Method to check slipNumber from Bank DataBase@
	 * @param slipNumber: slip number to verified from db
	 * @return: return aa boolean response whether slip number is in DB or not
	 */
	public boolean authenticate(int slipNumber);

}