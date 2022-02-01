/**
 * 
 */
package com.crs.flipkart.restController;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.CourseRegistrationOperation;
import com.crs.flipkart.business.CourseRegistrationOperationInterface;
import com.crs.flipkart.business.GradeCardOperation;
import com.crs.flipkart.business.GradeCardOperationInterface;
import com.crs.flipkart.business.OfflinePayment;
import com.crs.flipkart.business.OnlinePayment;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.CourseDaoOperationInterface;
import com.crs.flipkart.dao.ProfessorDaoOperation;
import com.crs.flipkart.dao.ProfessorDaoOperationInterface;
import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.dao.UserDaoOperationInterface;
import com.crs.flipkart.exception.CourseDoesNotExistException;
import com.crs.flipkart.exception.CourseNotTaughtByProfessorException;
import com.crs.flipkart.exception.NoUnallottedCourseException;
import com.crs.flipkart.exception.StudentNotRegisteredForCourseException;

/**
 * 
 * User Interactive menu if user logs in is a professor
 * 
 * @author Ashruth
 *
 */


/*
Manages the major roles of professor based on user inputs
*/
@Path("/professor")
public class ProfessorRestAPI {
	
	/*
	ProfessorDaoOperationInterface profDaoObj=new ProfessorDaoOperation(); 
	CourseDaoOperationInterface courseDAOobj = new CourseDaoOperation();
	CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
	GradeCardOperationInterface gradeCardObj= new GradeCardOperation();
	*/
	//int userId;
	//Scanner sc = new Scanner(System.in);
	//private static Logger logger = Logger.getLogger(CourseRegistrationOperation.class);

	
	@GET
	@Path("/viewEnrolledStudents")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Integer> viewEnrolledStudents(
			@QueryParam("courseId") int courseId
			
			) {
		
		CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
		
		/*
		ArrayList<Course> courseList=courseRegistrationObj.viewProfessorCourses(userId);
		for(Course course:courseList) {
			ArrayList<Integer> enrolledStudents=courseRegistrationObj.viewEnrolledStudents(course.getCourseID());
			logger.info("Course ID:- "+ course.getCourseID()+"\tCourse Name:- "+course.getCourseName());
			logger.info("Student ID");
			enrolledStudents.forEach(System.out::println);
		}	 
		*/
		ArrayList<Integer> enrolledStudents=courseRegistrationObj.viewEnrolledStudents(courseId);
		return enrolledStudents;
		
	}
	

	@GET
	@Path("/viewUnregisteredCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Course> viewUnregisteredCourses() throws NoUnallottedCourseException {
		
		CourseDaoOperationInterface courseDAOobj = new CourseDaoOperation(); 
		ArrayList<Course> courseList=new ArrayList<Course>();
		 courseList=courseDAOobj.getUnregisteredCourses();
		 if (courseList.size()==0) {
			 //throw new NoUnallottedCourseException();
			 return null;
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
		 ArrayList<Integer> courseIdList=new ArrayList<Integer>();
		 for(Course course:courseList) {
				courseIdList.add(course.getCourseID());
			}
		 */
		 return courseList;
	}
	
	
	
	// Wrong userId & right course id case has to be handled.
	@POST
	@Path("/registerCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response registerCourse(
			@QueryParam("courseId") int choice,
			@QueryParam("userId") int userId
			) {
		try {
			CourseDaoOperationInterface courseDAOobj = new CourseDaoOperation();
			CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
			UserDaoOperationInterface userDao= new UserDaoOperation();
//			viewUnregisteredCourses();
			if(userDao.getUser(userId)==null) {
				return Response.status(400).entity("User with ID "+userId+" does not exist").build();
			}
			ArrayList<Course> courseList=new ArrayList<Course>();
			 courseList=courseDAOobj.getUnregisteredCourses();
			 
			 if (courseList.size()==0) {
				 throw new  NoUnallottedCourseException();
			 }
			 ArrayList<Integer> courseIdList=new ArrayList<Integer>();
			 for(Course course:courseList) {
					courseIdList.add(course.getCourseID());
				}
			 //System.out.println("Enter Course ID to register");
			 //int choice=sc.nextInt();
			 //sc.nextLine();
			 boolean flag=courseRegistrationObj.registerProfessorCourse(userId,courseIdList,choice);
			 if(flag) {
				 //System.out.println("Course - "+choice+" succesfully allotted to Professor - "+userId);
				 String msg = "Course - " + Integer.toString(choice) + " succesfully allotted to Professor - "+ Integer.toString(userId);
				 return Response.status(201).entity(msg).build();
			 }
			 
				 
		}
		catch(NoUnallottedCourseException | CourseDoesNotExistException e) {
		
			//System.out.println(e.getMessage());
			return Response.status(400).entity(e.getMessage()).build();
		}
		return null;
	}
	

	@GET
	@Path("/viewProfessorCourse")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Course> viewProfessorCourse(
			@QueryParam("userId") int userId) {
	
		CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
		ArrayList<Course> courseList =courseRegistrationObj.viewProfessorCourses(userId);
		/*
		for(Course course:courseList) {
			logger.info("Course Id:- "+course.getCourseID());
			logger.info("Course Name:- "+course.getCourseName());
			logger.info("Course Credits:- "+course.getCredits());
			logger.info("Course Prerequisites:- "+course.getPrerequisites());
			logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
		*/
		return courseList;
	}
	

	@POST
	@Path("/assignGrade")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response assignGrade(
			@QueryParam("courseId") int courseId,
			@QueryParam("studentId") int studentId,
			@QueryParam("userId") int userId,
			@QueryParam("grade") String grade
			) {
		try {
		/*
		System.out.println("Enter CourseID");
		int courseId=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Student ID");
		int studentId=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Grade");
		String grade=sc.nextLine();
		*/
		
		GradeCardOperationInterface gradeCardObj= new GradeCardOperation();
		boolean flag=gradeCardObj.assignGrade(userId,courseId,studentId,grade);
		if(flag) {
			//System.out.println("Grade Assigned Succesfully");	
			return Response.status(201).entity("Grade Assigned Succesfully").build();	
		}
		}catch(StudentNotRegisteredForCourseException | CourseNotTaughtByProfessorException e) {
			//System.out.println(e.getMessage());
			return Response.status(400).entity(e.getMessage()).build();	
		}
		return null;
	}

}
