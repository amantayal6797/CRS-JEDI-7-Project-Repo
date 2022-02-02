/**
 * 
 */
package com.crs.flipkart.restController;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
//import java.util.logging.Logger;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

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
	

	private static final Logger logger = Logger.getLogger(ProfessorRestAPI.class);

	
	@GET
	@Path("/viewEnrolledStudents")
	@Produces(MediaType.APPLICATION_JSON)
	
	public ArrayList<Integer> viewEnrolledStudents(
			@NotNull
			@QueryParam("courseId") int courseId
			
			) {
		
		CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
		
		
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
			logger.info("No UnregisteredCourses");
			 return null;
		 }
		 
		
		 return courseList;
	}
	
	
	
	// Wrong userId & right course id case has to be handled.
	@POST
	@Path("/registerCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)	
	
	public Response registerCourse(
			@NotNull
			@QueryParam("courseId") int choice,
			@NotNull
			@QueryParam("userId") int userId
			) {
		try {
			CourseDaoOperationInterface courseDAOobj = new CourseDaoOperation();
			CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
			UserDaoOperationInterface userDao= new UserDaoOperation();
			if(userDao.getUser(userId)==null) {
				logger.error("User with ID "+userId+" does not exist");
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
			 
			 boolean flag=courseRegistrationObj.registerProfessorCourse(userId,courseIdList,choice);
			 if(flag) {
				
				 String msg = "Course - " + Integer.toString(choice) + " succesfully allotted to Professor - "+ Integer.toString(userId);
				 logger.info(msg);
				 return Response.status(201).entity(msg).build();
			 }
			 
				 
		}
		catch(NoUnallottedCourseException | CourseDoesNotExistException e) {
		
			logger.debug(e.getMessage());
			return Response.status(400).entity(e.getMessage()).build();
		}
		return null;
	}
	

	@GET
	@Path("/viewProfessorCourse")
	@Produces(MediaType.APPLICATION_JSON)
	
	public ArrayList<Course> viewProfessorCourse(
			@NotNull
			@QueryParam("userId") int userId) {
	
		CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
		ArrayList<Course> courseList =courseRegistrationObj.viewProfessorCourses(userId);
		
		return courseList;
	}
	

	@POST
	@Path("/assignGrade")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)	
	
	public Response assignGrade(
			@NotNull
			@QueryParam("courseId") int courseId,
			@NotNull
			@QueryParam("studentId") int studentId,
			@NotNull
			@QueryParam("userId") int userId,
			@NotNull
			@QueryParam("grade") String grade
			) {
		try {
		
		GradeCardOperationInterface gradeCardObj= new GradeCardOperation();
		boolean flag=gradeCardObj.assignGrade(userId,courseId,studentId,grade);
		if(flag) {
			logger.info("Grade Assigned Succesfully");	
			return Response.status(201).entity("Grade Assigned Succesfully").build();	
		}
		}catch(StudentNotRegisteredForCourseException | CourseNotTaughtByProfessorException e) {
			logger.debug(e.getMessage());
			return Response.status(400).entity(e.getMessage()).build();	
		}
		return null;
	}

}
