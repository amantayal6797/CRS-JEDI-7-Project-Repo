/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author Ashruth
 *
 */
public class GradeCard {
	private int rollNo;
	private Grade listOfGrades[]= new Grade[4];
	/**
	 * @return the rollNo
	 */
	public int getRollNo() {
		return rollNo;
	}
	/**
	 * @param rollNo the rollNo to set
	 */
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public void setListOfGrades(Grade listOfGrades[]){
		this.listOfGrades=listOfGrades;
	}
	public Grade[] getListOfGrades() {
		return this.listOfGrades;
	}
}
