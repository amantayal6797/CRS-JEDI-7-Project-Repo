/**
 * 
 */
package com.crs.flipkart.business;

import org.apache.log4j.Logger;

import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.dao.UserDaoOperationInterface;
import com.crs.flipkart.exception.PasswordNotMatchingException;
import com.crs.flipkart.exception.UserDoesNotExistException;
import com.crs.flipkart.validator.UserValidator;

/**
 * @author aditya.gupta3
 *
 */
public class AuthorizationOperation implements AuthorizationOperationInterface {

	UserDaoOperationInterface userDaoOperation = new UserDaoOperation();
	UserValidator userValidator = new UserValidator();
	private static Logger logger = Logger.getLogger(AuthorizationOperation.class);
	
	@Override
	public String Authorize(int id, String password) {
		return userDaoOperation.Authorize(id, password);
	}

	@SuppressWarnings("finally")
	@Override
	public boolean updatePasswordCheck(int userId, String nPassword, String cNPassword) {
		try {
		if (userValidator.equals(nPassword, cNPassword)) {	
			int status = userDaoOperation.updatePasswordCheck(userId, nPassword);
			
			if(status==1) {
				logger.info("Password updated successfully");
				return true;
			}else if(status==2){
				logger.error("Error");
				return false;
			}else if(status==3) {
			throw new UserDoesNotExistException(userId);
			}
			
		} else {
			throw new PasswordNotMatchingException();
		}
		}
		catch(UserDoesNotExistException | PasswordNotMatchingException e ) {
			System.out.println(e.getMessage());
		}
		finally {
		return false;
		}
	}

}
