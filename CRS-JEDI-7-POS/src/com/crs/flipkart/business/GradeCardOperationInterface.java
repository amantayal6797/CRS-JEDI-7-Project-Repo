/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.Student;

/**
 * @author Aditya
 *
 */
public interface GradeCardOperationInterface {
	/**
	 * Method to grade a Student
	 * @param userId: identification number of the professor
	 */
	public void assignGrade(int userId, int courseId,int studentId,String grade);;
	/**
	 * Method to view grades a Student
	 * @param studentId: identification number of the student whose grdes are to be displayed
	 */
	public void viewGradeCard(int studentId);
	/**
	 * Method to generate grade card of a  Student
	 * @param studentId: identification number of the student whose grdes card is to be generated
	 */
	public GradeCard generateGradeCard(int studentId);
}