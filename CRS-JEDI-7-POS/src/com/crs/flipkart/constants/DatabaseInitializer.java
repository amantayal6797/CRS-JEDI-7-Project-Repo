/**
 * 
 */
package com.crs.flipkart.constants;

import com.crs.flipkart.bean.Admin;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;

/**
 * @author Ashruth
 *
 */
public class DatabaseInitializer {
	public void initializeCourseCatalog() {
		Course course1 = new Course();
		course1.setCourseID(1);
		course1.setCourseName("Course 1");
		course1.setCredits(4);
		course1.setPrerequisites("NA");
		course1.setProfessorAllotted(301);
		CourseCatalogDB.catalog.add(course1);
		
		Course course2 = new Course();
		course2.setCourseID(2);
		course2.setCourseName("Course 2");
		course2.setCredits(4);
		course2.setPrerequisites("NA");
		course2.setProfessorAllotted(301);
		CourseCatalogDB.catalog.add(course2);
		
		Course course3 = new Course();
		course3.setCourseID(3);
		course3.setCourseName("Course 3");
		course3.setCredits(4);
		course3.setPrerequisites("NA");
		course3.setProfessorAllotted(301);
		CourseCatalogDB.catalog.add(course3);
		
		Course course4 = new Course();
		course4.setCourseID(4);
		course4.setCourseName("Course 4");
		course4.setCredits(4);
		course4.setPrerequisites("NA");
		course4.setProfessorAllotted(301);
		CourseCatalogDB.catalog.add(course4);

		Course course5 = new Course();
		course5.setCourseID(5);
		course5.setCourseName("Course 5");
		course5.setCredits(4);
		course5.setPrerequisites("NA");
		course5.setProfessorAllotted(302);
		CourseCatalogDB.catalog.add(course5);

		Course course6 = new Course();
		course6.setCourseID(6);
		course6.setCourseName("Course 6");
		course6.setCredits(4);
		course6.setPrerequisites("NA");
		course6.setProfessorAllotted(302);
		CourseCatalogDB.catalog.add(course6);

		Course course7 = new Course();
		course7.setCourseID(7);
		course7.setCourseName("Course 7");
		course7.setCredits(4);
		course7.setPrerequisites("NA");
		course7.setProfessorAllotted(302);
		CourseCatalogDB.catalog.add(course7);

		Course course8 = new Course();
		course8.setCourseID(8);
		course8.setCourseName("Course 8");
		course8.setCredits(4);
		course8.setPrerequisites("NA");
		course8.setProfessorAllotted(302);
		CourseCatalogDB.catalog.add(course8);

	}
	public void initializeUsers() {
		Student student1=new Student();
		student1.setUserId(101);
		student1.setPassword("password");
		student1.setUserName("Student1");
		student1.setAddress("Address 1");
		student1.setAge(20);
		student1.setBranch("CS");
		student1.setContact("99999999");
		student1.setEmail("email1@email.com");
		student1.setGender("M");
		student1.setIsApproved(true);
		student1.setPaymentStatus(false);
		student1.setRegistered(false);
		student1.setRole("Student");
		student1.setRollNo(1);
		AuthorizationDB.listOfStudents.add(student1);
		
		Student student2=new Student();
		student2.setUserId(102);
		student2.setPassword("password");
		student2.setUserName("Student2");
		student2.setAddress("Address 2");
		student2.setAge(20);
		student2.setBranch("ECE");
		student2.setContact("99998888");
		student2.setEmail("email2@email.com");
		student2.setGender("M");
		student2.setIsApproved(true);
		student2.setPaymentStatus(false);
		student2.setRegistered(false);
		student2.setRole("Student");
		student2.setRollNo(2);
		AuthorizationDB.listOfStudents.add(student2);
		
		Admin admin1=new Admin();
		admin1.setUserId(301);
		admin1.setPassword("password");
		admin1.setUserName("Admin1");
		admin1.setAddress("Address 3");
		admin1.setAge(40);
		admin1.setContact("777777777");
		admin1.setGender("F");
		admin1.setIsApproved(true);
		admin1.setRole("Admin");
		AuthorizationDB.listOfAdmins.add(admin1);
		
		Admin admin2=new Admin();
		admin2.setUserId(302);
		admin2.setPassword("password");
		admin2.setUserName("Admin2");
		admin2.setAddress("Address 4");
		admin2.setAge(40);
		admin2.setContact("777777777");
		admin2.setGender("F");
		admin2.setIsApproved(true);
		admin2.setRole("Admin");
		AuthorizationDB.listOfAdmins.add(admin2);
		
		Professor prof1=new Professor();
		prof1.setUserId(201);
		prof1.setPassword("password");
		prof1.setUserName("Prof1");
		prof1.setAddress("Address 5");
		prof1.setAge(40);
		prof1.setContact("777777777");
		prof1.setGender("F");
		prof1.setIsApproved(true);
		prof1.setRole("Professor");
		prof1.setIsApproved(true);
		prof1.setDepartment("CS");
		AuthorizationDB.listOfProfessors.add(prof1);

		Professor prof2=new Professor();
		prof2.setUserId(202);
		prof2.setPassword("password");
		prof2.setUserName("Prof2");
		prof2.setAddress("Address 6");
		prof2.setAge(40);
		prof2.setContact("777777777");
		prof2.setGender("F");
		prof2.setIsApproved(true);
		prof2.setRole("Professor");
		prof2.setIsApproved(true);
		prof2.setDepartment("ECE");
		AuthorizationDB.listOfProfessors.add(prof2);
	}
	
}
