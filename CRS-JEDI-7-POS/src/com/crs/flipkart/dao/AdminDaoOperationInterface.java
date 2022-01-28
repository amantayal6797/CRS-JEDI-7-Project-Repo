/**
 * 
 */
package com.crs.flipkart.dao;

import com.crs.flipkart.bean.Admin;
/**
 * @author Ashruth
 *
 */
	import com.crs.flipkart.bean.Course;

	public interface AdminDaoOperationInterface {


		/*
			add course details in course catalog
			*courseID
			*courseName
			*ProfessorAlloated to that course
			*credits assigned to that
		*/
	    public void addCourse (Course course) ;


		/*
			delete a course from course catalog
		*/
	    public void dropCourse(int courseId);
	    /*
	     * Get Details of Admin from database
	     */
	    public Admin getAdmin(int userID);
	}
