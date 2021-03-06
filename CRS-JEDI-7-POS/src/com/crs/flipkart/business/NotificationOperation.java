/**
 * 
 */
package com.crs.flipkart.business;

import org.apache.log4j.Logger;

import com.crs.flipkart.dao.NotificationDaoOperation;
import com.crs.flipkart.dao.NotificationDaoOperationInterface;

/**
 * @author Aditya
 *
 */
public class NotificationOperation implements NotificationOperationInterface {

	private static Logger logger = Logger.getLogger(CourseRegistrationOperation.class);
	public void Notify(int userId){
		
		NotificationDaoOperationInterface notificationObj = new NotificationDaoOperation();
		
		int status = notificationObj.geStatus(userId);
		
		if(status==1) {
			logger.info("You are approved by Admin. Now you can enroll for courses");
			notificationObj.updateStatus(userId,2);
		}
	}
	
}
