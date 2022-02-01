/**
 * 
 */
package com.crs.flipkart.restController;
import java.util.*;
import java.util.logging.Logger;

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
import com.crs.flipkart.bean.Grade;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.CourseRegistrationOperation;
import com.crs.flipkart.business.CourseRegistrationOperationInterface;
import com.crs.flipkart.business.GradeCardOperation;
import com.crs.flipkart.business.GradeCardOperationInterface;
import com.crs.flipkart.business.NotificationOperation;
import com.crs.flipkart.business.NotificationOperationInterface;
import com.crs.flipkart.business.OfflinePayment;
import com.crs.flipkart.business.OnlinePayment;
import com.crs.flipkart.business.PaymentOperation;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.CourseDaoOperationInterface;
import com.crs.flipkart.dao.StudentDaoOperation;
import com.crs.flipkart.exception.CourseAlreadyRegisteredException;
import com.crs.flipkart.exception.CourseDoesNotExistException;
import com.crs.flipkart.exception.CourseNotRegisteredToDropException;
import com.crs.flipkart.exception.NoCourseToDropException;
import com.crs.flipkart.exception.RegistrationCompletedException;
import com.crs.flipkart.exception.UserDoesNotExistException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


/**
 * User Interactive menu for students
 * 
 * @author Nitish
 *
 */
@Path("/student")
public class StudentRestAPI {
	/**
	 * Main Student Client which displays and manages all student related operations
	 * 
	 */
	private static Logger logger = Logger.getLogger(StudentRestAPI.class);
	
	

	/**
	 * Method to handle API request for course registration
	 * @param userId
	 * @param courseId1
	 * @param courseId2
	 * @param courseId3
	 * @param courseId4
	 * @param courseId5
	 * @param courseId6
	 * @return
	 */
	
	@POST
	@Path("/courseRegistration")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response courseRegistration(
			@NotNull
			@QueryParam("userId") int userId,
			@NotNull
			@QueryParam("courseId1") int courseId1,
			@NotNull
			@QueryParam("courseId2") int courseId2,
			@NotNull
			@QueryParam("courseId3") int courseId3,
			@NotNull
			@QueryParam("courseId4") int courseId4,
			@NotNull
			@QueryParam("courseId5") int courseId5,
			@NotNull
			@QueryParam("courseId6") int courseId6
			) {
		
		StudentDaoOperation studDAOobj = new StudentDaoOperation();
		CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
		
		if(!studDAOobj.isRegistered(userId)) {
			ArrayList<Integer> choices=new ArrayList<Integer>();
			
			
			choices.add(courseId1);
			choices.add(courseId2);
			choices.add(courseId3);
			choices.add(courseId4);
			choices.add(courseId5);
			choices.add(courseId6);
			
			boolean flag=courseRegistrationObj.registerCourses(userId,choices);
			if (flag) {
				logger.info("Course Registration Done");
				logger.info("Pay Fees Now.");
				return Response.status(200).entity("Course Registration Done. Pay Fees Now.").build();	
				
			}
				
		}
				else
					logger.info("Already Registered");
					return Response.status(400).entity("Already Registered").build();
		return null;
	}
	
	
	/**
	 * Handles api request to add a course
	 * @param courseId
	 * @param userId
	 * @return
	 */
	
