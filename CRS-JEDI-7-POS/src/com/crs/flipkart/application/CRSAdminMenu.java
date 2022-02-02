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
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.AdminOperation;
import com.crs.flipkart.business.CourseRegistrationOperation;
import com.crs.flipkart.business.CourseRegistrationOperationInterface;
import com.crs.flipkart.dao.AdminDaoOperation;
import com.crs.flipkart.dao.AdminDaoOperationInterface;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.CourseDaoOperationInterface;
import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.dao.UserDaoOperationInterface;
import com.crs.flipkart.exception.NoUnallottedCourseException;
import com.crs.flipkart.exception.UserAlreadyExistsException;
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
		UserDaoOperationInterface userDaoOperation = new UserDaoOperation();
		
		Admin admin=adminDaoObj.getAdmin(userId);
		System.out.println("\nWelcome "+admin.getUserName());
		
		LocalTime localTime = LocalTime.now();
		System.out.print("Login Time:- " + localTime);
		
		LocalDate localDate = LocalDate.now();
		System.out.println("\t Login Date:- "+ localDate.getDayOfMonth()+" "+localDate.getMonth()+", "+localDate.getYear());
		
		
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		while(choice!=8) {
			System.out.println("+++++++++++++++++++++++++++++++++++");
			System.out.printf("%20s\n", "Admin Menu");
			System.out.println("++++++++++++++++++++++++++++++++++++");
			System.out.println("1. View all courses");
			System.out.println("2. Add Course to catalog");
			System.out.println("3. Drop Course from catalog");
			System.out.println("4. Approve Student");
			System.out.println("5. Add Professor");
			System.out.println("6. Assign Course To Professor");
			System.out.println("7. View Grade Card");
			System.out.println("8. Logout");
			System.out.println("++++++++++++++++++++++++++++++++++++");
			System.out.println("Enter your choice: ");
			
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
				ArrayList<Integer> unapprovedStudents=userDaoOperation.getUnapprovedStudents();
				System.out.println("List of Unapproved Students");
				unapprovedStudents.forEach(System.out::println);
				
				//for(int i:unapprovedStudents) {
					//logger.info(i);
				//}
				
				System.out.println("Enter User ID to approve: ");
				int userIdToApprove = sc.nextInt();
				adminOperation.approveUser(userIdToApprove);
				break;
			// Add Professor case
			case 5:
				System.out.println("Enter User ID: ");
				int professorUserId = sc.nextInt();

				Professor professor = new Professor();
				professor.setUserId(professorUserId);
				
				System.out.println("Enter Username: ");
				String userName = sc.next();
				professor.setUserName(userName);
				System.out.println("Enter Password: ");
				String password = sc.next();
				professor.setPassword(password);
				professor.setRole("Professor");
				System.out.println("Enter Email: ");
				String email = sc.next();
				professor.setEmail(email);
				professor.setIsApproved(true);
				System.out.println("Enter Address: ");
				String address = sc.next();
				professor.setAddress(address);
				System.out.println("Enter Age: ");
				int age = sc.nextInt();
				professor.setAge(age);
				System.out.println("Enter Gender: ");
				String gender = sc.next();
				professor.setGender(gender);
				System.out.println("Enter Contact: ");
				String contact = sc.next();
				professor.setContact(contact);
				System.out.println("Enter Department: ");
				String dep = sc.next();
				professor.setDepartment(dep);
				adminOperation.addProfessor(professor);
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
				 System.out.println("---------------------------------------------------------------------------------------------------");
				 System.out.printf("|%-20s | %-20s | %-20s | %-20s|\n","Course Id","Course Name","Course Credits","Course Prerequisites");
				 System.out.println("---------------------------------------------------------------------------------------------------");
				 for(Course course:courseList) {
					   System.out.printf("|%-20d | %-20d | %-20s|  %-20d|\n",course.getCourseID(),course.getCourseName(),course.getCredits(),course.getPrerequisites());
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
