/**
 * 
 */
package com.crs.flipkart.dao;

/**
 * @author Ashruth
 *
 */
	import com.crs.flipkart.bean.Course;

	public interface AdminDaoOperationInterface {



	    public void addCourse (Course course) ;
	    public void dropCourse(int courseId);
	}

