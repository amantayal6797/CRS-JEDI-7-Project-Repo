package com.crs.flipkart.business;

public interface PaymentOperationInterface {
	
	
	public boolean make_payment(int amount);
	public void generateBill();
	public boolean authenticate(int cardNumber, int date);
	public boolean authenticate(int slipNumber);
}
