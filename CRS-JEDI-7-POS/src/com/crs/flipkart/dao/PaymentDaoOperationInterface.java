/**
 * 
 */
package com.crs.flipkart.dao;

/**
 * @author Aditya
 *
 */
public interface PaymentDaoOperationInterface {
	

/*
store the payment details in DB like
*userId
*transcationId
*amount
*/


	public void savePayment(int userId, int amount, String trasactionId);

}