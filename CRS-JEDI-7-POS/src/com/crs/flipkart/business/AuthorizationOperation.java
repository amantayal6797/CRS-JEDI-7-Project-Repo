/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.constants.AuthorizationDB;

/**
 * @author Ashruth
 *
 */
public class AuthorizationOperation extends AuthorizationDB {
	public String Authorize(int id) {
		//Authorize id and return role
		return "Student";
	}
	
	public boolean updatePasswordCheck(int userId, String nPassword, String cNPassword) {
		// check whether userId is present in DB or not
		// if user present in DB, then check nPassword and cNPassword is same or not
		// if both are same, then update in DB

		if (nPassword==cNPassword) {
			// update in DB
			for (Student student: listOfStudents) {
				if (student.getUserId()==userId) {
					student.setPassword(nPassword);
					System.out.println("Password updated successfully");
					return true;
				}
			}
			
			System.out.println("Student id not found");
			return false;
			
		} else {
			System.out.println("Both passwords don't match");
			return false;
		}
	}
}
