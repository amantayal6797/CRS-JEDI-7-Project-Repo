/**
 * 
 */
package com.crs.flipkart.bean;

import java.util.ArrayList;

/**
 * @author Ashruth
 *
 */
public class Student extends User{
	private int rollNo;
	private boolean isRegistered;
	private ArrayList<Integer> enrolledCourses = new ArrayList<Integer>();
	private String branch;
	private boolean paymentStatus;
	/**
	 * @return the rollNo
	 */
	public int getRollNo() {
		return rollNo;
	}
	/**
	 * @param rollNo the rollNo to set
	 */
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	/**
	 * @return the isRegistered
	 */
	public boolean isRegistered() {
		return isRegistered;
	}
	/**
	 * @param isRegistered the isRegistered to set
	 */
	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}
	/**
	 * @return the branch
	 */
	public String getBranch() {
		return branch;
	}
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}
	/**
	 * @return the paymentStatus
	 */
	public boolean isPaymentStatus() {
		return paymentStatus;
	}
	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	/**
	 * @param enrolledCourses the enrolledCourses to set
	 */
	public void setEnrolledCourses(ArrayList<Integer> enrolledCourses) {
		this.enrolledCourses = enrolledCourses;
		System.out.println(this.enrolledCourses);
	}
	/**
	 * @return the enrolledCourses
	 */
	public ArrayList<Integer> getEnrolledCourses() {
		return enrolledCourses;
	}
}
