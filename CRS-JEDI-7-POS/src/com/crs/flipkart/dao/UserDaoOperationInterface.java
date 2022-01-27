package com.crs.flipkart.dao;

import java.util.ArrayList;

import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.User;

public interface UserDaoOperationInterface {
    
    public User getUser (int userId) ;
    public void approveUser (int userId) ;
    public String Authorize(int id,String password) ;

    public int updatePasswordCheck(int userId, String Password) ;

    public ArrayList<Integer> getUnapprovedStudents();

    public void registerUser(int userId, String password, boolean isApproved);

}