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
	public void assignGrade(int userId);
	public void viewGradeCard(int studentId);
	public GradeCard generateGradeCard(int studentId);
}
