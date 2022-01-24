/**
 * 
 */
package com.crs.flipkart.constants;

import java.util.ArrayList;

import com.crs.flipkart.bean.Course;

/**
 * @author Ashruth
 *
 */
public class CourseCatalogDB {
	public static ArrayList <Course> catalog=new ArrayList<Course>();
	/**
	 * @return the catalog
	 */
	public ArrayList <Course> getCatalog() {
		return catalog;
	}
	public Course getCourse(int courseId) {
		for(Course course: catalog) {
			if (course.getCourseID()==courseId)
				return course;
		}
		return null;
	}
}
