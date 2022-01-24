/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.Admin;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.constants.AuthorizationDB;

/**
 * @author Ashruth
 *
 */
public class AuthorizationOperation  implements AuthorizationOperationInterface {
	
	public String Authorize(int id,String password) {
		for(Student student:AuthorizationDB.listOfStudents) {
			if(student==null)
				continue;
			if(student.getUserId()==id&&student.getPassword().equals(password)) {
				return student.getRole();
			}
		}
		for(Admin admin:AuthorizationDB.listOfAdmins) {
			if(admin==null)
				continue;
			if(admin.getUserId()==id&&admin.getPassword().equals(password)) {
				return admin.getRole();
			}
		}
		for(Professor prof:AuthorizationDB.listOfProfessors) {
			if(prof==null)
				continue;
			if(prof.getUserId()==id&&prof.getPassword().equals(password)) {
				return prof.getRole();
			}
		}
		return "Invalid";
	}
	
	public boolean updatePasswordCheck(int userId, String nPassword, String cNPassword) {
		// check whether userId is present in DB or not
		// if user present in DB, then check nPassword and cNPassword is same or not
		// if both are same, then update in DB

		if (nPassword==cNPassword) {
			// update in DB
			for (Student student: AuthorizationDB.listOfStudents) {
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
