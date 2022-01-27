package com.crs.flipkart.dao;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.RegisteredCourse;

import java.util.ArrayList;

public interface CourseDaoOperationInterface {

    public ArrayList<Integer> getEnrolledStudents(int courseId);
    public Course getCourse(int courseId) ;
    public ArrayList<Course> viewCourses();

    public ArrayList<RegisteredCourse> getRegisteredCourses(int studentId);

    public boolean verifyCourse(int courseId) ;

    public void addCourse(int studentId,int courseId) ;
    public void dropCourse(int courseId,int studentId) ;


    public ArrayList<Course> getProfessorCourses(int userId);
    public ArrayList<Course> getUnregisteredCourses(int userId);
    public void setRegisterCourse(int userID,int courseId) ;

    public void setGrade(int studentId,int courseId,String grade) ;
}