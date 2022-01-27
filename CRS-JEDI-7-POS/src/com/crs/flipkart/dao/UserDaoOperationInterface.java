package com.crs.flipkart.dao;

import java.util.ArrayList;

import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.User;

public interface UserDaoOperationInterface {
    
    public User getUser (int userId) ;
    public void approveUser (int userId) ;
    public String Authorize(int id,String password) ;

    public int updatePasswordCheck(int userId, String Password) ;

    public void registerStudent(int userId, String password, String userName, String address, int age, String branch, String contact, String email, String gender) ;
    public ArrayList<Integer> getUnapprovedStudents();

    public void registerUser(int userId, String password, boolean isApproved);

}