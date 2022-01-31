
/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Scanner;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.AuthorizationOperation;
import com.crs.flipkart.business.AuthorizationOperationInterface;
import com.crs.flipkart.business.StudentOperation;
import com.crs.flipkart.business.StudentOperationInterface;
import com.crs.flipkart.exception.PasswordNotMatchingException;
import com.crs.flipkart.exception.UserAlreadyExistsException;
import com.crs.flipkart.exception.UserDoesNotExistException;

/**
 * The Main Entry Point of our application and shows choices for Registration or
 * Login
 * 
 * @author Ashruth
 */


 /**
	 * The Main method for MainCRSApplication
	 * 
**/
public class CRSApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int choice = 0;
		while(choice!=4) {
			// Main menu
		System.out.println("Menu");
		System.out.println("Enter 1 for register student");
		System.out.println("Enter 2 for login");
		System.out.println("Enter 3 to update password");
		System.out.println("Enter 4 to exit");
		Scanner sc=new Scanner(System.in);
		choice=sc.nextInt();
		sc.nextLine();
		
		switch(choice) {
		// New Student Register Case
		case 1:
			registerStudent();
			
			break;
		// Login Case
		case 2:
			login();
			break;
		case 3:
			resetPassword();
			break;
		// Logout Case
		case 4:
			System.out.println("Exiting");
			break;
		default:
			System.out.println("Invalid Choice");
		}
		
		}
	}

	private static void registerStudent() {
		try{
		Scanner sc=new Scanner(System.in);
		StudentOperationInterface studentOperation = new StudentOperation();
		
		System.out.println("Enter User ID: ");
		int userId = sc.nextInt();

		Student student = new Student();
		student.setUserId(userId);
		
		
		System.out.println("Enter Username: ");
		String userName = sc.next();
		student.setUserName(userName);
		System.out.println("Enter Password: ");
		String newStudentPassword = sc.next();
		student.setPassword(newStudentPassword);
		student.setRole("Student");
		System.out.println("Enter Email: ");
		String email = sc.next();
		student.setEmail(email);
		student.setIsApproved(false);
		System.out.println("Enter Address: ");
		String address = sc.next();
		student.setAddress(address);
		System.out.println("Enter Age: ");
		int age = sc.nextInt();
		student.setAge(age);
		System.out.println("Enter Gender: ");
		String gender = sc.next();
		student.setGender(gender);
		System.out.println("Enter Contact: ");
		String contact = sc.next();
		student.setContact(contact);
		student.setRegistered(false);
		System.out.println("Enter Branch: ");
		String branch = sc.next();
		student.setBranch(branch);
		student.setPaymentStatus(false);
		boolean flag=studentOperation.registerStudent(student);
		if(flag)
			System.out.println("student - "+student.getUserId()+" registered successfully & your approval is pending by admin");
		}catch(UserAlreadyExistsException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void login() {
		AuthorizationOperationInterface authOperation= new AuthorizationOperation();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter UserID");
		int id=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Password");
		String password = sc.nextLine();
		String role = authOperation.Authorize(id,password);
		switch(role) {
		// Student Login
		case "Student":
			CRSStudentMenu studentPageObj = new CRSStudentMenu(); 
			studentPageObj.StudentMenu(id);
			break;
		// Admin Login
		case "Admin":
			CRSAdminMenu adminPageObj=new CRSAdminMenu();
			adminPageObj.AdminMenu(id);
			break;
		// Professor Login
		case "Professor":
			CRSProfessorMenu professorPageObj=new CRSProfessorMenu();
			professorPageObj.ProfessorMenu(id);
			break;
		default:
			System.out.println(role);
		}
		
	}

	private static void resetPassword() {
		try{
		AuthorizationOperationInterface authOperation= new AuthorizationOperation();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter UserID");
		int nUserId = sc.nextInt();
		sc.nextLine();
//		System.out.println("Enter Password");
//		password = sc.nextLine();
//		role=authOperation.Authorize(nUserId,password);
//		if(role.equalsIgnoreCase("Invalid")) {
//			System.out.println("Invalid Current UserId/Password");
//			break;
//		}
		System.out.println("Enter new Password");
		String nPassword = sc.next();
		System.out.println("Confirm new Password");
		String cNPassword = sc.next();
		boolean flag=authOperation.updatePasswordCheck(nUserId, nPassword, cNPassword);
		if(flag)
			System.out.println("Password updated successfully");
		else
			System.out.println("Error");
		}catch(UserDoesNotExistException | PasswordNotMatchingException e ) {
			System.out.println(e.getMessage());
		}
		
	}
}
