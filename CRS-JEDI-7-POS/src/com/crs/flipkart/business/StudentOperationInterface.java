/**
 * 
 */
package com.crs.flipkart.business;

/**
 * @author Aditya
 *
 */
public interface StudentOperationInterface {
	
	public void registerStudent(int userId, String password, String userName, String address, int age, String branch, String contact, String email, String gender);
	public void showstudent();
	public void setRegistration(int studentId);
}