/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.exception.CourseDoesNotExistException;
import com.crs.flipkart.exception.CourseIDAlreadyExistException;
import com.crs.flipkart.exception.UserAlreadyExistsException;
import com.crs.flipkart.exception.UserDoNotExistException;
import com.crs.flipkart.exception.UserDoesNotExistException;

/**
 * @author aditya.gupta3
 *
 */
	public interface AdminOperationInterface {
		public boolean addCourse(int courseID,String courseName, int credits) throws CourseIDAlreadyExistException; // Method to add a Course to Course Catalog
		public boolean dropCourse(int courseID) throws CourseDoesNotExistException; // Method to drop a Course to Course Catalog
		public boolean approveUser(int userId) throws UserDoNotExistException; //Method to approve a Student
		public boolean addProfessor(Professor professor) throws UserAlreadyExistsException; //Method to add a Professor to DB
		public boolean assignCourseToProfessor(int profId,ArrayList<Integer>CourseIdList,int ch) throws CourseDoesNotExistException, UserDoesNotExistException; // Method to assign Course to a Professor
		public GradeCard viewGradeCard(int userId) throws UserDoesNotExistException;//method to view the gradecard of a student
	}

