/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.application.CRSStudentMenu;
import com.crs.flipkart.bean.Grade;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.constants.AuthorizationDB;
import com.crs.flipkart.constants.CoursesRegisteredDB;

/**
 * @author Ashruth
 *
 */
public class GradeCardOperation extends CRSStudentMenu implements GradeCardOperationInterface {

	public void viewGradeCard(int studentId) {
		// Generate Grades 
		// Display
		Student student=authObj.getStudentInfo(studentId);
		GradeCard gradeCard=generateGradeCard(student);
		System.out.println("Grade Card");
		System.out.println("Name:-"+student.getUserName()+" RollNo:-"+student.getRollNo());
		for(Grade grade:gradeCard.getListOfGrades()) {
			System.out.println("Course ID:-"+grade.getCourseID()+" Grade:-"+grade.getGrade());
		}
	}
	
	GradeCard generateGradeCard(Student student) {
		GradeCard gradeCard=new GradeCard();
		int rollNo=student.getRollNo();
		Grade listOfGrades[]=new Grade[4];
		int index=0;
		for(int courseID:student.getEnrolledCourses()) {
			for(RegisteredCourse regCourse: coursesRegisteredDB.getListOfRegisteredCourses()) {
				if(regCourse.getCourseID()==courseID&&regCourse.getRollNo()==rollNo) {
					Grade grade=new Grade();
					grade.setCourseID(courseID);
					grade.setGrade(regCourse.getGrade());
					listOfGrades[index]=grade;
					index++;
				}
			}
		}
		gradeCard.setListOfGrades(listOfGrades);
		gradeCard.setRollNo(rollNo);
		return gradeCard;
	}
}
