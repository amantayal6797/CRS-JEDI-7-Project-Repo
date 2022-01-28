/**
 * 
 */
package com.crs.flipkart.business;

/**
 * @author aditya.gupta3
 *
 */
	public interface AdminOperationInterface {
		public void addCourse(); // Method to add a Course to Course Catalog
		public void dropCourse(); // Method to drop a Course to Course Catalog
		public void approveUser(); //Method to approve a Student
		public void addProfessor(); //Method to add a Professor to DB
		public void assignCourseToProfessor(); // Method to assign Course to a Professor
		public void viewGradeCard();//method to view the gradecard of a student
	}

