/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.dao.UserDaoOperationInterface;
import com.crs.flipkart.exception.PasswordNotMatchingException;
import com.crs.flipkart.exception.UserDoesNotExistException;

/**
 * @author aditya.gupta3
 *
 */
public class AuthorizationOperation implements AuthorizationOperationInterface {

	UserDaoOperationInterface userDaoOperation = new UserDaoOperation();
	
	@Override
	public String Authorize(int id, String password) {
		return userDaoOperation.Authorize(id, password);
	}

	@SuppressWarnings("finally")
	@Override
	public boolean updatePasswordCheck(int userId, String nPassword, String cNPassword) {
		try {
		if (nPassword.equals(cNPassword)) {	
			int status = userDaoOperation.updatePasswordCheck(userId, nPassword);
			
			if(status==1) {
				System.out.println("Password updated successfully");
				return true;
			}else if(status==2){
				System.out.println("Error");
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
