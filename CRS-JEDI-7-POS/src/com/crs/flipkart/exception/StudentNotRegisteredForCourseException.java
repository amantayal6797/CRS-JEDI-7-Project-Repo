/**
 * 
 */
package com.crs.flipkart.exception;

/**
 * @author Ashruth
 *
 */
public class StudentNotRegisteredForCourseException extends Exception {

	int courseId,studentId;
	public StudentNotRegisteredForCourseException(int cid,int sid){
		courseId=cid;
		studentId=sid;
	}
	public String getMessage() {
		return "Student with ID "+Integer.toString(studentId)+" is not registered for Course "+Integer.toString(courseId);
		
	}
}
