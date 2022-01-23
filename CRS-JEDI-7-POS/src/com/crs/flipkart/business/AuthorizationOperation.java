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
public class AuthorizationOperation {
	AuthorizationDB authObj=new AuthorizationDB();
	public String Authorize(int id) {
		for(Student student:authObj.listOfStudents) {
			if(student==null)
				continue;
			if(student.getUserId()==id) {
				return student.getRole();
			}
		}
		for(Admin admin:authObj.listOfAdmins) {
			if(admin==null)
				continue;
			if(admin.getUserId()==id) {
				return admin.getRole();
			}
		}
		for(Professor prof:authObj.listOfProfessors) {
			if(prof==null)
				continue;
			if(prof.getUserId()==id) {
				return prof.getRole();
			}
		}
		return "Invalid";
	}
}
