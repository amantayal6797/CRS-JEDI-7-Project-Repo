package com.crs.flipkart.dao;

import com.crs.flipkart.bean.Student;

public interface StudentDaoOperationInterface {

    public boolean getPaymentStatus (int studentId) ;

    /*
	 * Method to register a student
	 * @param student Student object containing the student information
	 */
    public void registerStudent (Student student);  

    public boolean setPaymentStatus (int studentId);    // Method to set the payment status of a Student to true

    public Student showStudent(int studentId) ;     //print the student details to correspond Id

    public void setRegistration(int studentId);     //Method to set the registration status of a student to true.

    public boolean isRegistered(int studentId) ;
}