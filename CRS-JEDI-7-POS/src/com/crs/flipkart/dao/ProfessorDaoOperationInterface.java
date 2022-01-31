package com.crs.flipkart.dao; /**
 *
 */

import com.crs.flipkart.bean.Professor;

/**
 * @author aditya.gupta3
 *
 */
public interface ProfessorDaoOperationInterface {


    public boolean addProfessor(Professor professor);  //add the new professor details into DB

	public Professor getProfessor(int userId); //Get Details of Professor from the Database
}