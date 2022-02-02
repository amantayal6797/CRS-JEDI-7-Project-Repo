/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.exception.CourseNotTaughtByProfessorException;
import com.crs.flipkart.exception.StudentNotRegisteredForCourseException;
import com.crs.flipkart.exception.UserDoesNotExistException;

/**
 * @author Aditya
 *
 */
public interface GradeCardOperationInterface {
	/**
	 * Method to grade a Student
	 * @param userId: identification number of the professor
	 * @return 
	 * @throws StudentNotRegisteredForCourseException 
	 * @throws CourseNotTaughtByProfessorException 
	 */
	public boolean assignGrade(int userId, int courseId,int studentId,String grade) throws CourseNotTaughtByProfessorException, StudentNotRegisteredForCourseException;
	/**
	 * Method to view grades a Student
	 * @param studentId: identification number of the student whose grdes are to be displayed
	 * @return 
	 * @throws UserDoesNotExistException 
	 */
	public GradeCard viewGradeCard(int studentId) throws UserDoesNotExistException;
	/**
	 * Method to generate grade card of a  Student
	 * @param studentId: identification number of the student whose grdes card is to be generated
	 */
	public GradeCard generateGradeCard(int studentId);
}