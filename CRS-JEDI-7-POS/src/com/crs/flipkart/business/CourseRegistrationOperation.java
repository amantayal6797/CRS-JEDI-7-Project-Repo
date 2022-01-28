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
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.CourseDaoOperationInterface;
import com.crs.flipkart.dao.StudentDaoOperation;
import com.crs.flipkart.dao.StudentDaoOperationInterface;

/**
 * @author Ashruth
 *
 */
public class CourseRegistrationOperation implements CourseRegistrationOperationInterface {
	CourseDaoOperationInterface courseDAOobj=new CourseDaoOperation();
	StudentDaoOperationInterface studDAOobj = new StudentDaoOperation();
	Scanner sc=new Scanner(System.in);
	StudentOperationInterface studOpObj = new StudentOperation();
	private static Logger logger = Logger.getLogger(CourseRegistrationOperation.class);

	public void viewRegisteredCourse(int studentId) {
		System.out.println("Register Courses for User "+studentId);
		ArrayList<RegisteredCourse> listOfRegisteredCourses=new ArrayList<RegisteredCourse>();
		listOfRegisteredCourses=courseDAOobj.getRegisteredCourses(studentId);
		for(RegisteredCourse regCourse:listOfRegisteredCourses) {
				Course course=courseDAOobj.getCourse(regCourse.getCourseID());
				System.out.println("Course Id:-"+course.getCourseID()+"\tCourse Name:-"+course.getCourseName());
			
		}
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}

