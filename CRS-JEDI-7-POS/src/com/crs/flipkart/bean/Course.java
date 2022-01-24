/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author Ashruth
 *
 */
public class Course {
	private int courseID;
    private String courseName;
    private int professorAllotted;
    private int credits;
    private Student listOfEnrolledStudents[] = new Student[10];
    private String prerequisites;
	/**
	 * @return the courseID
	 */
	public int getCourseID() {
		return courseID;
	}
	/**
	 * @param courseID the courseID to set
	 */
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	/**
	 * @return the professorAllotted
	 */
	public int getProfessorAllotted() {
		return professorAllotted;
	}
	/**
	 * @param professorAllotted the professorAllotted to set
	 */
	public void setProfessorAllotted(int professorAllotted) {
		this.professorAllotted = professorAllotted;
	}
	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	/**
	 * @param credits the credits to set
	 */
	public void setCredits(int credits) {
		this.credits = credits;
	}
	/**
	 * @return the listOfEnrolledStudents
	 */
	public Student[] getListOfEnrolledStudents() {
		return listOfEnrolledStudents;
	}
	/**
	 * @param listOfEnrolledStudents the listOfEnrolledStudents to set
	 */
	public void setListOfEnrolledStudents(Student listOfEnrolledStudents[]) {
		this.listOfEnrolledStudents = listOfEnrolledStudents;
	}
	/**
	 * @return the prerequisites
	 */
	public String getPrerequisites() {
		return prerequisites;
	}
	/**
	 * @param prerequisites the prerequisites to set
	 */
	public void setPrerequisites(String prerequisites) {
		this.prerequisites = prerequisites;
	}
}
