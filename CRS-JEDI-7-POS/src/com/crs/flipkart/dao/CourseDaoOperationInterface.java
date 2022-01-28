package com.crs.flipkart.dao;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.RegisteredCourse;

import java.util.ArrayList;

public interface CourseDaoOperationInterface {


    /*
        return the list of userId who are assigned to a particular couserId
    */
    public ArrayList<Integer> getEnrolledStudents(int courseId);

    /*
        set the course details in DB
        *courseId
        *name
        *credit
        *prerequisites
        professor alloated
    */
    public Course getCourse(int courseId) ;



    public ArrayList<Course> viewCourses(); //get the list of all course from catalog

    public ArrayList<RegisteredCourse> getRegisteredCourses(int studentId); //list of registered courses to particular studentId

    public boolean verifyCourse(int courseId) ;     //verify the courseId from catalog whether it exist or not


    /*
        add the course to correspond studentId in registered courses to him
    */
    public void addCourse(int studentId,int courseId) ;

    /*
        delete the course to correspond studentId from registered courses to him
    */
    public void dropCourse(int courseId,int studentId) ;

    /*
        return the list of courses details that were assigned to that professor

    */
    public ArrayList<Course> getProfessorCourses(int userId);

    /*
        gives the list of courses details which are not alloted to any professor
    */
    public ArrayList<Course> getUnregisteredCourses(int userId);


    /*
        alloat the course to a professor(userID)
    */
    public void setRegisterCourse(int userID,int courseId) ;

    /*
        update registeredcourse grade of a student using ID
    */
    public void setGrade(int studentId,int courseId,String grade) ;
}