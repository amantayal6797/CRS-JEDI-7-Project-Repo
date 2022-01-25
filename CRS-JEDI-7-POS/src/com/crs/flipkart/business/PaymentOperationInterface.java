package com.crs.flipkart.business;

public interface PaymentOperationInterface {

	public boolean makePayment(int userId, int amount);
	public void generateBill(int amount);
}
