/**
 * 
 */
package com.crs.flipkart.business;

/**
 * @author Aditya
 *
 */
public interface OfflinePaymentInterface {
	
	public void payByCash(int userId, int slipNumber);
	public boolean authenticate(int slipNumber);

}
