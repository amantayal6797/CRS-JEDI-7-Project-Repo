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
	
	public void viewGradeCard(int studentId);
	GradeCard generateGradeCard(Student student);
}
