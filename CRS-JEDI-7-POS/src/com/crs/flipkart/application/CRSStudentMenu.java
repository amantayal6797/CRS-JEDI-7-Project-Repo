/**
 * 
 */
package com.crs.flipkart.application;
import java.util.*;

import com.crs.flipkart.business.CourseRegistrationOperation;
import com.crs.flipkart.business.GradeCardOperation;
import com.crs.flipkart.business.PaymentOperation;
import com.crs.flipkart.constants.AuthorizationDB;

/**
 * @author Nitish
 *
 */
public class CRSStudentMenu extends CRSApplication {
	
	public void StudentMenu(int studentId) {
		int choice=0;
		Scanner sc = new Scanner(System.in);
		while (choice!=8) 
		{
			System.out.println();
			System.out.println("Student Menu");
			System.out.println("1. Course Registration");
			System.out.println("2. Add Course");
			System.out.println("3. Drop Course");
			System.out.println("4. View Course");
			System.out.println("5. View Registered Courses");
			System.out.println("6. View grade card");
			System.out.println("7. Make Payment");
			System.out.println("8. Logout");
			System.out.println();
			CourseRegistrationOperation courseRegistrationObj=new CourseRegistrationOperation();
			GradeCardOperation gradeCardObj=new GradeCardOperation();
			PaymentOperation paymentObj=new PaymentOperation();
			choice = sc.nextInt();
			
			switch (choice) {
				case 1: 
					courseRegistrationObj.registerCourses();
						break;
						
				case 2: 
					courseRegistrationObj.addCourse(studentId);
						break;

				case 3:
						
					courseRegistrationObj.dropCourse(studentId);
						break;

				case 4:
					courseRegistrationObj.viewCourses();
						break;

				case 5:
					courseRegistrationObj.viewRegisteredCourse(studentId);
						break;

				case 6:
						gradeCardObj.viewGradeCard(studentId);
						break;
						
				case 7:
						paymentObj.make_payment(studentId);
						break;
						
				case 8: 
						break;
						
				default:
						System.out.println("Choice not available");
			}
		}
	}
}
