/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.dao.AdminDaoOperation;
import com.crs.flipkart.dao.AdminDaoOperationInterface;
import com.crs.flipkart.dao.NotificationDaoOperation;
import com.crs.flipkart.dao.NotificationDaoOperationInterface;
import com.crs.flipkart.dao.ProfessorDaoOperation;
import com.crs.flipkart.dao.ProfessorDaoOperationInterface;
import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.dao.UserDaoOperationInterface;
import com.crs.flipkart.exception.UserAlreadyExistsException;
import com.crs.flipkart.exception.UserDoesNotExistException;
import com.crs.flipkart.validator.UserValidator;

/**
 * @author aditya.gupta3
 *
 */
public class AdminOperation implements AdminOperationInterface {
	
	UserDaoOperationInterface userDaoOperation = new UserDaoOperation();
	AdminDaoOperationInterface adminDaoOperation = new AdminDaoOperation();
	ProfessorDaoOperationInterface professorDaoOperation = new ProfessorDaoOperation();
	UserValidator userValidator = new UserValidator();

	public void addCourse(int courseID,String courseName, int credits) {
		Course course = new Course();
		course.setCourseID(courseID);
		course.setCourseName(courseName);
		course.setProfessorAllotted(0);
		course.setCredits(credits);
		
		adminDaoOperation.addCourse(course);
	}
	
	public void dropCourse(int courseID) {
		adminDaoOperation.dropCourse(courseID);
	}
	
	public void approveUser(int userId) {
		userDaoOperation.approveUser(userId);
		
		NotificationDaoOperationInterface notificationObj = new NotificationDaoOperation();
		notificationObj.updateStatus(userId, 1);
	}
	
	public void addProfessor(Professor professor) {
		try {
			if (userValidator.checkIfExists(professor.getUserId())) {
				throw new UserAlreadyExistsException(professor.getUserId());
			}
		}
		catch(UserAlreadyExistsException e) {
			System.out.println(e.getMessage());
			}
		professorDaoOperation.addProfessor(professor);
	}
	
	public void assignCourseToProfessor(int profId,ArrayList<Integer>CourseIdList,int ch) {
		try {
		if (!userValidator.checkIfExists(profId)) {
			throw new UserDoesNotExistException(profId);
		}
		
		CourseRegistrationOperation courseRegistrationOperation = new CourseRegistrationOperation();
		courseRegistrationOperation.registerProfessorCourse(profId,CourseIdList,ch);
		}
		catch(UserDoesNotExistException e){
			System.out.println(e.getMessage());
		}
	}
	
	public void viewGradeCard(int userId) {
		GradeCardOperation gradeCardOperation = new GradeCardOperation();
		gradeCardOperation.viewGradeCard(userId);
	}
}
