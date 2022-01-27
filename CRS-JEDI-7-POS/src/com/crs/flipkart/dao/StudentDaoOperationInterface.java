package com.crs.flipkart.dao;

import com.crs.flipkart.bean.Student;

public interface StudentDaoOperationInterface {

    public boolean getPaymentStatus (int studentId) ;
    public void registerStudent (Student student);
    public boolean setPaymentStatus (int studentId);
    public Student showStudent(int studentId) ;

    public void setRegistration(int studentId);
    public boolean isRegistered(int studentId) ;
}