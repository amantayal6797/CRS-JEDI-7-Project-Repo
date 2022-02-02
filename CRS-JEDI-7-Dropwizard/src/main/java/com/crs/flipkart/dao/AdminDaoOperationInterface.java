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
	    public boolean addCourse (Course course) ;


		/*
			delete a course from course catalog
		*/
	    public boolean dropCourse(int courseId);
	    /*
	     * Get Details of Admin from database
	     */
	    public Admin getAdmin(int userID);
	}
