/**
 * 
 */
package com.crs.flipkart.constants;

/**
 * @author aditya.gupta3
 *
 */
public class SQLQueryConstant {

	//	AdminDaoOperation
	public static final String ADD_COURSE_QUERY = "insert into course values (?,?,?,?,'NA')";
	public static final String DELETE_COURSE_QUERY = "delete from course where courseid = ?";

	//	CourseDaoOperation
	public static final String GET_ALL_REGISTERED_COURSES = "select * from registeredcourse";
	public static final String GET_ALL_COURSES = "select * from course";
	public static final String ADD_REGISTERED_COURSE = "insert into registeredcourse values(?,?,'NA')";
	public static final String DELETE_REGISTERED_COURSE = "delete from registeredcourse where courseId=? and userId=?";
	public static final String GET_PROFESSOR_COURSES = "select * from course where professoralloted=?";
	public static final String GET_UNALLOTED_COURSES = "select * from course where professoralloted=0";
	public static final String ALLOT_COURSE = "update course set professoralloted = ? where courseId=?";
	public static final String ASSIGN_GRADE = "update registeredcourse set grade = ? where courseid = ? and userid = ? ";
	
	//	ProfessorDaoOperation
	public static final String ADD_PROFESSOR_DETAIL ="insert into professor values (?,?,?,?,?,?,?,?)";

	//	StudentDaoOperation
	public static final String ADD_STUDENT_QUERY = "insert into student values (?,?,?,?,?,?,?,?,?,?)";
	public static final String ADD_STUDENT_ROLE = "insert into role values(?,'Student')";
	public static final String GET_PAYMENT_STATUS = "select paymentstatus from student where userid = ?";
	public static final String SET_PAYMENT_STATUS = "update student set paymentstatus=1 where userid = ?";
	public static final String GET_USER_DETAIL = "select * from user where userid = ?";
	public static final String GET_STUDENT_DETAIL = "select * from student where userid = ?" ;
	public static final String SET_REGISTERED = "update student set isregistered=1 where userid = ?";
	public static final String IS_REGISTERED = "select isregistered from student where userid = ?";

	//	UserDaoOperation
	public static final String APPROVE_USER = "update user set isApproved=1 where userId = ?";
	public static final String GET_ALL_USERS = "select * from user";
	public static final String GET_USER_ROLE = "select role from role where userid=?";
	public static final String UPDATE_PASSWORD = "update user set password=? where userid=?";
	public static final String ADD_USER_QUERY = "insert into user values (?,?,?)";
	public static final String UNAPPROVED_USERS = "select * from user where isapproved=0";
}
