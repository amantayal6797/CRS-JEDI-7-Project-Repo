/**
 * 
 */
package com.crs.flipkart.application;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import com.crs.flipkart.bean.Admin;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.AdminOperation;
import com.crs.flipkart.business.CourseRegistrationOperation;
import com.crs.flipkart.business.CourseRegistrationOperationInterface;
import com.crs.flipkart.dao.AdminDaoOperation;
import com.crs.flipkart.dao.AdminDaoOperationInterface;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.CourseDaoOperationInterface;
import com.crs.flipkart.exception.NoUnallottedCourseException;
import com.crs.flipkart.exception.UserDoesNotExistException;

/**
 * @author aditya.gupta3
 *
 *  The Client Side Application for displaying and going forward with
 *  Administrator related operations and functionalities.
 */


 /**
 * Main Function to display, choose and then call the respective Administrator
 * Operations.
 */
public class CRSAdminMenu extends CRSApplication {
	
	AdminDaoOperationInterface adminDaoObj=new AdminDaoOperation();
	public void AdminMenu(int userId) {
		
		CourseDaoOperationInterface courseDAOobj=new CourseDaoOperation();
		
		Admin admin=adminDaoObj.getAdmin(userId);
		System.out.println("\nWelcome "+admin.getUserName());
		
		LocalTime localTime = LocalTime.now();
		System.out.println("Login Time:- " + localTime);
		
		LocalDate localDate = LocalDate.now();
		System.out.println("Login Date:- "+ localDate.getDayOfMonth()+" "+localDate.getMonth()+", "+localDate.getYear());
		
		
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		while(choice!=8) {
			System.out.println("+++++++++++++++++++++++++++++++++++");
			System.out.println("Admin Menu");
			System.out.println("1. View all courses");
			System.out.println("2. Add Course to catalog");
			System.out.println("3. Drop Course from catalog");
			System.out.println("4. Approve Student");
			System.out.println("5. Add Professor");
			System.out.println("6. Assign Course To Professor");
			System.out.println("7. View Grade Card");
			System.out.println("8. Logout");
			System.out.println("++++++++++++++++++++++++++++++++++++");
			
			choice = sc.nextInt();
			CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
			
			AdminOperation adminOperation = new AdminOperation();
			
			switch(choice) {
				// View Course case
			case 1:
				courseRegistrationObj.viewCourses();
				break;
				// Add Course case
			case 2:
				System.out.println("Enter Course ID to add: ");
				int courseID = sc.nextInt();
				System.out.println("Enter Course Name to add: ");
				String courseName = sc.next();
				System.out.println("Enter Course Credits: ");
				int credits = sc.nextInt();
				adminOperation.addCourse(courseID,courseName,credits);
				break;
				// Drop Course case
			case 3:
				System.out.println("Enter Course ID to drop: ");
				courseID = sc.nextInt();
				sc.nextLine();
				adminOperation.dropCourse(courseID);
				break;
				// Approve Course case
			case 4:
				adminOperation.approveUser();
				break;
			// Add Professor case
			case 5:
				adminOperation.addProfessor();
				break;
			// Assign Course to Professor Case
			case 6:
				try {
				ArrayList<Course> courseList=new ArrayList<Course>();
				System.out.println("Enter User ID of professor: ");
				int profId = sc.nextInt();
				 sc.nextLine();
				courseList=courseDAOobj.getUnregisteredCourses(userId);
				 
				 if (courseList.size()==0) {
					 throw new NoUnallottedCourseException();
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
				 int ch=sc.nextInt();
				 sc.nextLine();
				  
				adminOperation.assignCourseToProfessor(profId,courseIdList,ch);
				break;
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			// View Grade Card Case
			case 7:
				System.out.println("Enter User ID of student: ");
				int studId = sc.nextInt();
				sc.nextLine();
				adminOperation.viewGradeCard(studId);
				break;
			
			case 8:
				System.out.println("Successfully logged out!!!");
				return;

			default:
				System.out.println("Invalid Choice!!!");
			}
		}
	}
}
