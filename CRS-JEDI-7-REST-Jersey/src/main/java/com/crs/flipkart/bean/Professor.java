/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author Ashruth
 *
 */
public class Professor extends User{
	private String department;
	private String listOfCourseAssigned[] = new String[10];
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the listOfCourseAssigned
	 */
	public String[] getListOfCourseAssigned() {
		return listOfCourseAssigned;
	}
	/**
	 * @param listOfCourseAssigned the listOfCourseAssigned to set
	 */
	public void setListOfCourseAssigned(String listOfCourseAssigned[]) {
		this.listOfCourseAssigned = listOfCourseAssigned;
	}
}
