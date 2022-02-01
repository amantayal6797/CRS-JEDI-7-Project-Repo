/**
 * 
 */
package com.crs.flipkart.restController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

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
	
	/*
	AdminDaoOperationInterface adminDaoObj=new AdminDaoOperation();
	Scanner sc = new Scanner(System.in);
	CourseDaoOperationInterface courseDAOobj=new CourseDaoOperation();
	UserDaoOperationInterface userDaoOperation = new UserDaoOperation();
	CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
	//private static Logger logger = Logger.getLogger(CourseRegistrationOperation.class);
	
	*/	
	
	@POST
	@Path("/addProfessor")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProfessor(Professor professor){
		//Logger logger = Logger.getLogger(AdminRestAPI.class);
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
			
		if(adminOperation.addProfessor(profObj)==true);
		return Response.status(201).entity("Professor added successfully").build();
		}catch(UserAlreadyExistsException e) {	
		return Response.status(400).entity(e.getMessage()).build();
	}
	}
	
	@GET
	@Path("/viewGradeCard")
	@Produces(MediaType.APPLICATION_JSON)
	public GradeCard viewGradeCard(@QueryParam("studId") int studId) {
		AdminOperationInterface adminOperation = new AdminOperation();
		
		try {
		//int studId = student.getUserId();
		GradeCard gradeCard=adminOperation.viewGradeCard(studId);
		/*
		logger.info("Grade Card");
		logger.info("User ID:-"+userId);
		for(Grade grade:gradeCard.getListOfGrades()) {
			logger.info("Course ID:-"+grade.getCourseID()+" Grade:-"+grade.getGrade());
			}
		*/

		return gradeCard;
		}catch(UserDoesNotExistException e) {
			//System.out.println(e.getMessage());
			return null;
		}
	}
	
	
	@POST
	@Path("/assignProfessorCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response assignProfessorCourse(
			@QueryParam("profId") int profId,
			@QueryParam("courseId") int ch
			) {
		
		try {
			ArrayList<Course> courseList=new ArrayList<Course>();
			//System.out.println("Enter User ID of professor: ");
			//int profId = professor.getUserId();
			CourseDaoOperationInterface courseDAOobj=new CourseDaoOperation();
			courseList=courseDAOobj.getUnregisteredCourses();
			 
		
			 if (courseList.size()==0) {
				 throw new NoUnallottedCourseException();
			 }
			 /*
			 System.out.println("Available Courses");
			 ArrayList<Integer> courseIdList=new ArrayList<Integer>();
			 for(Course course:courseList) {
					System.out.println("Course Id:- "+course.getCourseID());
					System.out.println("Course Name:- "+course.getCourseName());
					System.out.println("Course Credits:- "+course.getCredits());
					System.out.println("Course Prerequisites:- "+course.getPrerequisites());
					System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					courseIdList.add(course.getCourseID());
				}
			 */
			 
			 ArrayList<Integer> courseIdList=new ArrayList<Integer>();
			 for(Course course:courseList)
					courseIdList.add(course.getCourseID());
			 
			 /*
			 System.out.println("Enter Course ID to register");
			 int ch=sc.nextInt();
			 sc.nextLine();
			 */
			AdminOperationInterface adminOperation = new AdminOperation();
			adminOperation.assignCourseToProfessor(profId,courseIdList,ch);
			}catch(UserDoesNotExistException e){
				//System.out.println(e.getMessage());
				return Response.status(400).entity(e.getMessage()).build();
			}catch(NoUnallottedCourseException | CourseDoesNotExistException e){
				return Response.status(201).entity(e.getMessage()).build();
			}
		return Response.status(201).entity("Professor successfully assigned to course").build();
	}
	

	@GET
	@Path("/viewUnapprovedUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Integer> viewUnapprovedUsers() {
		UserDaoOperationInterface userDaoOperation = new UserDaoOperation();
		ArrayList<Integer> unapprovedStudents=userDaoOperation.getUnapprovedStudents();
		if(unapprovedStudents.size()==0) {
			//System.out.println("All users Approved");
			return null;
		}
		return unapprovedStudents;
		/*
		System.out.println("List of Unapproved Students");
		unapprovedStudents.forEach(System.out::println);
		*/
		
	}
	

	@POST
	@Path("/ApproveUser")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ApproveUser(
			@QueryParam("userIdToApprove") int userIdToApprove) {
		try {
		UserDaoOperationInterface userDaoOperation = new UserDaoOperation();
		ArrayList<Integer> unapprovedStudents=userDaoOperation.getUnapprovedStudents();
		if(unapprovedStudents.size()==0) {
			//System.out.println("All users Approved");
			//return;
			return Response.status(201).entity("All users Approved").build();
		}
		
		/*System.out.println("Enter User ID to approve: ");
		int userIdToApprove = sc.nextInt();*/
		AdminOperationInterface adminOperation = new AdminOperation();
		boolean flag=adminOperation.approveUser(userIdToApprove);
		if (flag) {
			//System.out.println("User - "+userId+" approved successfully");	
			String msg = "User -" + Integer.toString(userIdToApprove) +" approved successfully";
			return Response.status(201).entity(msg).build();
		}
	
		}catch(UserDoNotExistException e) {
			/*System.out.println(e.getMessage());
			return;*/
			return Response.status(400).entity(e.getMessage()).build();
		}
		return null;
	}
	
	
	@POST
	@Path("/dropCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dropCourse(
			@QueryParam("courseID") int courseID) {
		try {
		/*System.out.println("Enter Course ID to drop: ");
		int courseID = sc.nextInt();
		sc.nextLine();*/
		AdminOperationInterface adminOperation = new AdminOperation();
		boolean flag=adminOperation.dropCourse(courseID);
		if (flag) {
		//System.out.println("Course - "+courseID+" dropped successfully");
			String msg = "Course -" + Integer.toString(courseID) +" dropped successfully";
			return Response.status(201).entity(msg).build();	
		}
			
		}catch(CourseDoesNotExistException e) {
			//System.out.println(e.getMessage());
			return Response.status(400).entity(e.getMessage()).build();
		}
		return null;
	}
	
	
	@POST
	@Path("/addCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(
			@QueryParam("courseID") int courseID,
			@QueryParam("courseName") String courseName,
			@QueryParam("credits") int credits
			) {
		try {
		/*
		System.out.println("Enter Course ID to add: ");
		int courseID = sc.nextInt();
		System.out.println("Enter Course Name to add: ");
		String courseName = sc.next();
		System.out.println("Enter Course Credits: ");
		int credits = sc.nextInt();
		*/
			
		AdminOperationInterface adminOperation = new AdminOperation();
		boolean flag=adminOperation.addCourse(courseID,courseName,credits);
		if(flag) {
			//System.out.println("Course - "+courseID+" added successfully");
			String msg = "Course -" + Integer.toString(courseID) +" added successfully";
			return Response.status(201).entity(msg).build();	
		}
			
		}
		catch(CourseIDAlreadyExistException e){
			//System.out.println(e.getMessage());
			//return;
			return Response.status(400).entity(e.getMessage()).build();
		}
		return null;
	}
	
	@GET
	@Path("/viewAllCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Course> viewAllCourses() {
		CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
		ArrayList<Course> catalog = courseRegistrationObj.viewCourses();
		/*for(Course course:catalog) {
			logger.info("Course Id:- "+course.getCourseID());
			logger.info("Course Name:- "+course.getCourseName());
			logger.info("Course Credits:- "+course.getCredits());
			logger.info("Course Prerequisites:- "+course.getPrerequisites());
			logger.info("Course Professor Id:- "+course.getProfessorAllotted());
			logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}*/
		return catalog;
	}
}
