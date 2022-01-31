/**
 * 
 */
package com.crs.flipkart.application;
import java.util.*;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Grade;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.RegisteredCourse;
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
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.CourseDaoOperationInterface;
import com.crs.flipkart.dao.StudentDaoOperation;
import com.crs.flipkart.exception.CourseAlreadyRegisteredException;
import com.crs.flipkart.exception.CourseDoesNotExistException;
import com.crs.flipkart.exception.CourseNotRegisteredToDropException;
import com.crs.flipkart.exception.NoCourseToDropException;
import com.crs.flipkart.exception.RegistrationCompletedException;
import com.crs.flipkart.exception.UserDoesNotExistException;

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
	CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
	GradeCardOperationInterface gradeCardObj=new GradeCardOperation();
	private static Logger logger = Logger.getLogger(CourseRegistrationOperation.class);
	CourseDaoOperationInterface courseDAOobj=new CourseDaoOperation();
	Scanner sc = new Scanner(System.in);
	int userId;
	public void StudentMenu(int userId) {
		this.userId=userId;
		Student student=studDAOobj.showStudent(userId);
		System.out.println("\nWelcome "+student.getUserName());
		LocalTime localTime = LocalTime.now();
		System.out.println("Login Time:- " + localTime);
		
		LocalDate localDate = LocalDate.now();
		System.out.println("Login Date:- "+ localDate.getDayOfMonth()+" "+localDate.getMonth()+", "+localDate.getYear());
		int choice=0;
		
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
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
				// Course Registration 
				case 1: courseRegistration();
						break;
				// Add Course		
				case 2:addCourse(); 
					
					break;
				// Drop Course
				case 3: dropCourse();
					break;
				// View All Courses
				case 4:
					viewCourses();	
					break;
					// View Registered Courses
				case 5:
					viewRegisteredCourses();
					break;
				// View Grade Card
				case 6:
					viewGradeCard();
					break;
				// Make Payment		
				case 7:
					makePayment();
					break;
				// Logout
				case 8: 
						break;
						
				default:
						System.out.println("Choice not available");
			}
		}
	}
	public void courseRegistration() {
		if(!studDAOobj.isRegistered(userId)) {
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
				
			boolean flag=courseRegistrationObj.registerCourses(userId,choices);
			if (flag) {
				logger.info("Course Registration Done");
				logger.info("Pay Fees Now.");
				
			}
				
		}
				else
					System.out.println("Already Registered");
	}
	public void addCourse() {
		try{
			if(studDAOobj.isRegistered(userId)) {
		
			System.out.println("Enter id of course to add");
			int courseId=sc.nextInt();
			sc.nextLine();
			boolean flag=courseRegistrationObj.addCourse(userId,courseId);
			if(flag)
				System.out.println("Course Succesfully Added\n");
			
		}else {
			System.out.println("Complete Course Registration first");
		}
		}
		catch(RegistrationCompletedException | CourseAlreadyRegisteredException | CourseDoesNotExistException e) {
			System.out.println(e.getMessage());
		}
	}
	public void dropCourse() {
		try {
		if(studDAOobj.isRegistered(userId)) {
			System.out.println("Enter id of course to remove");
			int courseId=sc.nextInt();
			sc.nextLine();
			boolean flag = courseRegistrationObj.dropCourse(userId,courseId);
			if (flag) {
				logger.info("Course Successfully Dropped");
			}
		}else {
			System.out.println("Complete Course Registration first");
		}
		}catch(NoCourseToDropException | CourseDoesNotExistException |  CourseNotRegisteredToDropException e) {
			System.out.println(e.getMessage());
		}
		
	}
	public void viewCourses() {
		ArrayList<Course> catalog = courseRegistrationObj.viewCourses();
		for(Course course:catalog) {
			logger.info("Course Id:- "+course.getCourseID());
			logger.info("Course Name:- "+course.getCourseName());
			logger.info("Course Credits:- "+course.getCredits());
			logger.info("Course Prerequisites:- "+course.getPrerequisites());
			logger.info("Course Professor Id:- "+course.getProfessorAllotted());
			logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
		
	}
	public void viewRegisteredCourses() {
		if(studDAOobj.isRegistered(userId)) {
			
			logger.info("Registered Courses");
			ArrayList<RegisteredCourse> listOfRegisteredCourses=courseRegistrationObj.viewRegisteredCourse(userId);
			for(RegisteredCourse regCourse:listOfRegisteredCourses) {
				Course course=courseDAOobj.getCourse(regCourse.getCourseID());
				logger.info("Course Id:-"+course.getCourseID()+"\tCourse Name:-"+course.getCourseName());
				}
		}
		else {
			System.out.println("Complete Course Registration first");
		}
		
	}
	public void viewGradeCard() {
		try {
		if(studDAOobj.isRegistered(userId)) {
			GradeCard gradeCard=gradeCardObj.viewGradeCard(userId);
			logger.info("Grade Card");
			logger.info("User ID:-"+userId);
			for(Grade grade:gradeCard.getListOfGrades()) {
				logger.info("Course ID:-"+grade.getCourseID()+" Grade:-"+grade.getGrade());
				}
			}
		else {
			System.out.println("Complete Course Registration first");
		}
		}catch(UserDoesNotExistException e) {
			System.out.println(e.getMessage());
		}
	}
	public void makePayment() {
		if (!studDAOobj.isRegistered(userId)) {
			System.out.println("Complete Course Registration first");
		} else if(!studDAOobj.getPaymentStatus(userId)) {
			System.out.println("Please select payment mode");
			System.out.println("1. Online Mode");
			System.out.println("2. Offline Mode");
			int modeChoice = sc.nextInt();
			sc.nextLine();
			boolean flag=false;
			if (modeChoice==1) {
				OnlinePayment onlinePayment = new OnlinePayment();
				flag = onlinePayment.payByCard(userId, 1, 12);
			} else if (modeChoice==2) {
				OfflinePayment offlinePayment = new OfflinePayment();
				flag = offlinePayment.payByCash(userId, 1);
			} else {
				System.out.println("Invalid payment mode selected");
			}

			if(flag)
				logger.info("Transaction completed\nFees Paid ");
		} else {
			System.out.println("Fees already paid");
		}
		
	}
}
