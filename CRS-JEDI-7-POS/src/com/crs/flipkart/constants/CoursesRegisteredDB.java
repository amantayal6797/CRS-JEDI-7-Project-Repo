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
	public static ArrayList<RegisteredCourse> listOfRegisteredCourses = new ArrayList<RegisteredCourse>();
	
	/**
	 * @return the listOfRegisteredCourses
	 */
	public ArrayList<RegisteredCourse> getListOfRegisteredCourses() {
		return CoursesRegisteredDB.listOfRegisteredCourses;
	}
	/**
	 * @param listOfRegisteredCourses the listOfRegisteredCourses to set
	 */
	public void setListOfRegisteredCourses(ArrayList<RegisteredCourse> listOfRegisteredCourses) {
		CoursesRegisteredDB.listOfRegisteredCourses = listOfRegisteredCourses;
	}
	
	public void addRegisteredCourse(RegisteredCourse regCourse) {
		CoursesRegisteredDB.listOfRegisteredCourses.add(regCourse);
	}
	
	public void removeRegisteredCourse(int courseId,int userId) {
		for(int i=0;i<CoursesRegisteredDB.listOfRegisteredCourses.size();i++) {
			if(CoursesRegisteredDB.listOfRegisteredCourses.get(i).getUserId()==userId&&
					CoursesRegisteredDB.listOfRegisteredCourses.get(i).getCourseID()==courseId) {
				CoursesRegisteredDB.listOfRegisteredCourses.remove(i);
				break;
			}	
		}
		
	}
	public int getNumCourses(int studentId) {
		int cnt=0;
		for(RegisteredCourse regCourse:CoursesRegisteredDB.listOfRegisteredCourses) {
			if (regCourse.getUserId()==studentId)
				cnt++;
		}
		return cnt;
	}
	public boolean checkExisting(int courseId,int studentId) {
		for(RegisteredCourse regCourse:CoursesRegisteredDB.listOfRegisteredCourses) {
			if (regCourse.getUserId()==studentId&&regCourse.getCourseID()==courseId)
				return true;
		}
		return false;
	}
	
}
	
