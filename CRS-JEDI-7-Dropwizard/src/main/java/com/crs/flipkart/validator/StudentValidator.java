/**
 * 
 */
package com.crs.flipkart.validator;

import java.util.ArrayList;

import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.CourseDaoOperationInterface;

/**
 * @author aditya
 *
 */
public class StudentValidator {
	CourseDaoOperationInterface courseDAOobj=new CourseDaoOperation();
	
	public boolean hasEnrolledCourses (int userId) {
		ArrayList<RegisteredCourse> enrolledCourses=courseDAOobj.getRegisteredCourses(userId);
		if (enrolledCourses.size()==0) {
			return false;
		}
		
		return true;
	}
	
	public boolean isPresentInList (ArrayList<Integer> studentIdList, int studentId) {
		if (studentIdList.contains(studentId)) {
			return true;
		}
		
		return false;
	}
	
	public boolean hasCompletedRegistration (int userId) {
		ArrayList<RegisteredCourse> enrolledCourses=courseDAOobj.getRegisteredCourses(userId);
		if(enrolledCourses.size()==4) {
			return true;
		}
		
		return false;
	}
}
