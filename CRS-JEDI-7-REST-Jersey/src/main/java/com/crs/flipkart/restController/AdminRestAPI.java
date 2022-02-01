/**
 * 
 */
package com.crs.flipkart.restController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Admin;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Customer;
import com.crs.flipkart.bean.Grade;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.AdminOperation;
import com.crs.flipkart.business.AdminOperationInterface;
import com.crs.flipkart.business.CourseRegistrationOperation;
import com.crs.flipkart.business.CourseRegistrationOperationInterface;
import com.crs.flipkart.dao.AdminDaoOperation;
import com.crs.flipkart.dao.AdminDaoOperationInterface;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.CourseDaoOperationInterface;
import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.dao.UserDaoOperationInterface;
import com.crs.flipkart.exception.CourseDoesNotExistException;
import com.crs.flipkart.exception.CourseIDAlreadyExistException;
import com.crs.flipkart.exception.NoUnallottedCourseException;
import com.crs.flipkart.exception.UserAlreadyExistsException;
import com.crs.flipkart.exception.UserDoNotExistException;
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

@Path("/admin")
public class AdminRestAPI  {
	
	/**
	 * /admin/addProfessor
	 * REST-service for adding professor
	 * @param Professor
	 * @return
	 */
	
	@POST
	@Path("/addProfessor")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProfessor(Professor professor){
		Logger logger = Logger.getLogger(AdminRestAPI.class);
		try {
			
		AdminOperationInterface adminOperation = new AdminOperation();
		Professor profObj = new Professor();
		
		int professorUserId = professor.getUserId();
		profObj.setUserId(professorUserId);	
		
		String userName = professor.getUserName();
		profObj.setUserName(userName);
		
		String password = professor.getPassword(); 
		profObj.setPassword(password);
		
		profObj.setRole("Professor");
		
		String email = professor.getEmail();
		profObj.setEmail(email);
		
		profObj.setIsApproved(true);
		
		String address = professor.getAddress();
		profObj.setAddress(address);
		
		int age = professor.getAge();
		profObj.setAge(age);
		
		String gender = professor.getGender();
		profObj.setGender(gender);
		
		String contact = professor.getContact();
		profObj.setContact(contact);
		
		String dep = professor.getDepartment();
		profObj.setDepartment(dep);
			
		if(adminOperation.addProfessor(profObj)==true)
		{
			logger.info("Professor added successfully");
			return Response.status(201).entity("Professor added successfully").build();
		}
		}catch(UserAlreadyExistsException e) {	
			logger.debug(e.getMessage());
		return Response.status(400).entity(e.getMessage()).build();
	}
	}
	
	
	/**
	 * /admin/viewGradeCard
	 * REST-service for viewing grade card
	 * @param studId
	 * @return
	 */
	
	@GET
	@Path("/viewGradeCard")
	@Produces(MediaType.APPLICATION_JSON)
	
	
	
	public GradeCard viewGradeCard(
			@NotNull
			@QueryParam("studId") int studId) {
		AdminOperationInterface adminOperation = new AdminOperation();
		
		try {
		
		GradeCard gradeCard=adminOperation.viewGradeCard(studId);
		

		return gradeCard;
		}catch(UserDoesNotExistException e) {
			logger.debug(e.getMessage());
			return null;
		}
	}
	
	
	
	/**
	 * /admin/assignProfessorCourse
	 * REST-service for assigning course to professor
	 * @param courseId
	 * @param profId
	 * @return
	 */
	
	@POST
	@Path("/assignProfessorCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response assignProfessorCourse(
			@NotNull
			@QueryParam("profId") int profId,
			@NotNull
			@QueryParam("courseId") int ch
			) {
		
		try {
			ArrayList<Course> courseList=new ArrayList<Course>();
			
			CourseDaoOperationInterface courseDAOobj=new CourseDaoOperation();
			courseList=courseDAOobj.getUnregisteredCourses();
			 
		
			 if (courseList.size()==0) {
				 throw new NoUnallottedCourseException();
			 }
			
			 
			 ArrayList<Integer> courseIdList=new ArrayList<Integer>();
			 for(Course course:courseList)
					courseIdList.add(course.getCourseID());
			 
			
			AdminOperationInterface adminOperation = new AdminOperation();
			adminOperation.assignCourseToProfessor(profId,courseIdList,ch);
			}catch(UserDoesNotExistException e){
				logger.debug(e.getMessage());
				return Response.status(400).entity(e.getMessage()).build();
			}catch(NoUnallottedCourseException | CourseDoesNotExistException e){
				logger.debug(e.getMessage());
				return Response.status(201).entity(e.getMessage()).build();
			}
		logger.info("Professor successfully assigned to course");
		return Response.status(201).entity("Professor successfully assigned to course").build();
	}
	

	
	/**
	 * /admin/viewUnapprovedUsers
	 * REST-service for viewing unaproved users
	 * @return
	 */
	
