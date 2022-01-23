/**
 * 
 */
package com.crs.flipkart.constants;

import java.util.ArrayList;

import com.crs.flipkart.bean.RegisteredCourse;

/**
 * @author Ashruth
 *
 */
public class CoursesRegisteredDB {
	ArrayList<RegisteredCourse> listOfRegisteredCourses = new ArrayList<RegisteredCourse>();
	
	/**
	 * @return the listOfRegisteredCourses
	 */
	public ArrayList<RegisteredCourse> getListOfRegisteredCourses() {
		return listOfRegisteredCourses;
	}
	/**
	 * @param listOfRegisteredCourses the listOfRegisteredCourses to set
	 */
	public void setListOfRegisteredCourses(ArrayList<RegisteredCourse> listOfRegisteredCourses) {
		this.listOfRegisteredCourses = listOfRegisteredCourses;
	}

	
}
