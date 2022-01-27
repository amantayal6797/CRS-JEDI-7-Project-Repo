/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.Admin;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.constants.AuthorizationDB;
import com.crs.flipkart.dao.UserDaoOperation;

/**
 * @author Ashruth
 *
 */
public class AuthorizationOperation  implements AuthorizationOperationInterface {
	
public String Authorize(int id,String password) {
		
		UserDaoOperation userDaoOperation = new UserDaoOperation();
		return userDaoOperation.Authorize(id, password);
	}
	
	public boolean updatePasswordCheck(int userId, String nPassword, String cNPassword) {
			
		if (nPassword.equals(cNPassword)) {	
			UserDaoOperation userDaoOperation = new UserDaoOperation();
			int status = userDaoOperation.updatePasswordCheck(userId, nPassword);
			
			if(status==1) {
				System.out.println("Password updated successfully");
				return true;
			}else if(status==2){
				System.out.println("Error");
				return false;
			}else if(status==3) {
			System.out.println("Student id not found");
			return false;
			}
			
		} else {
			System.out.println("Both passwords don't match");
			return false;
		}
		return true;
	}
}