	@GET
	@Path("/viewUnapprovedUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Integer> viewUnapprovedUsers() {
		UserDaoOperationInterface userDaoOperation = new UserDaoOperation();
		ArrayList<Integer> unapprovedStudents=userDaoOperation.getUnapprovedStudents();
		if(unapprovedStudents.size()==0) {
			logger.info("All users Approved");
			return null;
		}
		return unapprovedStudents;
		
		
	}
	
	

	/**
	 * /admin/ApproveUser
	 * REST-service for approve user
	 * @param userIdToApprove
	 * @return
	 */

	@POST
	@Path("/ApproveUser")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response ApproveUser(
			@NotNull
			@QueryParam("userIdToApprove") int userIdToApprove) {
		try {
		UserDaoOperationInterface userDaoOperation = new UserDaoOperation();
		ArrayList<Integer> unapprovedStudents=userDaoOperation.getUnapprovedStudents();
		if(unapprovedStudents.size()==0) {
			logger.info("All users Approved");
			
			return Response.status(201).entity("All users Approved").build();
		}
		
		
		AdminOperationInterface adminOperation = new AdminOperation();
		boolean flag=adminOperation.approveUser(userIdToApprove);
		if (flag) {
			
			String msg = "User -" + Integer.toString(userIdToApprove) +" approved successfully";
			logger.info(msg);
			return Response.status(201).entity(msg).build();
		}
	
		}catch(UserDoNotExistException e) {
			logger.debug(e.getMessage());
			return Response.status(400).entity(e.getMessage()).build();
		}
		return null;
	}
	
	
	/**
	 * /admin/dropCourse
	 * REST-service for drop course
	 * @param courseId
	 * @return
	 */
	
	@POST
	@Path("/dropCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response dropCourse(
			@NotNull
			@QueryParam("courseID") int courseID) {
		try {
		
		AdminOperationInterface adminOperation = new AdminOperation();
		boolean flag=adminOperation.dropCourse(courseID);
		if (flag) {

			String msg = "Course -" + Integer.toString(courseID) +" dropped successfully";
			logger.info(msg);
			return Response.status(201).entity(msg).build();	
		}
			
		}catch(CourseDoesNotExistException e) {
			logger.debug(e.getMessage());
			return Response.status(400).entity(e.getMessage()).build();
		}
		return null;
	}
	
	
	/**
	 * /admin/addCourse
	 * REST-service for add course
	 * @param courseId
	 * @param courseName
	  * @param credits
	 * @return
	 */
	
	@POST
	@Path("/addCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response addCourse(
			@NotNull
			@QueryParam("courseID") int courseID,
			@NotNull
			@QueryParam("courseName") String courseName,
			@NotNull
			@QueryParam("credits") int credits
			) {
		try {
		
			
		AdminOperationInterface adminOperation = new AdminOperation();
		boolean flag=adminOperation.addCourse(courseID,courseName,credits);
		if(flag) {

			String msg = "Course -" + Integer.toString(courseID) +" added successfully";
			logger.info(msg);
			return Response.status(201).entity(msg).build();	
		}
			
		}
		catch(CourseIDAlreadyExistException e){
			logger.debug(e.getMessage());
			return Response.status(400).entity(e.getMessage()).build();
		}
		return null;
	}
	
	/**
	 * /admin/viewAllCourses
	 * REST-service for viewing all courses
	 * @return
	 */
	
	@GET
	@Path("/viewAllCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Course> viewAllCourses() {
		CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
		ArrayList<Course> catalog = courseRegistrationObj.viewCourses();
		
		return catalog;
	}
}
