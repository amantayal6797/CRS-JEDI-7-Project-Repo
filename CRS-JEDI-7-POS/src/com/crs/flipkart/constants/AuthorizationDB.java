/**
 * 
 */
package com.crs.flipkart.constants;

import java.util.ArrayList;

import com.crs.flipkart.bean.Admin;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;

/**
 * @author Ashruth
 *
 */
public class AuthorizationDB {
	public static ArrayList <Student> listOfStudents=new ArrayList<Student>();
	public static ArrayList <Admin> listOfAdmins=new ArrayList<Admin>();
	public static ArrayList <Professor> listOfProfessors=new ArrayList<Professor>();
	
	
	/**
	 * @return the listOfStudents
	 */
	public ArrayList<Student> getListOfStudents() {
		return AuthorizationDB.listOfStudents;
	}

	/**
	 * @param listOfStudents the listOfStudents to set
	 */
	public void setListOfStudents(ArrayList<Student> listOfStudents) {
		AuthorizationDB.listOfStudents = listOfStudents;
	}
	
	public Student getStudentInfo(int id) {
		for(Student student:listOfStudents) {
			if(student==null)
				continue;
			if(student.getUserId()==id) {
				return student;
			}
		}
		return null;
	}
	public void setRegistration(boolean registered,int studentId) {
		for(Student student:listOfStudents) {
			if(student==null)
				continue;
			if(student.getUserId()==studentId) {
				student.setRegistered(registered);
			}
		}
	}
	public boolean getRegistration(int studentId) {
		for(Student student:listOfStudents) {
			if(student==null)
				continue;
			if(student.getUserId()==studentId) {
				return student.isRegistered();
			}
		}
		return false;
	}
	
}
