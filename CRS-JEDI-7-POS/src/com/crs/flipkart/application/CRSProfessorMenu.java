/**
 * 
 */
package com.crs.flipkart.application;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.CourseRegistrationOperation;
import com.crs.flipkart.business.CourseRegistrationOperationInterface;
import com.crs.flipkart.business.GradeCardOperation;
import com.crs.flipkart.business.GradeCardOperationInterface;
import com.crs.flipkart.business.OfflinePayment;
import com.crs.flipkart.business.OnlinePayment;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.CourseDaoOperationInterface;
import com.crs.flipkart.dao.ProfessorDaoOperation;
import com.crs.flipkart.dao.ProfessorDaoOperationInterface;
import com.crs.flipkart.exception.NoUnallottedCourseException;

/**
 * 
 * User Interactive menu if user logs in is a professor
 * 
 * @author Ashruth
 *
 */


/*
Manages the major roles of professor based on user inputs
*/
public class CRSProfessorMenu extends CRSApplication {
	
	ProfessorDaoOperationInterface profDaoObj=new ProfessorDaoOperation(); 
	public void ProfessorMenu(int userId) {
		
		CourseDaoOperationInterface courseDAOobj = new CourseDaoOperation();
		
		Professor professor=profDaoObj.getProfessor(userId);
		System.out.println("***********************************************************************");
		System.out.println("Welcome "+professor.getUserName());

		
		LocalTime localTime = LocalTime.now();
		System.out.print("Login Time:- " + localTime+"  ");
		
		LocalDate localDate = LocalDate.now();
		System.out.println("\t Login Date:- "+ localDate.getDayOfMonth()+" "+localDate.getMonth()+", "+localDate.getYear());
		int choice=0;
		Scanner sc = new Scanner(System.in);
		while (choice!=5) 
		{
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.printf("%38s \n","Professor Menu");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("1. Register for Course");
			System.out.println("2. View Registered Courses");
			System.out.println("3. View Enrolled Students");
			System.out.println("4. Grade Student");
			System.out.println("5. Logout");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("Enter your choice: ");
			CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
			GradeCardOperationInterface gradeCardObj= new GradeCardOperation();
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
				// Professor Registers Course
				case 1:
					try {
						 ArrayList<Course> courseList=new ArrayList<Course>();
						 courseList=courseDAOobj.getUnregisteredCourses(userId);
						 if (courseList.size()==0) {
							 throw new NoUnallottedCourseException();
						 }
						 System.out.println("Available Courses");
						 ArrayList<Integer> courseIdList=new ArrayList<Integer>();
						System.out.println("---------------------------------------------------------------------------------------------------");
						System.out.printf("|%-20s | %-20s | %-20s | %-20s|\n","Course Id","Course Name","Course Credits","Course Prerequisites");
						System.out.println("---------------------------------------------------------------------------------------------------");

						for(Course course:courseList) {
							System.out.printf("|%-20d | %-20d | %-20s|  %-20d|\n",course.getCourseID(),course.getCourseName(),course.getCredits(),course.getPrerequisites());

							courseIdList.add(course.getCourseID());
							}
						 System.out.println("Enter Course ID to register");
						 choice=sc.nextInt();
						 sc.nextLine();
						 courseRegistrationObj.registerProfessorCourse(userId,courseIdList,choice);
							break;
					}
					catch(Exception e) {
						System.out.println(e.getMessage());
					}
					
				// View Course	of Professor
				case 2: 
					courseRegistrationObj.viewProfessorCourses(userId);
					break;
				// View Enrolled Students in a course
				case 3:
					courseRegistrationObj.viewEnrolledStudents(userId);
					break;
				// Assign Grade
				case 4:
					System.out.println("Enter CourseID");
					int courseId=sc.nextInt();
					sc.nextLine();
					System.out.println("Enter Student ID");
					int studentId=sc.nextInt();
					sc.nextLine();
					System.out.println("Enter Grade");
					String grade=sc.nextLine();
					gradeCardObj.assignGrade(userId,courseId,studentId,grade);
						break;						
				case 5: 
						break;
						
				default:
						System.out.println("Choice not available");
			}
		}
	}
}
