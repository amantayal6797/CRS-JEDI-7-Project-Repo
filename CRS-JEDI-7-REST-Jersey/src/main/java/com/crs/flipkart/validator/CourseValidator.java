/**
 * 
 */
package com.crs.flipkart.validator;

import java.util.ArrayList;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.CourseDaoOperationInterface;
import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.dao.UserDaoOperationInterface;

/**
 * @author aditya
 *
 */
public class CourseValidator {
	CourseDaoOperationInterface courseDAOobj=new CourseDaoOperation();
	
	public boolean isVerified (int courseId) {
		return courseDAOobj.verifyCourse(courseId);
	}
	
	public boolean isPresentInList (ArrayList<Integer> courseIdList, int courseId) {
		if (courseIdList.contains(courseId)) {
			return true;
		}
		
		return false;
	}
	
	public boolean equals (String s1, String s2) {
		return s1.equals(s2);
	}
}
