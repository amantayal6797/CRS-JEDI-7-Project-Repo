/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Professor;

/**
 * @author aditya.gupta3
 *
 */
	public interface AdminOperationInterface {
		public void addCourse(int courseID,String courseName, int credits); // Method to add a Course to Course Catalog
		public void dropCourse(int courseID); // Method to drop a Course to Course Catalog
		public void approveUser(int userId); //Method to approve a Student
		public void addProfessor(Professor professor); //Method to add a Professor to DB
		public void assignCourseToProfessor(int profId,ArrayList<Integer>CourseIdList,int ch); // Method to assign Course to a Professor
		public void viewGradeCard(int userId);//method to view the gradecard of a student
	}

