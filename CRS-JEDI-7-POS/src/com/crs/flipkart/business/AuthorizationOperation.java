/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.dao.UserDaoOperationInterface;

/**
 * @author aditya.gupta3
 *
 */
public class AuthorizationOperation implements AuthorizationOperationInterface {

	UserDaoOperationInterface userDaoOperation = new UserDaoOperation();
	private static Logger logger = Logger.getLogger(AuthorizationOperation.class);
	
	@Override
	public String Authorize(int id, String password) {
		return userDaoOperation.Authorize(id, password);
	}

	@Override
	public boolean updatePasswordCheck(int userId, String nPassword, String cNPassword) {
		if (nPassword.equals(cNPassword)) {	
			int status = userDaoOperation.updatePasswordCheck(userId, nPassword);
			
			if(status==1) {
				System.out.println("Password updated successfully");
				return true;
			}else if(status==2){
				logger.error("Error");
				return false;
			}else if(status==3) {
			logger.error("Student id not found");
			return false;
			}
			
		} else {
			System.out.println("Both passwords don't match");
			return false;
		}
		return true;
	}

}
