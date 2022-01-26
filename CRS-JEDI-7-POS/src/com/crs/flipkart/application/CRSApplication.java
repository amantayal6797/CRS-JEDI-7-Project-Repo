
/**
 * 
 */
package com.crs.flipkart.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.AuthorizationOperation;
import com.crs.flipkart.business.AuthorizationOperationInterface;
import com.crs.flipkart.business.StudentOperation;
import com.crs.flipkart.business.StudentOperationInterface;
import com.crs.flipkart.constants.AuthorizationDB;
import com.crs.flipkart.constants.CourseCatalogDB;
import com.crs.flipkart.constants.CoursesRegisteredDB;
import com.crs.flipkart.constants.DatabaseInitializer;
import com.crs.flipkart.utils.ConnectionSetup;

/**
 * @author Ashruth
 *
 */
public class CRSApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatabaseInitializer databaseInitializerObj = new DatabaseInitializer();
		databaseInitializerObj.initializeCourseCatalog();
		databaseInitializerObj.initializeUsers();
		int choice = 0;
		while(choice!=4) {
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
		case 1:
			System.out.println("Enter UserID");
			int userId = sc.nextInt();
			sc.nextLine();
			System.out.println("Enter Password");
			String password = sc.nextLine();
			System.out.println("Enter user name");
			String userName = sc.nextLine();
			System.out.println("Enter address");
			String address = sc.nextLine();
			System.out.println("Enter age");
			int age = sc.nextInt();
			sc.nextLine();
			System.out.println("Enter branch");
			String branch = sc.nextLine();
			System.out.println("Enter contact number");
			String contact = sc.nextLine();
			System.out.println("Enter email");
			String email = sc.nextLine();
			System.out.println("Enter gender");
			String gender = sc.nextLine();
			studentOperation.registerStudent(userId, password, userName, address, age, branch, contact, email, gender);
			break;
		case 2:
			System.out.println("Enter UserID");
			int id=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter Password");
			password = sc.nextLine();
			String role = authOperation.Authorize(id,password);
			switch(role) {
			case "Student":
				CRSStudentMenu studentPageObj = new CRSStudentMenu(); 
				studentPageObj.StudentMenu(id);
				break;
			case "Admin":
				CRSAdminMenu adminPageObj=new CRSAdminMenu();
				adminPageObj.AdminMenu(id);
				break;
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
			System.out.println("Enter Password");
			password = sc.nextLine();
			role=authOperation.Authorize(nUserId,password);
			if(role.equalsIgnoreCase("Invalid")) {
				System.out.println("Invalid Current UserId/Password");
				break;
			}
			System.out.println("Enter new Password");
			String nPassword = sc.next();
			System.out.println("Confirm new Password");
			String cNPassword = sc.next();
			authOperation.updatePasswordCheck(nUserId, nPassword, cNPassword);
			break;
		case 4:
			System.out.println("Exiting");
			break;
		default:
			System.out.println("Invalid Choice");
		}
		
		}
	}
}
