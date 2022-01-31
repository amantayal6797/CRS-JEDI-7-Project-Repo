/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.exception.PasswordNotMatchingException;
import com.crs.flipkart.exception.UserDoesNotExistException;

/**
 * @author aditya.gupta3
 *
 */
public interface AuthorizationOperationInterface {
	/**
	 * Method to send notification
	 * @param id: user id
	 * @param password: password to check access permission of user
	 * @return display whether login was successful or not
	 */
	public String Authorize(int id,String password);
	/**
	 * Method to update password
	 * @param userId: user id of the user
	 * @param nPassword: new password
	 * @param cNPassword: new password to match and confirm the password
	 * @return boolean to notify whether password was updated successfully or not
	 * @throws PasswordNotMatchingException 
	 * @throws UserDoesNotExistException 
	 */
	public boolean updatePasswordCheck(int userId, String nPassword, String cNPassword) throws UserDoesNotExistException, PasswordNotMatchingException;
}