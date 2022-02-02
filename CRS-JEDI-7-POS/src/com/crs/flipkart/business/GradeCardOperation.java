/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.crs.flipkart.application.CRSStudentMenu;
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
public class GradeCardOperation extends CRSStudentMenu implements GradeCardOperationInterface {
	UserDaoOperationInterface userDaoOperation =new UserDaoOperation();
	CourseValidator courseValidator = new CourseValidator();
	UserValidator userValidator = new UserValidator();
	StudentValidator studentValidator = new StudentValidator();
	private static Logger logger = Logger.getLogger(GradeCardOperation.class);
	public void viewGradeCard(int studentId) {
		try {
		if (!userValidator.checkIfExists(studentId)) {
			throw new UserDoesNotExistException(studentId);
			}
		GradeCard gradeCard=generateGradeCard(studentId);
		logger.info("Grade Card");
		logger.info("User ID:-"+studentId);
		logger.info("---------------------------------------------------------------------------------------------------\n");
		logger.info(String.format("|%-20s | %-20s|\n","Course Id","Grade"));
		logger.info("---------------------------------------------------------------------------------------------------\n");

		for(Grade grade:gradeCard.getListOfGrades()) {
			logger.info(String.format("|%-20d | %-20s|\n",grade.getCourseID(),grade.getGrade()));

		}
		}catch(UserDoesNotExistException e) {
			System.out.println(e.getMessage());
		}
		
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
	
	public void assignGrade(int userId, int courseId,int studentId,String grade) {
		try {
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
		if(!studentValidator.isPresentInList(enrolledStudents, studentId)) {
			throw new StudentNotRegisteredForCourseException(courseId,studentId);
		}
		System.out.println("Grade Assigned Succesfully");
		}catch(CourseNotTaughtByProfessorException | StudentNotRegisteredForCourseException e ) {
			System.out.println(e.getMessage());
		}
	}
}
