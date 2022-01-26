/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Scanner;

import com.crs.flipkart.business.AdminOperation;
import com.crs.flipkart.business.CourseRegistrationOperation;
import com.crs.flipkart.business.CourseRegistrationOperationInterface;

/**
 * @author aditya.gupta3
 *
 */
public class CRSAdminMenu extends CRSApplication {
	public void AdminMenu(int userId) {
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
			case 1:
				courseRegistrationObj.viewCourses();
				break;
				
			case 2:
				adminOperation.addCourse();
				break;
				
			case 3:
				adminOperation.dropCourse();
				break;
				
			case 4:
				adminOperation.approveUser();
				break;
	
			case 5:
				adminOperation.addProfessor();
				break;
			
			case 6:
				adminOperation.assignCourseToProfessor();
				break;

			case 7:
				adminOperation.viewGradeCard();
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
