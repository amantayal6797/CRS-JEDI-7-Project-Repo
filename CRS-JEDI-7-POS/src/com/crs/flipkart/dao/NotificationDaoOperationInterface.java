/**
 * 
 */
package com.crs.flipkart.dao;

/**
 * @author Aditya
 *
 */
public interface NotificationDaoOperationInterface {

	public int geStatus(int userId);
	
	public void updateStatus(int userId,int status);
	
	public void insertStatus(int userId);
	
}