	@POST
	@Path("/addCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(
			@NotNull
			@QueryParam("userId") int userId,
			@NotNull
			@QueryParam("courseId") int courseId
			
			) {
		try{
			StudentDaoOperation studDAOobj = new StudentDaoOperation();
			CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
		
			if(studDAOobj.isRegistered(userId)) {
		
				
			boolean flag=courseRegistrationObj.addCourse(userId,courseId);
			if(flag)
				logger.info("Course Succesfully Added\n");
				return Response.status(400).entity("Course Succesfully Added").build();
			
		}else {
			logger.error("Complete Course Registration first");
			return Response.status(400).entity("Complete Course Registration first").build();
			
		}
		}
		catch(RegistrationCompletedException | CourseAlreadyRegisteredException | CourseDoesNotExistException e) {
			logger.debug(e.getMessage());
			return Response.status(400).entity(e.getMessage()).build();
		}
		return null;
	}
	
	
	/**
	 * Handles api request to drop a course
	 * @param courseId
	 * @param userId
	 * @return
	 */
	
	
	@POST
	@Path("/dropCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dropCourse(
			@NotNull
			@QueryParam("userId") int userId,
			@NotNull
			@QueryParam("courseId") int courseId
			) {
		try {
			StudentDaoOperation studDAOobj = new StudentDaoOperation();
			CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
			
		if(studDAOobj.isRegistered(userId)) {
			
			boolean flag = courseRegistrationObj.dropCourse(userId,courseId);
			if (flag) {
				logger.info("Course Successfully Dropped");
				return Response.status(200).entity("Course Successfully Dropped").build();
			}
		}else {
			logger.error("Complete Course Registration first");
			return Response.status(200).entity("Complete Course Registration first").build();
		}
		}catch(NoCourseToDropException | CourseDoesNotExistException |  CourseNotRegisteredToDropException e) {
			logger.debug(e.getMessage());
			return Response.status(400).entity(e.getMessage()).build();
		}
		return null;
	}
	
	
	/**
	 * Handles api request to view courses
	 * @return
	 */
	
	
	@GET
	@Path("/viewCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Course>  viewCourses() {
		CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
		
		ArrayList<Course> catalog = courseRegistrationObj.viewCourses();
		
		return catalog;
	}
	
	
	/**
	 * Handles api request to view registered courses
	 * @param userId
	 * @return
	 */
	
	
	@GET
	@Path("/viewRegisteredCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<RegisteredCourse> viewRegisteredCourses(
			@NotNull
			@QueryParam("userId") int userId) {
		
		StudentDaoOperation studDAOobj = new StudentDaoOperation();
		CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
		
		if(studDAOobj.isRegistered(userId)) {
			
						ArrayList<RegisteredCourse> listOfRegisteredCourses=courseRegistrationObj.viewRegisteredCourse(userId);
			return listOfRegisteredCourses;
		}
		else {
			logger.error("Complete Course Registration first");
			return null;
		}
		
	}
	
	
	/**
	 * Handles api request to view GradeCard
	 * @param userId
	 * @return
	 */
	
	
	@GET
	@Path("/viewGradeCard")
	@Produces(MediaType.APPLICATION_JSON)
	public GradeCard viewGradeCard(
			@NotNull
			@QueryParam("userId") int userId) {
		
		StudentDaoOperation studDAOobj = new StudentDaoOperation();
		GradeCardOperationInterface gradeCardObj=new GradeCardOperation();
		
		try {
		if(studDAOobj.isRegistered(userId)) {
			GradeCard gradeCard=gradeCardObj.viewGradeCard(userId);
			
			return gradeCard;
			}
		else {
			logger.error("Complete Course Registration first");
			return null;
		}
		}catch(UserDoesNotExistException e) {
			logger.debug(e.getMessage());
			return null;
		}
	}
	
	
	/**
	 * Handles api request make a Payment
	 * @param userId
	 * @param modeChoice
	 * @return
	 * @throws ValidationException
	 */
	
	@POST
	@Path("/makePayment")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response makePayment(
			@NotNull
			@QueryParam("userId") int userId,
			@NotNull
			@QueryParam("modeChoice") int modeChoice
			) {
		
		StudentDaoOperation studDAOobj = new StudentDaoOperation();
		CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
		GradeCardOperationInterface gradeCardObj=new GradeCardOperation();
		CourseDaoOperationInterface courseDAOobj=new CourseDaoOperation();
		
		if (!studDAOobj.isRegistered(userId)) {
			logger.error("Complete Course Registration first");
			return Response.status(400).entity("Complete Course Registration first").build();
		} else if(!studDAOobj.getPaymentStatus(userId)) {
			
			
			boolean flag=false;
			if (modeChoice==1) {
				OnlinePayment onlinePayment = new OnlinePayment();
				flag = onlinePayment.payByCard(userId, 1, 12);
			} else if (modeChoice==2) {
				OfflinePayment offlinePayment = new OfflinePayment();
				flag = offlinePayment.payByCash(userId, 1);
			} else {
				logger.error("Invalid payment mode selected");
				return Response.status(400).entity("Invalid payment mode selected").build();
			}

			if(flag)
				logger.info("Transaction completed\nFees Paid ");
				return Response.status(200).entity("Transaction completed\nFees Paid").build();
		} else {
			logger.error("Fees already paid");
			return Response.status(400).entity("Fees already paid").build();
		}
		return null;
	}
}
