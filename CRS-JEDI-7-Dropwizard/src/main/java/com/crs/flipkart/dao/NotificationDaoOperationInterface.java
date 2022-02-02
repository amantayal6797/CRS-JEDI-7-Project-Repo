/**
 * 
 */
package com.crs.flipkart.dao;

/**
 * @author Aditya
 *
 */
public interface NotificationDaoOperationInterface {

	public int geStatus(int userId);	//fetch the status from DB (0 or 1)
	
	public void updateStatus(int userId,int status);	//set status along with userId
	
	public void insertStatus(int userId);
	
}