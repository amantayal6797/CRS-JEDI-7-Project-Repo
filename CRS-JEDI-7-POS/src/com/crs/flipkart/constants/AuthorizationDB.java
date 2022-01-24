/**
 * 
 */
package com.crs.flipkart.constants;

import com.crs.flipkart.bean.Admin;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;

/**
 * @author Ashruth
 *
 */
public class AuthorizationDB {
	protected Student listOfStudents[]=new Student[100];
	protected Admin listOfAdmins[]=new Admin[2];
	protected Professor listOfProfessors[]=new Professor[2];
	
	public AuthorizationDB() {
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
		listOfStudents[0]=student1;
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
		listOfStudents[1]=student2;
		Admin admin1=new Admin();
		admin1.setUserId(201);
		admin1.setPassword("password");
		admin1.setUserName("Admin1");
		admin1.setAddress("Address 3");
		admin1.setAge(40);
		admin1.setContact("777777777");
		admin1.setGender("F");
		admin1.setIsApproved(true);
		admin1.setRole("Admin");
		admin1.setNationality("India");
		listOfAdmins[0]=admin1;
		Admin admin2=new Admin();
		admin2.setUserId(202);
		admin2.setPassword("password");
		admin2.setUserName("Admin2");
		admin2.setAddress("Address 4");
		admin2.setAge(40);
		admin2.setContact("777777777");
		admin2.setGender("F");
		admin2.setIsApproved(true);
		admin2.setRole("Admin");
		admin2.setNationality("India");
		listOfAdmins[1]=admin2;
		
		Professor prof1=new Professor();
		prof1.setUserId(301);
		prof1.setPassword("password");
		prof1.setUserName("Prof1");
		prof1.setAddress("Address 5");
		prof1.setAge(40);
		prof1.setContact("777777777");
		prof1.setGender("F");
		prof1.setIsApproved(true);
		prof1.setRole("Professor");
		prof1.setNationality("India");
		prof1.setIsApproved(true);
		prof1.setDepartment("CS");
		listOfProfessors[0]=prof1;

		Professor prof2=new Professor();
		prof2.setUserId(302);
		prof2.setPassword("password");
		prof2.setUserName("Prof2");
		prof2.setAddress("Address 6");
		prof2.setAge(40);
		prof2.setContact("777777777");
		prof2.setGender("F");
		prof2.setIsApproved(true);
		prof2.setRole("Professor");
		prof2.setNationality("India");
		prof2.setIsApproved(true);
		prof2.setDepartment("ECE");
		listOfProfessors[1]=prof2;
	}
	
	/**
	 * @return the listOfStudents
	 */
	public Student[] getListOfStudents() {
		return listOfStudents;
	}

	/**
	 * @param listOfStudents the listOfStudents to set
	 */
	public void setListOfStudents(Student[] listOfStudents) {
		this.listOfStudents = listOfStudents;
	}
	
	public Student getStudentInfo(int id) {
		for(Student student:listOfStudents) {
			if(student==null)
				continue;
			if(student.getUserId()==id) {
				return student;
			}
		}
		return null;
	}
}
