/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Scanner;

import com.crs.flipkart.business.AuthorizationOperation;

/**
 * @author Ashruth
 *
 */
public class MainLogin {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Menu");
		System.out.println("Enter 1 for login");
		System.out.println("Enter 2 to exit");
		Scanner sc=new Scanner(System.in);
		int choice=sc.nextInt();
		sc.nextLine();
		switch(choice) {
		case 1:
			System.out.println("Enter UserID");
			int id=sc.nextInt();
			sc.nextLine();
			AuthorizationOperation authObj=new AuthorizationOperation();
			String role = authObj.Authorize(id);
			switch(role) {
			case "Student":
				StudentPage studentPageObj = new StudentPage(); 
				studentPageObj.StudentMenu(id);
				break;
			case "Admin":
				AdminPage adminPageObj=new AdminPage();
				adminPageObj.AdminMenu(id);
				break;
			case "Professor":
				ProfessorPage professorPageObj=new ProfessorPage();
				professorPageObj.ProfessorMenu(id);
				break;
			default:
				System.out.println("Invalid ID");
			}
			break;
		case 2:
			System.out.println("Exiting");
			break;
		default:
			System.out.println("Invalid Choice Exiting");
		}
		
	}

}
