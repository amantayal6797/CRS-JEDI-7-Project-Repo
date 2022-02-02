package com.crs.flipkart.business;

public interface PaymentOperationInterface {

	public boolean makePayment(int userId, int amount,int modeofpayment);
	public void generateBill(int userId, int amount,int modeofpayment);
}
