
/**
 * 
 */
package com.crs.flipkart.restController;

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

	
	/**
	 *  Handles api request to register student
	 * @param Student
	 * @return
	 */
	
	@POST
	@Path("/registerStudent")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerStudent(Student student) {
		try{

		StudentOperationInterface studentOperation = new StudentOperation();
		Student studentObj = new Student();
		
	
		int userId = student.getUserId();
		studentObj.setUserId(userId);
		
		
		String userName = student.getUserName();	
		studentObj.setUserName(userName);
		
		
		String newStudentPassword = student.getPassword();
		studentObj.setPassword(newStudentPassword);
		
		studentObj.setRole("Student");
		
		
		String email = student.getEmail();
		studentObj.setEmail(email);
		
		studentObj.setIsApproved(false);
		
		
		String address = student.getAddress();
		studentObj.setAddress(address);
		
		
		int age = student.getAge();
		studentObj.setAge(age);
		
		
		String gender = student.getGender();
		studentObj.setGender(gender);
		
		
		String contact = student.getContact();
		studentObj.setContact(contact);
		
		studentObj.setRegistered(false);
		
		
		String branch = student.getBranch();
		studentObj.setBranch(branch);
		
		studentObj.setPaymentStatus(false);
		
		boolean flag=studentOperation.registerStudent(studentObj);
		if(flag) {
			
			String msg = "student - "+ Integer.toString(userId) +" registered successfully & your approval is pending by admin";
			logger.info(msg);
			return Response.status(200).entity(msg).build();
		}
		}catch(UserAlreadyExistsException e) {
			logger.debug(e.getMessage());
			return Response.status(400).entity(e.getMessage()).build();
		}
		return null;
	}
	
	
	/**
	 *  Handles api request to login user
	 * @param id
	 * @param password
	 * @return
	 */
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(
			@NotNull
			@QueryParam ("id") int id,
			@NotNull
			@QueryParam ("password") String password
			) {
		AuthorizationOperationInterface authOperation= new AuthorizationOperation();
		
		
		String role = authOperation.Authorize(id,password);
		
	return role;	
	}
	
	/**
	 *  Handles api request to reset password
	 * @param userId
	 * @param nPassword
	 * @param cNPassword
	 * @return
	 */
	
	@POST
	@Path("/resetPassword")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response resetPassword(
			@NotNull
			@QueryParam ("userId") int userId,
			@NotNull
			@QueryParam ("nPassword") String nPassword,
			@NotNull
			@QueryParam ("cNPassword") String cNPassword
			) {
		try{
		AuthorizationOperationInterface authOperation= new AuthorizationOperation();
		
		
		boolean flag=authOperation.updatePasswordCheck(userId, nPassword, cNPassword);
		if(flag)
			logger.info("Password updated successfully");
			return Response.status(200).entity("Password updated successfully").build();
		else
			logger.error("Error");
			return Response.status(400).entity("Error").build();
			
		}catch(UserDoesNotExistException | PasswordNotMatchingException e ) {
			logger.debug(e.getMessage());
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
}
