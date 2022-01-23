/**
 * 
 */
package com.crs.flipkart.constants;

import com.crs.flipkart.bean.Course;

/**
 * @author Ashruth
 *
 */
public class CourseCatalogDB {
	Course catalog[]=new Course[8];
	CourseCatalogDB() {
		Course course1 = new Course();
		course1.setCourseID(1);
		course1.setCourseName("Course 1");
		course1.setCredits(4);
		course1.setPrerequisites("NA");
		course1.setProfessorAllotted(301);
		catalog[0]=course1;
		
		Course course2 = new Course();
		course2.setCourseID(2);
		course2.setCourseName("Course 2");
		course2.setCredits(4);
		course2.setPrerequisites("NA");
		course2.setProfessorAllotted(301);
		catalog[1]=course2;
		
		Course course3 = new Course();
		course3.setCourseID(3);
		course3.setCourseName("Course 3");
		course3.setCredits(4);
		course3.setPrerequisites("NA");
		course3.setProfessorAllotted(301);
		catalog[2]=course3;
		
		Course course4 = new Course();
		course4.setCourseID(4);
		course4.setCourseName("Course 4");
		course4.setCredits(4);
		course4.setPrerequisites("NA");
		course4.setProfessorAllotted(301);
		catalog[3]=course4;

		Course course5 = new Course();
		course5.setCourseID(5);
		course5.setCourseName("Course 5");
		course5.setCredits(4);
		course5.setPrerequisites("NA");
		course5.setProfessorAllotted(302);
		catalog[4]=course5;

		Course course6 = new Course();
		course6.setCourseID(6);
		course6.setCourseName("Course 6");
		course6.setCredits(4);
		course6.setPrerequisites("NA");
		course6.setProfessorAllotted(302);
		catalog[5]=course6;

		Course course7 = new Course();
		course7.setCourseID(7);
		course7.setCourseName("Course 7");
		course7.setCredits(4);
		course7.setPrerequisites("NA");
		course7.setProfessorAllotted(302);
		catalog[6]=course7;

		Course course8 = new Course();
		course8.setCourseID(8);
		course8.setCourseName("Course 8");
		course8.setCredits(4);
		course8.setPrerequisites("NA");
		course8.setProfessorAllotted(302);
		catalog[7]=course8;
	}
	
}
