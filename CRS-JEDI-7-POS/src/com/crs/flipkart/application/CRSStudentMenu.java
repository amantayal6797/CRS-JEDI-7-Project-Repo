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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


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
		
		Student student=studDAOobj.showStudent(userId);
		System.out.println("***********************************************************************");
		System.out.printf("%40s\n","Welcome "+student.getUserName());
		LocalTime localTime = LocalTime.now();
		System.out.print("Login Time:- " + localTime+"  ");
		
		LocalDate localDate = LocalDate.now();
		System.out.println("Login Date:- "+ localDate.getDayOfMonth()+" "+localDate.getMonth()+", "+localDate.getYear());
		System.out.println("***********************************************************************");
		int choice=0;
		Scanner sc = new Scanner(System.in);
		
		NotificationOperationInterface notificationOperation = new NotificationOperation();
		notificationOperation.Notify(userId);
		
		while (choice!=8) 
		{
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.printf("%38s\n","Student Menu");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
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
				case 1: if(!studDAOobj.isRegistered(userId)) {
					ArrayList<Integer> choices=new ArrayList<Integer>();
					
					System.out.println("Enter 6 choices");
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
						
					courseRegistrationObj.registerCourses(userId,choices);
				}
						else
							System.out.println("Already Registered");
						break;	
				case 2: 
					if(studDAOobj.isRegistered(userId)) {
						System.out.println("Enter id of course to add");
						int courseId=sc.nextInt();
						sc.nextLine();
						courseRegistrationObj.addCourse(userId,courseId);
					}else {
						System.out.println("Complete Course Registration first");
					}
					break;
				case 3:
					if(studDAOobj.isRegistered(userId)) {
						System.out.println("Enter id of course to remove");
						int courseId=sc.nextInt();
						sc.nextLine();
						courseRegistrationObj.dropCourse(userId,courseId);
					}else {
						System.out.println("Complete Course Registration first");
					}
					break;
				case 4:
					courseRegistrationObj.viewCourses();
						break;
				case 5:
					if(studDAOobj.isRegistered(userId))
						courseRegistrationObj.viewRegisteredCourse(userId);
					else {
						System.out.println("Complete Course Registration first");
					}
					break;
				case 6:
					if(studDAOobj.isRegistered(userId))
						gradeCardObj.viewGradeCard(userId);
					else {
						System.out.println("Complete Course Registration first");
					}
					break;	
				case 7:
					
					if (!studDAOobj.isRegistered(userId)) {
						System.out.println("Complete Course Registration first");
					} else if(!studDAOobj.getPaymentStatus(userId)) {
						System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						System.out.printf("%100s\n","Please select payment mode");
						System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						System.out.println("1. Online Mode");
						System.out.println("2. Offline Mode");
						System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
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
				case 8: 
						break;
						
				default:
						System.out.println("Choice not available");
			}
		}
	}
}
