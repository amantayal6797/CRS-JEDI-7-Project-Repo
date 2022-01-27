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
import com.crs.flipkart.business.NotificationOperation;
import com.crs.flipkart.business.NotificationOperationInterface;
import com.crs.flipkart.business.OfflinePayment;
import com.crs.flipkart.business.OnlinePayment;
import com.crs.flipkart.business.PaymentOperation;
import com.crs.flipkart.dao.StudentDaoOperation;

/**
 * User Interactive menu for students
 * 
 * @author Nitish
 *
 */
public class CRSStudentMenu {
	/**
	 * Main Student Client which displays and manages all student related operations
	 * 
	 */
	StudentDaoOperation studDAOobj = new StudentDaoOperation();
	public void StudentMenu(int userId) {
		int choice=0;
		Scanner sc = new Scanner(System.in);
		
		NotificationOperationInterface notificationOperation = new NotificationOperation();
		notificationOperation.Notify(userId);
		
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
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
				// Course Registration 
				case 1: if(!studDAOobj.isRegistered(userId))
							courseRegistrationObj.registerCourses(userId);
						else
							System.out.println("Already Registered");
						break;
				// Add Course		
				case 2: 
					if(studDAOobj.isRegistered(userId))
						courseRegistrationObj.addCourse(userId);
					else {
						System.out.println("Complete Course Registration first");
					}
					break;
				// Drop Course
				case 3:
					if(studDAOobj.isRegistered(userId))
						courseRegistrationObj.dropCourse(userId);
					else {
						System.out.println("Complete Course Registration first");
					}
					break;
				// View All Courses
				case 4:
					courseRegistrationObj.viewCourses();
						break;
					// View Registered Courses
				case 5:
					if(studDAOobj.isRegistered(userId))
						courseRegistrationObj.viewRegisteredCourse(userId);
					else {
						System.out.println("Complete Course Registration first");
					}
					break;
				// View Grade Card
				case 6:
					if(studDAOobj.isRegistered(userId))
						gradeCardObj.viewGradeCard(userId);
					else {
						System.out.println("Complete Course Registration first");
					}
					break;
				// Make Payment		
				case 7:
					
					if (!studDAOobj.isRegistered(userId)) {
						System.out.println("Complete Course Registration first");
					} else if(!studDAOobj.getPaymentStatus(userId)) {
						System.out.println("Please select payment mode");
						System.out.println("1. Online Mode");
						System.out.println("2. Offline Mode");
						int modeChoice = sc.nextInt();
						sc.nextLine();
						if (modeChoice==1) {
							OnlinePayment onlinePayment = new OnlinePayment();
							onlinePayment.payByCard(userId, 1, 12);
						} else if (modeChoice==2) {
							OfflinePayment offlinePayment = new OfflinePayment();
							offlinePayment.payByCash(userId, 1);
						} else {
							System.out.println("Invalid payment mode selected");
						}	
					} else {
						System.out.println("Fees already paid");
					}
					break;
				// Logout
				case 8: 
						break;
						
				default:
						System.out.println("Choice not available");
			}
		}
	}
}
