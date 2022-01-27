/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Scanner;

import com.crs.flipkart.business.CourseRegistrationOperation;
import com.crs.flipkart.business.CourseRegistrationOperationInterface;
import com.crs.flipkart.business.GradeCardOperation;
import com.crs.flipkart.business.GradeCardOperationInterface;
import com.crs.flipkart.business.OfflinePayment;
import com.crs.flipkart.business.OnlinePayment;

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
	public void ProfessorMenu(int userId) {
		int choice=0;
		Scanner sc = new Scanner(System.in);
		while (choice!=5) 
		{
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("Professor Menu");
			System.out.println("1. Register for Course");
			System.out.println("2. View Registered Courses");
			System.out.println("3. View Enrolled Students");
			System.out.println("4. Grade Student");
			System.out.println("5. Logout");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
			GradeCardOperationInterface gradeCardObj= new GradeCardOperation();
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
				// Professor Registers Course
				case 1:
					courseRegistrationObj.registerProfessorCourse(userId);
					break;
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
					gradeCardObj.assignGrade(userId);
						break;						
				case 5: 
						break;
						
				default:
						System.out.println("Choice not available");
			}
		}
	}
}
