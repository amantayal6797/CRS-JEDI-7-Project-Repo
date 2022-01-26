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
import com.crs.flipkart.constants.AuthorizationDB;

/**
 * @author Ashruth
 *
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
				case 1:
					courseRegistrationObj.registerProfessorCourse(userId);
					break;
						
				case 2: 
					courseRegistrationObj.viewProfessorCourses(userId);
					break;

				case 3:
					courseRegistrationObj.viewEnrolledStudents(userId);
					break;
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
