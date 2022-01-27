/**
 * 
 */
package com.crs.flipkart.business;

/**
 * @author aditya.gupta3
 *
 */
public interface AuthorizationOperationInterface {
	public String Authorize(int id,String password);
	public boolean updatePasswordCheck(int userId, String nPassword, String cNPassword);
}