	public void viewCourses() {
		// Display all courses in catalog
		System.out.println("Displaying All Courses");
		ArrayList<Course> catalog=new ArrayList<Course>();
		catalog=courseDAOobj.viewCourses();
		for(Course course:catalog) {
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
		ArrayList<RegisteredCourse> enrolledCourses=courseDAOobj.getRegisteredCourses(studentId);
		if (enrolledCourses.size()==0) {
			System.out.println("No Courses to remove.");
			return;
		}
		System.out.println("Enter id of course to remove");
		int courseId=sc.nextInt();
		sc.nextLine();
		if(!courseDAOobj.verifyCourse(courseId)) {
			logger.error("Entered Course ID does not exist");
			return;
		}
		for(RegisteredCourse regCourse:enrolledCourses) {
			if(regCourse.getCourseID()==courseId) {
				courseDAOobj.dropCourse(courseId,studentId);
				System.out.println("Course Successfully Dropped");
				return;}
		}
		System.out.println("You have not registered for the Course. Enter only registered course id");
		
		
	}

	public void addCourse(int studentId) {
		// take course as input
		// add registration in CoursesReGisteredDB
		// update enrolledCourses for student
		ArrayList<RegisteredCourse> enrolledCourses=courseDAOobj.getRegisteredCourses(studentId);
		if(enrolledCourses.size()==4) {
			System.out.println("Already Registered for 4 courses.Drop a course before adding");
			return;
		}
		System.out.println("Enter id of course to add");
		int courseId=sc.nextInt();
		sc.nextLine();
		//System.out.println(1);
		for(RegisteredCourse regCourse:enrolledCourses) {
			if(regCourse.getCourseID()==courseId) {
				logger.error("Course Already Registered");
				return;}
		}

		//System.out.println(2);
		if(!courseDAOobj.verifyCourse(courseId)) {
			logger.error("Entered Course ID does not exist");
			return;
		}

		//System.out.println(3);
		courseDAOobj.addCourse(studentId,courseId);
		System.out.println("Course Successfully Added\n");
		
	}

	public void registerCourses(int studentId) {
		viewCourses();
		ArrayList<Integer> choices=new ArrayList<Integer>();
		
		System.out.println("Enter 6 distinct choices");
		System.out.println("Enter Course ID 1:-");
		int courseId=sc.nextInt();
		sc.nextLine();
		choices.add(courseId);
		
		System.out.println("Enter Course ID 2:-");
		courseId=sc.nextInt();
		sc.nextLine();
		choices.add(courseId);
		
		System.out.println("Enter Course ID 3:-");
		courseId=sc.nextInt();
		sc.nextLine();
		choices.add(courseId);
		
		System.out.println("Enter Course ID 4:-");
		courseId=sc.nextInt();
		sc.nextLine();
		choices.add(courseId);
		
		System.out.println("Enter Course ID 5:-");
		courseId=sc.nextInt();
		sc.nextLine();
		choices.add(courseId);
		
		System.out.println("Enter Course ID 6:-");
		courseId=sc.nextInt();
		sc.nextLine();
		choices.add(courseId);
		
		int count=0;
		ArrayList<Integer> enrolled=new ArrayList<Integer>();
		for(int cId:choices) {
			if(count==4)
				break;
			if(enrolled.contains(cId)) {
				System.out.println("Course "+cId+" already registered");
				continue;
			}
			if(!courseDAOobj.verifyCourse(cId)) {
				logger.error("Invalid Course Id "+cId);
				continue;}
			
			if(courseDAOobj.getEnrolledStudents(cId).size()<10) {
				courseDAOobj.addCourse(studentId, cId);
				enrolled.add(cId);
				System.out.println("Registered Course ID "+cId);
				count++;
			}
			else
				logger.error("Course "+cId+" exceeded student limit");
		}
		System.out.println("Course Registration Done");
		System.out.println("Pay Fees Now.");
		studDAOobj.setRegistration(studentId);
	}
	
	 public void registerProfessorCourse(int userId) {
		 ArrayList<Course> courseList=new ArrayList<Course>();
		 courseList=courseDAOobj.getUnregisteredCourses(userId);
		 if (courseList.size()==0) {
			 logger.error("All registered courses have already been allotted to professors");
			 return;
		 }
		 System.out.println("Available Courses");
		 ArrayList<Integer> courseIdList=new ArrayList<Integer>();
		 for(Course course:courseList) {
				System.out.println("Course Id:- "+course.getCourseID());
				System.out.println("Course Name:- "+course.getCourseName());
				System.out.println("Course Credits:- "+course.getCredits());
				System.out.println("Course Prerequisites:- "+course.getPrerequisites());
				System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				courseIdList.add(course.getCourseID());
			}
		 System.out.println("Enter Course ID to register");
		 int choice=sc.nextInt();
		 sc.nextLine();
		 if(!courseIdList.contains(choice)) {
			 logger.error("Entered CourseID not present");
			 return;
		 }
		 courseDAOobj.setRegisterCourse(userId,choice);
		 System.out.println("Course - "+choice+" successfully allotted to Professor - "+userId);
	 }
	 
	 public void viewProfessorCourses(int userId) {
		 System.out.println("Registered Courses");
		 ArrayList<Course> courseList=new ArrayList<Course>();
		 courseList=courseDAOobj.getProfessorCourses(userId);
		 for(Course course:courseList) {
				System.out.println("Course Id:- "+course.getCourseID());
				System.out.println("Course Name:- "+course.getCourseName());
				System.out.println("Course Credits:- "+course.getCredits());
				System.out.println("Course Prerequisites:- "+course.getPrerequisites());
				System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			}
	 }
	 
	 public void viewEnrolledStudents(int userId) {
		 System.out.println("Enrolled Students");
		 ArrayList<Course> courseList=new ArrayList<Course>();
		 courseList=courseDAOobj.getProfessorCourses(userId);
		 for(Course course:courseList) {
			 System.out.println("Course ID:- "+ course.getCourseID()+"\tCourse Name:- "+course.getCourseName());
			 ArrayList <Integer> enrolledStudents = courseDAOobj.getEnrolledStudents(course.getCourseID());
			 System.out.println("Students:-");
			 for(int i:enrolledStudents) {
				 System.out.println("Student ID :- "+i);
			 }
			 System.out.println();
		 }
		 
	 }
	

}
