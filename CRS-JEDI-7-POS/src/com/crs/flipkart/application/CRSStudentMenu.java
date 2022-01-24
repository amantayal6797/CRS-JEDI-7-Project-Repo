/**
 * 
 */
package com.crs.flipkart.application;
import java.util.*;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.CourseRegistrationOperation;
import com.crs.flipkart.business.CourseRegistrationOperationInterface;
import com.crs.flipkart.business.GradeCardOperation;
import com.crs.flipkart.business.GradeCardOperationInterface;
import com.crs.flipkart.business.OfflinePayment;
import com.crs.flipkart.business.OnlinePayment;
import com.crs.flipkart.business.PaymentOperation;
import com.crs.flipkart.constants.AuthorizationDB;

/**
 * @author Nitish
 *
 */
public class CRSStudentMenu {
	
	public void StudentMenu(int userId) {
		int choice=0;
		Scanner sc = new Scanner(System.in);
		while (choice!=8) 
		{
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("Student Menu");
			System.out.println("1. Course Registration");
			System.out.println("2. Add Course");
			System.out.println("3. Drop Course");
			System.out.println("4. View All Courses");
			System.out.println("5. View Registered Courses");
			System.out.println("6. View grade card");
			System.out.println("7. Make Payment");
			System.out.println("8. Logout");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
			GradeCardOperationInterface gradeCardObj=new GradeCardOperation();
//			PaymentOperation paymentObj=new PaymentOperation();
			choice = sc.nextInt();
			AuthorizationDB authObj=new AuthorizationDB();
			sc.nextLine();
			switch (choice) {
				case 1: if(!authObj.getRegistration(userId))
							courseRegistrationObj.registerCourses(userId);
						else
							System.out.println("Already Registered");
						break;
						
				case 2: 
					if(authObj.getRegistration(userId))
						courseRegistrationObj.addCourse(userId);
					else {
						System.out.println("Complete Course Registration first");
					}
					break;

				case 3:
					if(authObj.getRegistration(userId))
						courseRegistrationObj.dropCourse(userId);
					else {
						System.out.println("Complete Course Registration first");
					}
					break;
				case 4:
					courseRegistrationObj.viewCourses();
						break;

				case 5:
					if(authObj.getRegistration(userId))
						courseRegistrationObj.viewRegisteredCourse(userId);
					else {
						System.out.println("Complete Course Registration first");
					}
					break;

				case 6:
					if(authObj.getRegistration(userId))
						gradeCardObj.viewGradeCard(userId);
					else {
						System.out.println("Complete Course Registration first");
					}
					break;
						
				case 7:
						System.out.println("Please select payment mode");
						System.out.println("1. Online Mode");
						System.out.println("2. Offline Mode");
						int modeChoice = sc.nextInt();
						sc.nextLine();
						if (modeChoice==1) {
							OnlinePayment onlinePayment = new OnlinePayment();
							onlinePayment.cardDetail(1, 12);
						} else if (modeChoice==2) {
							OfflinePayment offlinePayment = new OfflinePayment();
							offlinePayment.slipDetail(1);
						} else {
							System.out.println("Invalid payment mode selected");
						}
						break;
						
				case 8: 
						break;
						
				default:
						System.out.println("Choice not available");
			}
		}
	}
}
