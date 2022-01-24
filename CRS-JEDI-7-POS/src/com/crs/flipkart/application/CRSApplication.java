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
import com.crs.flipkart.constants.AuthorizationDB;
import com.crs.flipkart.constants.CourseCatalogDB;
import com.crs.flipkart.constants.CoursesRegisteredDB;

/**
 * @author Ashruth
 *
 */
public class CRSApplication {

	protected AuthorizationDB authObj = new AuthorizationDB();
	protected CourseCatalogDB courseCatalogDB = new CourseCatalogDB();
	protected CoursesRegisteredDB coursesRegisteredDB = new CoursesRegisteredDB();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int choice = 0;
		while(choice!=4) {
		System.out.println("Menu");
		System.out.println("Enter 1 for register student");
		System.out.println("Enter 2 for login");
		System.out.println("Enter 3 for update password");
		System.out.println("Enter 4 to exit");
		Scanner sc=new Scanner(System.in);
		choice=sc.nextInt();
		sc.nextLine();
		//AuthorizationOperation authOperation=new AuthorizationOperation();
		AuthorizationOperationInterface authOperation= new AuthorizationOperation();
		StudentOperationInterface studentOperation = new StudentOperation();
	
		int registeredStudentCount = 1;
		switch(choice) {
		case 1:
			System.out.println("Enter UserID");
			int userId = sc.nextInt();
			System.out.println("Enter Password");
			String password = sc.next();
			System.out.println("Enter user name");
			String userName = sc.next();
			System.out.println("Enter address");
			String address = sc.next();
			System.out.println("Enter age");
			int age = sc.nextInt();
			System.out.println("Enter branch");
			String branch = sc.next();
			System.out.println("Enter contact number");
			String contact = sc.next();
			System.out.println("Enter email");
			String email = sc.next();
			System.out.println("Enter gender");
			String gender = sc.next();
			registeredStudentCount++;
			studentOperation.registerStudent(userId, password, userName, address, age, branch, contact, email, gender, registeredStudentCount);
			break;
		case 2:
			studentOperation.showstudent();
			System.out.println("Enter UserID");
			int id=sc.nextInt();
			sc.nextLine();
			String role = authOperation.Authorize(id);
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
				System.out.println("Invalid ID");
			}
			break;
		case 3:
			System.out.println("Enter UserID");
			int nUserId = sc.nextInt();
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
			System.out.println("Invalid Choice Exiting");
		}
		
		}
	}

}
