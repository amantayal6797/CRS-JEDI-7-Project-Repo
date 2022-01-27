
/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Scanner;

import com.crs.flipkart.business.AuthorizationOperation;
import com.crs.flipkart.business.AuthorizationOperationInterface;
import com.crs.flipkart.business.StudentOperation;
import com.crs.flipkart.business.StudentOperationInterface;

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
		AuthorizationOperationInterface authOperation= new AuthorizationOperation();
		StudentOperationInterface studentOperation = new StudentOperation();
	
		switch(choice) {
		// New Student Register Case
		case 1:
			studentOperation.registerStudent();
			break;
		// Login Case
		case 2:
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
			break;
		case 3:
			System.out.println("Enter UserID");
			int nUserId = sc.nextInt();
			sc.nextLine();
//			System.out.println("Enter Password");
//			password = sc.nextLine();
//			role=authOperation.Authorize(nUserId,password);
//			if(role.equalsIgnoreCase("Invalid")) {
//				System.out.println("Invalid Current UserId/Password");
//				break;
//			}
			System.out.println("Enter new Password");
			String nPassword = sc.next();
			System.out.println("Confirm new Password");
			String cNPassword = sc.next();
			authOperation.updatePasswordCheck(nUserId, nPassword, cNPassword);
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
}
