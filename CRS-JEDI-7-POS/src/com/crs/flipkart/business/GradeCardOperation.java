/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.Scanner;

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

/**
 * @author Ashruth
 *
 */
public class GradeCardOperation extends CRSStudentMenu implements GradeCardOperationInterface {
	UserDaoOperationInterface userDaoOperation =new UserDaoOperation();
	public void viewGradeCard(int studentId) {
		// Generate Grades 
		// Display
		if (userDaoOperation.getUser(studentId)==null) {
			System.out.println("Student does not exists with this userId");
			return;}
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
	
	public void assignGrade(int userId) {
		CourseDaoOperationInterface courseDAOobj=new CourseDaoOperation();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter CourseID");
		int courseId=sc.nextInt();
		sc.nextLine();
		boolean flag=false;
		ArrayList<Course> professorCourses=courseDAOobj.getProfessorCourses(userId);
		for(Course course:professorCourses) {
			if(course.getCourseID()==courseId) {
				flag=true;
				break;
			}
		}
		if(flag==false) {
			System.out.println("Entered Course is not taught by you.");
			return;
		}
		System.out.println("Enter Student ID");
		int studentId=sc.nextInt();
		sc.nextLine();
		ArrayList <Integer> enrolledStudents= courseDAOobj.getEnrolledStudents(courseId);
		if(!enrolledStudents.contains(studentId)) {
			System.out.println("Entered Student is not registered for the given course");
			return;
		}
		System.out.println("Enter Grade");
		String grade=sc.nextLine();
		courseDAOobj.setGrade(studentId,courseId,grade);
		System.out.println("Grade Assigned Succesfully");
		
	}
}
