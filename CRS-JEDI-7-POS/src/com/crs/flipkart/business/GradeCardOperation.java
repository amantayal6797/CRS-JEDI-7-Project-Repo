/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.application.CRSStudentMenu;
import com.crs.flipkart.bean.Grade;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.constants.AuthorizationDB;
import com.crs.flipkart.constants.CoursesRegisteredDB;
import com.crs.flipkart.dao.CourseDaoOperation;

/**
 * @author Ashruth
 *
 */
public class GradeCardOperation extends CRSStudentMenu implements GradeCardOperationInterface {

	public void viewGradeCard(int studentId) {
		// Generate Grades 
		// Display
		GradeCard gradeCard=generateGradeCard(studentId);
		System.out.println("Grade Card");
		System.out.println("User ID:-"+studentId);
		for(Grade grade:gradeCard.getListOfGrades()) {
			System.out.println("Course ID:-"+grade.getCourseID()+" Grade:-"+grade.getGrade());
		}
	}
	
	public GradeCard generateGradeCard(int studentId) {
		GradeCard gradeCard=new GradeCard();
		ArrayList<Grade> listOfGrades=new ArrayList<Grade>();
		ArrayList<RegisteredCourse> listOfRegisteredCourses= new ArrayList<RegisteredCourse>();
		CourseDaoOperation coursedaoObj = new CourseDaoOperation();
		listOfRegisteredCourses = coursedaoObj.getRegisteredCourses(studentId);
		for(RegisteredCourse regCourse: listOfRegisteredCourses) {
			if(regCourse.getUserId()==studentId) {
				Grade grade=new Grade();
				grade.setCourseID(regCourse.getCourseID());
				grade.setGrade(regCourse.getGrade());
				listOfGrades.add(grade);
			}
		}
		
		gradeCard.setListOfGrades(listOfGrades);
		gradeCard.setUserId(studentId);
		return gradeCard;
	}
}
