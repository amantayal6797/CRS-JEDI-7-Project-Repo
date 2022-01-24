/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.Scanner;

import com.crs.flipkart.application.CRSStudentMenu;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.constants.CourseCatalogDB;
import com.crs.flipkart.constants.CoursesRegisteredDB;

/**
 * @author Ashruth
 *
 */
public class CourseRegistrationOperation implements CourseRegistrationOperationInterface {
	CourseCatalogDB coursedbObj=new CourseCatalogDB();
	Scanner sc=new Scanner(System.in);
	CoursesRegisteredDB courseRegdbObj=new CoursesRegisteredDB();
	StudentOperationInterface studOpObj = new StudentOperation();
	public void viewRegisteredCourse(int studentId) {
		System.out.println("Register Courses for User "+studentId);
		for(RegisteredCourse regCourse:CoursesRegisteredDB.listOfRegisteredCourses) {
			if(regCourse.getUserId()==studentId) {
				Course course=coursedbObj.getCourse(regCourse.getCourseID());
				System.out.println("Course Id:-"+course.getCourseID()+"\tCourse Name:-"+course.getCourseName());
			}
		}
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}

	public void viewCourses() {
		// Display all courses in catalog
		System.out.println("Displaying All Courses");
		for(Course course:CourseCatalogDB.catalog) {
			System.out.println("Course Id:- "+course.getCourseID());
			System.out.println("Course Name:- "+course.getCourseName());
			System.out.println("Course Credits:- "+course.getCredits());
			System.out.println("Course Prerequisites:- "+course.getPrerequisites());
			System.out.println("Course Professor Id:- "+course.getProfessorAllotted());
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
		}
	}

	public void dropCourse(int studentId) {
		// take course as input
		// remove registration from CoursesRegisteredDB
		// update enrolledCourses for student
		System.out.println("Enter id of course to remove");
		int courseId=sc.nextInt();
		sc.nextLine();
		for(int i=0;i<CoursesRegisteredDB.listOfRegisteredCourses.size();i++) {
			if(CoursesRegisteredDB.listOfRegisteredCourses.get(i).getUserId()==studentId&&
					CoursesRegisteredDB.listOfRegisteredCourses.get(i).getCourseID()==courseId) {
				CoursesRegisteredDB.listOfRegisteredCourses.remove(i);
				break;
			}	
		}
		System.out.println("Course Succesfully removed");
		
		
	}

	public void addCourse(int studentId) {
		// take course as input
		// add registration in CoursesReGisteredDB
		// update enrolledCourses for student
		
		int numOfCourses=courseRegdbObj.getNumCourses(studentId);
		if(numOfCourses==4) {
			System.out.println("Already Registered for 4 courses.Drop a course before adding");
			return;
		}
		System.out.println("Enter id of course to add");
		int courseId=sc.nextInt();
		sc.nextLine();
		if(courseRegdbObj.checkExisting(courseId,studentId)) {
			System.out.println("Course Already Registered");
			return;
		}
		RegisteredCourse regCourse=new RegisteredCourse();
		regCourse.setCourseID(courseId);
		regCourse.setUserId(courseId);
		regCourse.setGrade("NA");
		CoursesRegisteredDB.listOfRegisteredCourses.add(regCourse);
		System.out.println("Course Succesfully Added\n");
		
	}

	public void registerCourses(int studentId) {
		System.out.println("Enter choices");
		System.out.println("Enter Course ID 1:-");
		int courseId=sc.nextInt();
		sc.nextLine();
		RegisteredCourse regCourse=new RegisteredCourse();
		regCourse.setCourseID(courseId);
		regCourse.setUserId(courseId);
		regCourse.setGrade("NA");
		CoursesRegisteredDB.listOfRegisteredCourses.add(regCourse);
		
		System.out.println("Enter Course ID 2:-");
		courseId=sc.nextInt();
		sc.nextLine();
		RegisteredCourse regCourse1=new RegisteredCourse();
		regCourse1.setCourseID(courseId);
		regCourse1.setUserId(courseId);
		regCourse1.setGrade("NA");
		CoursesRegisteredDB.listOfRegisteredCourses.add(regCourse1);
		
		System.out.println("Enter Course ID 3:-");
		courseId=sc.nextInt();
		sc.nextLine();
		RegisteredCourse regCourse2=new RegisteredCourse();
		regCourse2.setCourseID(courseId);
		regCourse2.setUserId(courseId);
		regCourse2.setGrade("NA");
		CoursesRegisteredDB.listOfRegisteredCourses.add(regCourse2);
		
		System.out.println("Enter Course ID 4:-");
		courseId=sc.nextInt();
		sc.nextLine();
		RegisteredCourse regCourse3=new RegisteredCourse();
		regCourse3.setCourseID(courseId);
		regCourse3.setUserId(courseId);
		regCourse3.setGrade("NA");
		CoursesRegisteredDB.listOfRegisteredCourses.add(regCourse3);
		
		System.out.println("Enter Course ID 5:-");
		courseId=sc.nextInt();
		sc.nextLine();
		
		System.out.println("Enter Course ID 6:-");
		courseId=sc.nextInt();
		sc.nextLine();
		
		System.out.println("Course Registration Done");
		studOpObj.setRegistration(studentId);
	}

}
