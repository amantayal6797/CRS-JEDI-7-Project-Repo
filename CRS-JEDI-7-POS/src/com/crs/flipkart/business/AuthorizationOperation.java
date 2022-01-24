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
			if(student.getUserId()==id) {
				if (student.getPassword().equals(password)) {
					return student.getRole();	
				}
				else {
					return "Invalid Password"; 
				}
			}
		}
		
		for(Admin admin:AuthorizationDB.listOfAdmins) {
			if(admin==null)
				continue;
			if(admin.getUserId()==id) {
				if (admin.getPassword().equals(password)) {
					return admin.getRole();	
				}
				else {
					return "Invalid Password"; 
				}
			}
		}
		
		for(Professor professor:AuthorizationDB.listOfProfessors) {
			if(professor==null)
				continue;
			if(professor.getUserId()==id) {
				if (professor.getPassword().equals(password)) {
					return professor.getRole();	
				}
				else {
					return "Invalid Password"; 
				}
			}
		}
		
		return "Invalid ID";
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
