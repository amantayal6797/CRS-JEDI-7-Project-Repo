/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author Ashruth
 *
 */
public class RegisteredCourse {
	private int userId;
	private int courseID;
	private String grade;
	private int numOfStudents;
	/**
	 * @return the numOfStudents
	 */
	public int getNumOfStudents() {
		return numOfStudents;
	}
	/**
	 * @param numOfStudents the numOfStudents to set
	 */
	public void setNumOfStudents(int numOfStudents) {
		this.numOfStudents = numOfStudents;
	}
	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * @return the courseID
	 */
	public int getCourseID() {
		return courseID;
	}
	/**
	 * @param courseID the courseID to set
	 */
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
