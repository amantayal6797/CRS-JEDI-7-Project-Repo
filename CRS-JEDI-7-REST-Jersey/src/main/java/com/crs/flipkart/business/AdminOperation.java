/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Grade;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.dao.AdminDaoOperation;
import com.crs.flipkart.dao.AdminDaoOperationInterface;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.CourseDaoOperationInterface;
import com.crs.flipkart.dao.NotificationDaoOperation;
import com.crs.flipkart.dao.NotificationDaoOperationInterface;
import com.crs.flipkart.dao.ProfessorDaoOperation;
import com.crs.flipkart.dao.ProfessorDaoOperationInterface;
import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.dao.UserDaoOperationInterface;
import com.crs.flipkart.exception.CourseDoesNotExistException;
import com.crs.flipkart.exception.CourseIDAlreadyExistException;
import com.crs.flipkart.exception.UserAlreadyExistsException;
import com.crs.flipkart.exception.UserDoNotExistException;
import com.crs.flipkart.exception.UserDoesNotExistException;
import com.crs.flipkart.validator.CourseValidator;
import com.crs.flipkart.validator.UserValidator;

/**
 * @author aditya.gupta3
 *
 */
public class AdminOperation implements AdminOperationInterface {
	
	UserDaoOperationInterface userDaoOperation = new UserDaoOperation();
	AdminDaoOperationInterface adminDaoOperation = new AdminDaoOperation();
	ProfessorDaoOperationInterface professorDaoOperation = new ProfessorDaoOperation();
	CourseDaoOperationInterface courseDaoOperation = new CourseDaoOperation();
	CourseValidator courseValidator = new CourseValidator();
	UserValidator userValidator = new UserValidator();
	
	public boolean addCourse(int courseID,String courseName, int credits) throws CourseIDAlreadyExistException {
		Course course = new Course();
		course.setCourseID(courseID);
		course.setCourseName(courseName);
		course.setProfessorAllotted(0);
		course.setCredits(credits);
			if(courseValidator.isVerified(course.getCourseID()))
				throw new CourseIDAlreadyExistException();
			
		return adminDaoOperation.addCourse(course);
		
	}
	
	public boolean dropCourse(int courseID) throws CourseDoesNotExistException {
		if(!courseValidator.isVerified(courseID)) {
			throw new CourseDoesNotExistException(courseID);
		}
		return adminDaoOperation.dropCourse(courseID);
	}
	
	public boolean approveUser(int userId) throws UserDoNotExistException {
		boolean flag=userDaoOperation.approveUser(userId);
		
		NotificationDaoOperationInterface notificationObj = new NotificationDaoOperation();
		notificationObj.updateStatus(userId, 1);
		return flag;
	}
	
	public boolean addProfessor(Professor professor) throws UserAlreadyExistsException {
		if (userValidator.checkIfExists(professor.getUserId())) 
			throw new UserAlreadyExistsException(professor.getUserId());
		
		return professorDaoOperation.addProfessor(professor);
	}
	
	public boolean assignCourseToProfessor(int profId,ArrayList<Integer>CourseIdList,int ch) throws CourseDoesNotExistException,UserDoesNotExistException {
		if (!userValidator.checkIfExists(profId)) {
			throw new UserDoesNotExistException(profId);
		}
		
		CourseRegistrationOperation courseRegistrationOperation = new CourseRegistrationOperation();
		return courseRegistrationOperation.registerProfessorCourse(profId,CourseIdList,ch);
		}
		
	
	
	public GradeCard viewGradeCard(int userId) throws UserDoesNotExistException {
		GradeCardOperationInterface gradeCardObj = new GradeCardOperation();
		GradeCard gradeCard=gradeCardObj.viewGradeCard(userId);	
		return gradeCard;
		}
		
	
}
