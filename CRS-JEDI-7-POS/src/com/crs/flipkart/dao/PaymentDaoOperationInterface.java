/**
 * 
 */
package com.crs.flipkart.dao;

/**
 * @author Aditya
 *
 */
public interface PaymentDaoOperationInterface {
	
	public void savePayment(int userId, int amount, String trasactionId);

}
