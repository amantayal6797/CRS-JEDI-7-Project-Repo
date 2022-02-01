/**
 * 
 */
package com.crs.flipkart.restController;
import java.util.*;

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
	/*
	StudentDaoOperation studDAOobj = new StudentDaoOperation();
	CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
	GradeCardOperationInterface gradeCardObj=new GradeCardOperation();
	CourseDaoOperationInterface courseDAOobj=new CourseDaoOperation();
	private static Logger logger = Logger.getLogger(CourseRegistrationOperation.class);
	Scanner sc = new Scanner(System.in);
	int userId;
	*/
	
	@POST
	@Path("/courseRegistration")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response courseRegistration(
			@QueryParam("userId") int userId,
			@QueryParam("courseId1") int courseId1,
			@QueryParam("courseId2") int courseId2,
			@QueryParam("courseId3") int courseId3,
			@QueryParam("courseId4") int courseId4,
			@QueryParam("courseId5") int courseId5,
			@QueryParam("courseId6") int courseId6
			) {
		
		StudentDaoOperation studDAOobj = new StudentDaoOperation();
		CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
		
		if(!studDAOobj.isRegistered(userId)) {
			ArrayList<Integer> choices=new ArrayList<Integer>();
			
			/*
			System.out.println("Enter 6 choices");
			System.out.println("Enter Course ID 1:-");
			int courseId=sc.nextInt();
			sc.nextLine();
			choices.add(courseId);
			
			System.out.println("Enter Course ID 2:-");
			courseId=sc.nextInt();
			sc.nextLine();
			choices.add(courseId);
			
			System.out.println("Enter Course ID 3:-");
			courseId=sc.nextInt();
			sc.nextLine();
			choices.add(courseId);
			
			System.out.println("Enter Course ID 4:-");
			courseId=sc.nextInt();
			sc.nextLine();
			choices.add(courseId);
			
			System.out.println("Enter Course ID 5:-");
			courseId=sc.nextInt();
			sc.nextLine();
			choices.add(courseId);
			
			System.out.println("Enter Course ID 6:-");
			courseId=sc.nextInt();
			sc.nextLine();
			choices.add(courseId);
			*/
			choices.add(courseId1);
			choices.add(courseId2);
			choices.add(courseId3);
			choices.add(courseId4);
			choices.add(courseId5);
			choices.add(courseId6);
			
			boolean flag=courseRegistrationObj.registerCourses(userId,choices);
			if (flag) {
				//logger.info("Course Registration Done");
				//logger.info("Pay Fees Now.");
				return Response.status(200).entity("Course Registration Done. Pay Fees Now.").build();	
				
			}
				
		}
				else
					//System.out.println("Already Registered");
					return Response.status(400).entity("Already Registered").build();
		return null;
	}
	
	@POST
	@Path("/addCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(
			@QueryParam("userId") int userId,
			@QueryParam("courseId") int courseId
			
			) {
		try{
			StudentDaoOperation studDAOobj = new StudentDaoOperation();
			CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
		
			if(studDAOobj.isRegistered(userId)) {
		
			/*
			System.out.println("Enter id of course to add");
			int courseId=sc.nextInt();
			sc.nextLine();
			*/
				
			boolean flag=courseRegistrationObj.addCourse(userId,courseId);
			if(flag)
				//System.out.println("Course Succesfully Added\n");
				return Response.status(400).entity("Course Succesfully Added").build();
			
		}else {
			//System.out.println("Complete Course Registration first");
			return Response.status(400).entity("Complete Course Registration first").build();
			
		}
		}
		catch(RegistrationCompletedException | CourseAlreadyRegisteredException | CourseDoesNotExistException e) {
			//System.out.println(e.getMessage());
			return Response.status(400).entity(e.getMessage()).build();
		}
		return null;
	}
	
	
	@POST
	@Path("/dropCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dropCourse(
			@QueryParam("userId") int userId,
			@QueryParam("courseId") int courseId
			) {
		try {
			StudentDaoOperation studDAOobj = new StudentDaoOperation();
			CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
			
		if(studDAOobj.isRegistered(userId)) {
			/*
			System.out.println("Enter id of course to remove");
			int courseId=sc.nextInt();
			sc.nextLine();
			*/
			boolean flag = courseRegistrationObj.dropCourse(userId,courseId);
			if (flag) {
				//logger.info("Course Successfully Dropped");
				return Response.status(200).entity("Course Successfully Dropped").build();
			}
		}else {
			//System.out.println("Complete Course Registration first");
			return Response.status(200).entity("Complete Course Registration first").build();
		}
		}catch(NoCourseToDropException | CourseDoesNotExistException |  CourseNotRegisteredToDropException e) {
			//System.out.println(e.getMessage());
			return Response.status(400).entity(e.getMessage()).build();
		}
		return null;
	}
	
	
	@GET
	@Path("/viewCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Course>  viewCourses() {
		CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
		
		ArrayList<Course> catalog = courseRegistrationObj.viewCourses();
		/*
		for(Course course:catalog) {
			logger.info("Course Id:- "+course.getCourseID());
			logger.info("Course Name:- "+course.getCourseName());
			logger.info("Course Credits:- "+course.getCredits());
			logger.info("Course Prerequisites:- "+course.getPrerequisites());
			logger.info("Course Professor Id:- "+course.getProfessorAllotted());
			logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
		*/
		return catalog;
	}
	
	
	@GET
	@Path("/viewRegisteredCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<RegisteredCourse> viewRegisteredCourses(
			@QueryParam("userId") int userId) {
		
		StudentDaoOperation studDAOobj = new StudentDaoOperation();
		CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
		
		if(studDAOobj.isRegistered(userId)) {
			
			/*logger.info("Registered Courses");
			ArrayList<RegisteredCourse> listOfRegisteredCourses=courseRegistrationObj.viewRegisteredCourse(userId);
			for(RegisteredCourse regCourse:listOfRegisteredCourses) {
				Course course=courseDAOobj.getCourse(regCourse.getCourseID());
				logger.info("Course Id:-"+course.getCourseID()+"\tCourse Name:-"+course.getCourseName());
				}*/
			ArrayList<RegisteredCourse> listOfRegisteredCourses=courseRegistrationObj.viewRegisteredCourse(userId);
			return listOfRegisteredCourses;
		}
		else {
			//System.out.println("Complete Course Registration first");
			return null;
		}
		
	}
	
	
	@GET
	@Path("/viewGradeCard")
	@Produces(MediaType.APPLICATION_JSON)
	public GradeCard viewGradeCard(
			@QueryParam("userId") int userId) {
		
		StudentDaoOperation studDAOobj = new StudentDaoOperation();
		GradeCardOperationInterface gradeCardObj=new GradeCardOperation();
		
		try {
		if(studDAOobj.isRegistered(userId)) {
			GradeCard gradeCard=gradeCardObj.viewGradeCard(userId);
			/*
			logger.info("Grade Card");
			logger.info("User ID:-"+userId);
			for(Grade grade:gradeCard.getListOfGrades()) {
				logger.info("Course ID:-"+grade.getCourseID()+" Grade:-"+grade.getGrade());
				}
			*/
			return gradeCard;
			}
		else {
			//System.out.println("Complete Course Registration first");
			return null;
		}
		}catch(UserDoesNotExistException e) {
			//System.out.println(e.getMessage());
			return null;
		}
	}
	
	@POST
	@Path("/makePayment")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response makePayment(
			@QueryParam("userId") int userId,
			@QueryParam("modeChoice") int modeChoice
			) {
		
		StudentDaoOperation studDAOobj = new StudentDaoOperation();
		CourseRegistrationOperationInterface courseRegistrationObj=new CourseRegistrationOperation();
		GradeCardOperationInterface gradeCardObj=new GradeCardOperation();
		CourseDaoOperationInterface courseDAOobj=new CourseDaoOperation();
		
		if (!studDAOobj.isRegistered(userId)) {
			//System.out.println("Complete Course Registration first");
			return Response.status(400).entity("Complete Course Registration first").build();
		} else if(!studDAOobj.getPaymentStatus(userId)) {
			/*
			System.out.println("Please select payment mode");
			System.out.println("1. Online Mode");
			System.out.println("2. Offline Mode");
			int modeChoice = sc.nextInt();
			sc.nextLine();
			boolean flag=false;
			*/
			
			boolean flag=false;
			if (modeChoice==1) {
				OnlinePayment onlinePayment = new OnlinePayment();
				flag = onlinePayment.payByCard(userId, 1, 12);
			} else if (modeChoice==2) {
				OfflinePayment offlinePayment = new OfflinePayment();
				flag = offlinePayment.payByCash(userId, 1);
			} else {
				//System.out.println("Invalid payment mode selected");
				return Response.status(400).entity("Invalid payment mode selected").build();
			}

			if(flag)
				//logger.info("Transaction completed\nFees Paid ");
				return Response.status(200).entity("Transaction completed\nFees Paid").build();
		} else {
			//System.out.println("Fees already paid");
			return Response.status(400).entity("Fees already paid").build();
		}
		return null;
	}
}
