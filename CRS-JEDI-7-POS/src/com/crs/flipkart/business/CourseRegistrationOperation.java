/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.Scanner;

import com.crs.flipkart.application.CRSStudentMenu;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.bean.Student;

/**
 * @author Ashruth
 *
 */
public class CourseRegistrationOperation extends CRSStudentMenu implements CourseRegistrationOperationInterface {

	public void viewRegisteredCourse(int studentId) {
		System.out.println(studentId);
		Student[] listOfStudents = authObj.getListOfStudents();
		for (Student stu: listOfStudents) {
			if (stu.getUserId() == studentId) {
				System.out.println(stu.getEnrolledCourses());
				return;
			}
		}
	}

	public void viewCourses() {
		// Display all courses in catalog
		
		Course[] courseCatalog = courseCatalogDB.getCatalog();
		System.out.println(courseCatalog);
	}

	@SuppressWarnings("deprecation")
	public void dropCourse(int studentId) {
		// take course as input
		// remove registration from CoursesRegisteredDB
		// update enrolledCourses for student
		
		Student[] listOfStudents = authObj.getListOfStudents();
		Student stuInfo = new Student();
		for (Student stu: listOfStudents) {
			if (stu.getUserId() == studentId) {
				stuInfo = stu;
				break;
			}
		}
		
		ArrayList<Integer> listOfEnrolledCourses = stuInfo.getEnrolledCourses();
		if (listOfEnrolledCourses.size() == 0) {
			System.out.println("Minimum one course should be added");
			return;
		}
		
		System.out.println("Enter course id to be dropped");
		Scanner sc = new Scanner (System.in);
		int courseId = sc.nextInt();
		
		// Check whether course exists in CourseCatalogDB
		Course[] courseCatalog = courseCatalogDB.getCatalog();
		
		for (Course course: courseCatalog) {
			if (course.getCourseID()==courseId) {
				listOfEnrolledCourses.remove(new Integer(courseId));
				stuInfo.setEnrolledCourses(listOfEnrolledCourses);
				ArrayList<RegisteredCourse> registeredCourses = coursesRegisteredDB.getListOfRegisteredCourses();
				
				for (RegisteredCourse registeredCourse: registeredCourses) {
					if (registeredCourse.getCourseID()==courseId) {
						registeredCourse.setNumOfStudents(registeredCourse.getNumOfStudents()-1);
						if (registeredCourse.getNumOfStudents()==0) {
							registeredCourses.remove(registeredCourse);
						}
						return;
					}
				}
			}
		}
		
		System.out.println("Invalid CourseId");
		
	}

	public void addCourse(int studentId) {
		// take course as input
		// add registration in CoursesReGisteredDB
		// update enrolledCourses for student
		
		Student[] listOfStudents = authObj.getListOfStudents();
		for (Student stuInfo: listOfStudents) {
			if (stuInfo.getUserId() == studentId) {
				ArrayList<Integer> listOfEnrolledCourses = stuInfo.getEnrolledCourses();
				if (listOfEnrolledCourses.size() == 6) {
					System.out.println("Maximum 6 courses can be added");
					return;
				}
				
				System.out.println("Enter course id to be added");
				Scanner sc = new Scanner (System.in);
				int courseId = sc.nextInt();
				
				// Check whether course exists in CourseCatalogDB
				Course[] courseCatalog = courseCatalogDB.getCatalog();
				
				for (Course course: courseCatalog) {
					if (course.getCourseID()==courseId) {
						listOfEnrolledCourses.add(courseId);
						System.out.println(listOfEnrolledCourses);
						stuInfo.setEnrolledCourses(listOfEnrolledCourses);
						ArrayList<RegisteredCourse> registeredCourses = coursesRegisteredDB.getListOfRegisteredCourses();
						
						for (RegisteredCourse registeredCourse: registeredCourses) {
							if (registeredCourse.getCourseID()==courseId) {
								registeredCourse.setNumOfStudents(registeredCourse.getNumOfStudents()+1);
								coursesRegisteredDB.setListOfRegisteredCourses(registeredCourses);
								authObj.setListOfStudents(listOfStudents);
								System.out.println(authObj.getListOfStudents()[0].getEnrolledCourses());
								return;
							}
						}
						
						RegisteredCourse registeredCourse = new RegisteredCourse();
						registeredCourse.setCourseID(courseId);
						registeredCourse.setNumOfStudents(1);
						registeredCourses.add(registeredCourse);
						coursesRegisteredDB.setListOfRegisteredCourses(registeredCourses);
						authObj.setListOfStudents(listOfStudents);
						System.out.println(authObj.getListOfStudents()[0].getEnrolledCourses());
						return;
					}
				}
				
				System.out.println("Invalid CourseId");
				return;
			}
		}
		
		
	}

	public void registerCourses() {
		// take 6 courses as input assign 4
		// add registration in CoursesReGisteredDB
		// update enrolledCourses for student
		//update isRegistered in student
		
	}

}
