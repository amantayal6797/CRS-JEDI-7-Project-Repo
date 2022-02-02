package com.crs.flipkart.dao;

import java.util.ArrayList;

import com.crs.flipkart.bean.User;
import com.crs.flipkart.exception.UserDoNotExistException;

public interface UserDaoOperationInterface {
    
    public User getUser (int userId) ;  //returns user correspond to userId
    public boolean approveUser (int userId) throws UserDoNotExistException ;  //for approve
    public String Authorize(int id,String password) ;   //Authorize on the basis of ID & password

    public int updatePasswordCheck(int userId, String Password) ;   //to update the password

    public ArrayList<Integer> getUnapprovedStudents();  //returns the userId whose isapproved=0

    public void registerUser(int userId, String password, boolean isApproved);  //add new user details in DB

}