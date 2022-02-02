/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.Scanner;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Grade;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.CourseDaoOperationInterface;
import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.dao.UserDaoOperationInterface;
import com.crs.flipkart.exception.CourseNotTaughtByProfessorException;
import com.crs.flipkart.exception.StudentNotRegisteredForCourseException;
import com.crs.flipkart.exception.UserDoesNotExistException;
import com.crs.flipkart.validator.CourseValidator;
import com.crs.flipkart.validator.StudentValidator;
import com.crs.flipkart.validator.UserValidator;

/**
 * @author Ashruth
 *
 */
public class GradeCardOperation implements GradeCardOperationInterface {
	UserDaoOperationInterface userDaoOperation =new UserDaoOperation();
	UserValidator userValidator = new UserValidator();
	CourseValidator courseValidator = new CourseValidator();
	StudentValidator studentValidator = new StudentValidator();
	
	public GradeCard viewGradeCard(int studentId) throws UserDoesNotExistException {
		if (!userValidator.checkIfExists(studentId)) {
			throw new UserDoesNotExistException(studentId);
			}
		GradeCard gradeCard=generateGradeCard(studentId);
		return gradeCard;	
	}
	
	public GradeCard generateGradeCard(int studentId) {
		GradeCard gradeCard=new GradeCard();
		ArrayList<Grade> listOfGrades=new ArrayList<Grade>();
		ArrayList<RegisteredCourse> listOfRegisteredCourses= new ArrayList<RegisteredCourse>();
		CourseDaoOperationInterface coursedaoObj = new CourseDaoOperation();
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
	
	public boolean assignGrade(int userId, int courseId,int studentId,String grade) throws CourseNotTaughtByProfessorException,StudentNotRegisteredForCourseException {
		
		CourseDaoOperationInterface courseDAOobj=new CourseDaoOperation();
		Scanner sc=new Scanner(System.in);
		boolean flag=false;
		ArrayList<Course> professorCourses=courseDAOobj.getProfessorCourses(userId);
		ArrayList<Integer> professorCourseIds = new ArrayList<Integer>();
		for(Course course:professorCourses) {
			professorCourseIds.add(course.getCourseID());
		}
		if (!courseValidator.isPresentInList(professorCourseIds, courseId)) {
			throw new CourseNotTaughtByProfessorException(courseId);
		}
		ArrayList <Integer> enrolledStudents= courseDAOobj.getEnrolledStudents(courseId);
		if(!enrolledStudents.contains(studentId)) {
			throw new StudentNotRegisteredForCourseException(courseId,studentId);
		}
		return true;
		
	}
}
