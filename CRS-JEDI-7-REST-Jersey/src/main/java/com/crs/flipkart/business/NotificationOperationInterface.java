/**
 * 
 */
package com.crs.flipkart.business;

/**
 * @author Aditya
 *
 */
public interface NotificationOperationInterface {
	/**
	 * Method tonotify approval status of student registration
	 * @param userId: identification number of the student
	 */
	public void Notify(int userId);
}