package com.crs.flipkart.exception;

public class CourseDoesNotExistException extends Exception {
	int courseId;
	public CourseDoesNotExistException(int cid) {
		courseId=cid;
	}
	public String getMessage() {
		return "Course "+Integer.toString(courseId)+" Does Not Exist";
	}
}
