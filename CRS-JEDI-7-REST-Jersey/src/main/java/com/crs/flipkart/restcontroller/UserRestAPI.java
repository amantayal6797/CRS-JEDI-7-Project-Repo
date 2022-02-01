
/**
 * 
 */
package com.crs.flipkart.restcontroller;

import java.util.Scanner;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
@Path("/user")
public class UserRestAPI {

	
	@POST
	@Path("/registerStudent")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerStudent(Student student) {
		try{
		//Scanner sc=new Scanner(System.in);
		StudentOperationInterface studentOperation = new StudentOperation();
		Student studentObj = new Student();
		
		//System.out.println("Enter User ID: ");
		int userId = student.getUserId();
		studentObj.setUserId(userId);
		
		//System.out.println("Enter Username: ");
		String userName = student.getUserName();	
		studentObj.setUserName(userName);
		
		//System.out.println("Enter Password: ");
		String newStudentPassword = student.getPassword();
		studentObj.setPassword(newStudentPassword);
		
		studentObj.setRole("Student");
		
		//System.out.println("Enter Email: ");
		String email = student.getEmail();
		studentObj.setEmail(email);
		
		studentObj.setIsApproved(false);
		
		//System.out.println("Enter Address: ");
		String address = student.getAddress();
		studentObj.setAddress(address);
		
		//System.out.println("Enter Age: ");
		int age = student.getAge();
		studentObj.setAge(age);
		
		//System.out.println("Enter Gender: ");
		String gender = student.getGender();
		studentObj.setGender(gender);
		
		//System.out.println("Enter Contact: ");
		String contact = student.getContact();
		studentObj.setContact(contact);
		
		studentObj.setRegistered(false);
		
		//System.out.println("Enter Branch: ");
		String branch = student.getBranch();
		studentObj.setBranch(branch);
		
		studentObj.setPaymentStatus(false);
		
		boolean flag=studentOperation.registerStudent(studentObj);
		if(flag) {
			//System.out.println("student - "+student.getUserId()+" registered successfully & your approval is pending by admin");
			String msg = "student - "+ Integer.toString(userId) +" registered successfully & your approval is pending by admin";
			return Response.status(200).entity(msg).build();
		}
		}catch(UserAlreadyExistsException e) {
			return Response.status(400).entity(e.getMessage()).build();
			//System.out.println(e.getMessage());
		}
		return null;
	}
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(
			@QueryParam ("id") int id,
			@QueryParam ("password") String password
			) {
		AuthorizationOperationInterface authOperation= new AuthorizationOperation();
		
		/*Scanner sc=new Scanner(System.in);
		System.out.println("Enter UserID");
		int id=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Password");
		String password = sc.nextLine();
		*/
		
		String role = authOperation.Authorize(id,password);
		/*
		switch(role) {
		// Student Login
		case "Student":
			CRSStudentMenu studentPageObj = new CRSStudentMenu(); 
			studentPageObj.StudentMenu(id);
			break;
		// Admin Login
		case "Admin":
			AdminRestAPI adminPageObj=new AdminRestAPI();
			adminPageObj.AdminMenu(id);
			break;
		// Professor Login
		case "Professor":
			ProfessorRestAPI professorPageObj=new ProfessorRestAPI();
			professorPageObj.ProfessorMenu(id);
			break;
		default:
			System.out.println(role);
		}
		*/
	return role;	
	}
	
	@POST
	@Path("/resetPassword")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response resetPassword(
			@QueryParam ("userId") int userId,
			@QueryParam ("nPassword") String nPassword,
			@QueryParam ("cNPassword") String cNPassword
			) {
		try{
		AuthorizationOperationInterface authOperation= new AuthorizationOperation();
		Scanner sc=new Scanner(System.in);
		/*
		System.out.println("Enter UserID");
		int nUserId = sc.nextInt();
		sc.nextLine();
		*/
//		System.out.println("Enter Password");
//		password = sc.nextLine();
//		role=authOperation.Authorize(nUserId,password);
//		if(role.equalsIgnoreCase("Invalid")) {
//			System.out.println("Invalid Current UserId/Password");
//			break;
//		}
		/*
		System.out.println("Enter new Password");
		String nPassword = sc.next();
		System.out.println("Confirm new Password");
		String cNPassword = sc.next();
		*/
		
		boolean flag=authOperation.updatePasswordCheck(userId, nPassword, cNPassword);
		if(flag)
			//System.out.println("Password updated successfully");
			return Response.status(200).entity("Password updated successfully").build();
		else
			//System.out.println("Error");
			return Response.status(400).entity("Error").build();
			
		}catch(UserDoesNotExistException | PasswordNotMatchingException e ) {
			//System.out.println(e.getMessage());
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
}
