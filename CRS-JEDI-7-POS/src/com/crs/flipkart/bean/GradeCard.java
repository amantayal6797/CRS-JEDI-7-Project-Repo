/**
 * 
 */
package com.crs.flipkart.bean;

import java.util.ArrayList;

/**
 * @author Ashruth
 *
 */
public class GradeCard {
	private int userId;
	private ArrayList<Grade> listOfGrades=new ArrayList<Grade>();
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public ArrayList<Grade> getListOfGrades() {
		return listOfGrades;
	}
	public void setListOfGrades(ArrayList<Grade> listOfGrades) {
		this.listOfGrades = listOfGrades;
	}
}
